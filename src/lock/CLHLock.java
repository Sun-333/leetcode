package lock;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;


/**
 * 线程只在本地变量上自旋，它不断轮询前驱的状态，如果发现前驱释放了锁就结束自旋，获得锁。
 *
 */
public class CLHLock {
    public static class CLHNode{
        public volatile boolean isLock = true;
    }

    //TreadLocal 保存每个线程的CLHNode状态
    ThreadLocal<CLHNode> local = new ThreadLocal<>();
    //尾部节点
    private CLHNode tail = new CLHNode();
    AtomicReferenceFieldUpdater<CLHLock,CLHNode> UPDATE = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class,CLHNode.class,"tail");

    public void lock(){
        //创建当前线程绑定的节点,并保存
        CLHNode node = new CLHNode();
        local.set(node);
        CLHNode preNode = UPDATE.getAndSet(this,node);
        //前驱节点不为空，说明当锁被其他线程占用，则不停轮询判断前驱节点的状态
        if(preNode!=null){
            while (preNode.isLock){}
            //垃圾回收
            preNode=null;
        }
        //前驱节点为空，说明当前锁未被其他线程访问名，直接返回
    }

    public void unLock(){
        //获取当前线程绑定节点
        CLHNode node = local.get();
        //如果尾节点为当前节点，说明后继无线程在轮询获取锁，将尾节点设置为null
        if(!UPDATE.compareAndSet(this,node,null)){
            //存在后继线程在竞争锁，释放锁
            node.isLock=false;
            Map map;
        }
        //垃圾回收
        node=null;
    }



    /**
     * 最简单的自旋锁 CAS
     * <p>
     * 结论：自旋锁不是公平的，即无法满足等待时间最长的线程优先获取锁。不公平的锁就会存在“线程饥饿”问题。
     */
    class SpinLock {
        //存储当前获取锁的线程
        private AtomicReference<Thread> cas = new AtomicReference<Thread>();

        public void lock() {
            Thread current = Thread.currentThread();
            // 利用CAS
            while (!cas.compareAndSet(null, current)) {
                // DO nothing
            }
        }

        public void unlock() {
            Thread current = Thread.currentThread();
            cas.compareAndSet(current, null);
        }
    }

    /**
     * 可冲入锁，对于方案一，添加了一个计数器
     */
    class ReentrantSpinLock {
        private AtomicReference<Thread> cas = new AtomicReference<Thread>();
        private int count;

        public void lock() {
            Thread current = Thread.currentThread();
            if (current == cas.get()) { // 如果当前线程已经获取到了锁，线程数增加一，然后返回
                count++;
                return;
            }
            // 如果没获取到锁，则通过CAS自旋
            while (!cas.compareAndSet(null, current)) {
                // DO nothing
            }
        }

        public void unlock() {
            Thread cur = Thread.currentThread();
            if (cur == cas.get()) {
                if (count > 0) {// 如果大于0，表示当前线程多次获取了该锁，释放锁通过count减一来模拟
                    count--;
                } else {// 如果count==0，可以将锁释放，这样就能保证获取锁的次数与释放锁的次数是一致的了。
                    cas.compareAndSet(cur, null);
                }
            }
        }
    }

    /**
     * 消费者｜服务者实现公平锁
     * <p>
     * 思路：
     * 每当有线程获取锁的时候，就给该线程分配一个递增的id，我们称之为排队号，
     * 同时，锁对应一个服务号，每当有线程释放锁，服务号就会递增，此时如果服务号与某个线程排队号一致，
     * 那么该线程就获得锁，由于排队号是递增的，所以就保证了最先请求获取锁的线程可以最先获取到锁，就实现了公平性。
     * <p>
     * 问题：排队号，在线程获取锁时发送给线程，线程运行完功能后将排队号作为参数调用unlock()释放锁，
     * 因为这个排队号是可以被修改的，一旦排队号被不小心修改了，那么锁将不能被正确释放
     */
    class TicketLock {
        /**
         * 服务号
         */
        private AtomicInteger serviceNum = new AtomicInteger();
        /**
         * 排队号
         */
        private AtomicInteger ticketNum = new AtomicInteger();

        /**
         * lock:获取锁，如果获取成功，返回当前线程的排队号，获取排队号用于释放锁.
         */
        public int lock() {
            int currentTicketNum = ticketNum.incrementAndGet();
            while (currentTicketNum != serviceNum.get()) {
                // Do nothing
            }
            return currentTicketNum;
        }

        /**
         * unlock:释放锁，传入当前持有锁的线程的排队号 <br/>
         *
         * @param ticketnum
         */
        public void unlock(int ticketnum) {
            serviceNum.compareAndSet(ticketnum, ticketnum + 1);
        }
    }


    /**
     * 解决上一个问题：
     * 添加Threadlocal()在对象内部存储每一个线程的排队号
     *
     * 问题：多处理器系统上，每个进程/线程占用的处理器都在读写同一个变量serviceNum ，
     * 每次读写操作都必须在多个处理器缓存之间进行缓存同步，这会导致繁重的系统总线和内存的流量，大大降低系统整体的性能。
     */
    class TicketLockV2 {
        /**
         * 服务号
         */
        private AtomicInteger serviceNum = new AtomicInteger();
        /**
         * 排队号
         */
        private AtomicInteger ticketNum = new AtomicInteger();
        /**
         * 新增一个ThreadLocal，用于存储每个线程的排队号
         */
        private ThreadLocal<Integer> ticketNumHolder = new ThreadLocal<Integer>();

        public void lock() {
            int currentTicketNum = ticketNum.incrementAndGet();
            // 获取锁的时候，将当前线程的排队号保存起来
            ticketNumHolder.set(currentTicketNum);
            while (currentTicketNum != serviceNum.get()) {
                // Do nothing
            }
        }

        public void unlock() {
            // 释放锁，从ThreadLocal中获取当前线程的排队号
            Integer currentTickNum = ticketNumHolder.get();
            serviceNum.compareAndSet(currentTickNum, currentTickNum + 1);
        }
    }
}

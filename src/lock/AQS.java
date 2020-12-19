package lock;

import java.util.Map;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AQS extends AbstractQueuedSynchronizer {
    //对于独占式锁，覆盖如下方法
    @Override
   public final boolean tryAcquire(int arg) {
        //进入状态，当前未有人占用锁，当前有人占用锁（其他人，自己）
        //需要操作：true 成果获取锁（1.改变state状态 2.设置当前使用锁线程为本线程）
        final  Thread current = Thread.currentThread();
        int state = getState();
        if (state==0){
            if (!hasQueuedPredecessors()&&compareAndSetState(0,arg))
                setExclusiveOwnerThread(current);
            return true;
            //重新入判断
        }else if (getExclusiveOwnerThread()==current){
            int newState = state+arg;
            if (newState<0) throw new RuntimeException("重入溢出");
            setState(newState);
            return true;
        }
        //尝试获取锁失败
        return false;
    }

    @Override
    public final boolean tryRelease(int arg) {
        //1.计算的到当前state
        //2.判断当前是否由此线程占用锁
        //3.锁释放后状态（有等待获取锁的线程--无需操作AQS内部自动激活下一节点线程无需维护），只需设置为空,状态修改
        int state = getState()-arg;
        if (Thread.currentThread()!=getExclusiveOwnerThread()) throw new RuntimeException("释放锁操作异常");
        boolean ans=false;
        if (state==0){
            //当前线程释放了锁
            ans=true;
            setExclusiveOwnerThread(null);
            Map map;
        }
        setState(state);
        return ans;
    }
    public void lock(){
        acquire(1);
    }
    public void unlock(){
        release(1);
    }

    //对于共享锁覆盖如下方法
    @Override
    protected int tryAcquireShared(int arg) {
        return super.tryAcquireShared(arg);
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        return super.tryReleaseShared(arg);
    }
}

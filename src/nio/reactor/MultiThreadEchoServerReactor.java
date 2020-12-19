package nio.reactor;

import lock.Synchronized;
import threadpool.ThreadPool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yushansun
 * @title: MultiThreadEchoServerReactor
 * @projectName leetcode
 * @description: 多线程Reactor模式
 * 1.多select（线程）请求连接Reactor和IO读写Reactor
 * 对于业务处理采用多线程处理，引入线程池
 * 对于每个channel绑定一个Handler实例，每个IO事件都异步调用，所以对每个channel的业务执行进行加锁
 * @date 2020/7/18:35 下午
 */

public class MultiThreadEchoServerReactor {
    ServerSocketChannel serverSocketChannel;
    //多选择器
    Selector[] selectors = new Selector[2];
    //引入多个反应器
    SubReactor[] subReactors = new SubReactor[2];
    MultiThreadEchoServerReactor() throws IOException {
        //初始化Selectors、SubReactors
        selectors[0] = Selector.open();
        selectors[1] = Selector.open();
        //给serverSocketChannel绑定参数、设置为非阻塞
        serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(1800));
        serverSocketChannel.configureBlocking(false);
        //向第一个select组册绑通道为ServerSocketChannel并且设置select选择事件为OP_Accept
        //事件SelectKey 绑定处理器为AcceptHandler
        serverSocketChannel.register(selectors[0],SelectionKey.OP_ACCEPT,new AcceptHandler());

        //第一个响应器处理IO连接
        subReactors[0]=new SubReactor(selectors[0]);
        //处理IO读写
        subReactors[1]=new SubReactor(selectors[1]);
    }

    private void startService(){
        new Thread(subReactors[0]).start();
        new Thread(subReactors[1]).start();
    }



    class SubReactor implements Runnable{
        //响应器绑定的选择器
        final Selector selector;

        SubReactor(Selector selector){
            this.selector=selector;
        }
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    //初始选择键
                    selector.select();
                    //获取选择键
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> it =selectionKeys.iterator();
                    //遍历所以选择键，将选择键对应的IO事件发送给绑定处理器
                    while (it.hasNext()){
                        SelectionKey sk = it.next();
                        dispatch(sk);
                    }
                    //清空已经处理的IO事件
                    selectionKeys.clear();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        //分发器，将IO事件发送给对应Handler
        private void dispatch(SelectionKey sk){
            Runnable rn = (Runnable)sk.attachment();
            rn.run();
        }
    }
    //请求连接IO事件处理器
    //责任：创建连接通道 创建处理器（交给处理器初始化 1绑定通道IO事件2.向selector注册）
    class AcceptHandler implements Runnable{
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                if (socketChannel!=null)
                    new MultiThreadEchoHandler(selectors[0],socketChannel);
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new MultiThreadEchoServerReactor().startService();
    }
}

/**
 * 多线程处理IO读写事件
 */
class MultiThreadEchoHandler  implements Runnable{
    final SocketChannel socketChannel;
    static final ExecutorService pool = Executors.newFixedThreadPool(4);
    final ByteBuffer buffer = ByteBuffer.allocate(1024);
    int state=RECEIVE;
    final static int RECEIVE =0;
    final static int WRITE=1;


    MultiThreadEchoHandler(Selector selector,SocketChannel socketChannel) throws ClosedChannelException {
        this.socketChannel=socketChannel;
        socketChannel.
                register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE,this);
    }

    @Override
    public void run() {
        pool.submit(new AsyncTask());
    }

    public synchronized void asyncRun(){
        try {
            if (state==RECEIVE){
                int length = 0;
                //channel 数据写入buffer
                while ((length=socketChannel.read(buffer))>0){
                    System.out.println(new String(buffer.array(), 0, length));
                    //切换为读取状态
                    buffer.flip();
                    state=WRITE;
                }
            }else {
                //将数据写入通道
                socketChannel.write(buffer);
                //将buffer切换为读取模式
                buffer.clear();
                //
                state=RECEIVE;
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    class AsyncTask implements Runnable{
        @Override
        public void run() {
            MultiThreadEchoHandler.this.asyncRun();
        }
    }
}

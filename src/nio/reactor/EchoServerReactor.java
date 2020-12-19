package nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author yushansun
 * @title: EchoServerReactor
 * @projectName leetcode
 * @description: TODO
 * @date 2020/7/15:03 下午
 */
//反应器时查询IO事件，并分发到Handler处理器进行处理
public class EchoServerReactor implements Runnable {
    Selector selector;
    ServerSocketChannel serverSocketChannel;
    EchoServerReactor() throws IOException{
        //初始化
        selector=Selector.open();
        serverSocketChannel=ServerSocketChannel.open();

        //绑定端口
        InetSocketAddress address = new InetSocketAddress(1800);
        serverSocketChannel.socket().bind(address);
        //设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);

        //接收accept事件
       SelectionKey sk =
               serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
       //对当前server绑定的accept事件绑定AcceptorHandler处理器
        sk.attach(new AcceptorHandle());
    }
    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                selector.select();
                //获取当前就绪事件
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()){
                    SelectionKey sk = it.next();
                    dispatch(sk);
                }
                //清空当前已经处理的所有选择键
                selected.clear();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    //将此选择键交给对应handler处理
    private void dispatch(SelectionKey sk){
        Runnable run = (Runnable) sk.attachment();
        run.run();
    }
    public static void main(String[] args) throws IOException {
        new EchoServerReactor().run();
    }

    /**
     * 处理IO accept 事件
     * 创建连接通道
     * 向select组册当前通道的IO read事件
     * 绑定EchoHandler到此选择键
     */
    class AcceptorHandle  implements Runnable{
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE,new EchoHandler(socketChannel));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //处理IO read write
    class EchoHandler implements Runnable{
        final SocketChannel socketChannel;
        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int state=RECEIVE;
        final static int RECEIVE =0;
        final static int WRITE=1;
        @Override
        public void run() {
            try {
                if (state==RECEIVE){
                    int length = 0;
                    //channel 数据写入buffer
                    while ((length=socketChannel.read(byteBuffer))>0){
                        System.out.println(new String(byteBuffer.array(), 0, length));
                        //切换为读取状态
                        byteBuffer.flip();
                        state=WRITE;
                    }
                }else {
                    //将数据写入通道
                    socketChannel.write(byteBuffer);
                    //将buffer切换为读取模式
                    byteBuffer.clear();
                    //
                    state=RECEIVE;
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        EchoHandler(SocketChannel socketChannel){
            this.socketChannel=socketChannel;
        }
    }
}

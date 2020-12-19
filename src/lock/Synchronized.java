package lock;

/**
 * 当存在线程占用锁时，其他请求此方法的线程会休眠。
 * 当某线程释放锁时，其余所有等待获取对应锁的线程都会被唤醒一起竞争锁。对竞争锁失败的线程又会休眠
 * 分析：不公平锁（存在饥饿线程），大量线程处于无意义的 运行状态-休眠状态的切换（用户态-内核态）
 */
public class  Synchronized  {
    //类锁
    public synchronized static void fun1(){

    }
    //对象锁
    public synchronized void func2(){

    }
}

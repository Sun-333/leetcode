public class PriorityQueue<key extends Comparable<key>> {
    //储存元素数组
    private key[] qp;
    //当前priority queue 元素个数
    private int N;
    public PriorityQueue(int cap){
        qp= (key[]) new Comparable[cap+1];
    }

    //返回当前最大元素
    public key max(){
        return qp[1];
    }
    //插入元素
    public void insert(key e){
        N++;
        qp[N]=e;
        swim(N);
    }
    //删除并返回当前最大元素
    public key delMax(){
        key max = qp[1];
        exch(1,N);
        qp[N]=null;
        N--;
        sink(1);
        return max;
    }
    //上浮第k个元素，维护最大堆性质
    private void swim(int k){
        int index=k;
        //若它比父亲节点大就上浮
        while (index>1&&less(parent(index),index)){
            exch(parent(index),index);
            index=parent(index);
        }
    }
    //下层第k个元素，维护最大堆性质
    private void sink(int k){
        while (left(k)<=N){
            //假设左边节点比较大
            int order = left(k);
            if (right(k)<=N&&less(order,right(k)))
                order=right(k);

            if (less(order,k)) break;

            exch(order,k);
            k=order;
        }

    }
    //交换两个元素
    private void exch(int i,int j){
        key temp = qp[i];
        qp[i]=qp[j];
        qp[j]=temp;
    }

    private int parent(int root){
        return root/2;
    }

    private int right(int root){
        return root*2+1;
    }

    private int left(int root){
        return root*2;
    }

    private boolean less(int i,int j){
        return qp[i].compareTo(qp[j])<0;
    }
}

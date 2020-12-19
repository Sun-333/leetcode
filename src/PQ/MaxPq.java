package PQ;

/**
 * @author yushansun
 * @title: MaxPq
 * @projectName leetcode
 * @description: 优先级队列
 * @date 2020/7/1611:30 上午
 */
public class MaxPq<Key extends Comparable<Key>> {
    //数组方式存储元素
    Key [] arr;
    //当前优先级队列中的元素个数
    int N;
    public MaxPq(int cap){
        arr = (Key[])new Comparable[cap+1];
    }

    public void insert(Key element){
        //插入末尾，更新N,维护大顶堆
        N++;
        arr[N]=element;
        swim(N);
    }
    public Key delMax(){
        Key max = arr[N];
        //交换位置，维护大顶堆，删除元素，更新N
        swap(1,N);
        arr[N]=null;
        sink(1);
        N--;
        return max;
    }

    //上浮第k个元素
    private void swim(int k){
        while (k>1){
            if (!compareLess(parent(k),k)) return;
            else {
                swap(parent(k),k);
                k=parent(k);
            }
        }
    }
    //下层第k个元素
    private void sink(int k){
        while (left(k)<=N){
            //假设最大元素为左节点
            int order = left(k);
            //如果右节点存在且比左节点大，设置最大为右节点
            if(right(k)<=N&&compareLess(order,right(k)))
                order=right(k);
            //若当前节点比左右节点大，返回
            if (compareLess(order,k)) return;
            //交换当前节点与最大节点
            swap(k,order);
            //更新比较位
            k=order;
        }
    }
    //查找第k个元素的左孩子
    private int left(int key){
        return key*2;
    }
    //查找第k个元素的右孩子
    private int right(int key){
        return key*2+1;
    }
    //找到第k个元素的父节点
    private int parent(int key){
        return key/2;
    }
    //比价第一个元素是否小于第二个元素
    private boolean compareLess(int i,int j){
        return arr[i].compareTo(arr[j])<0;
    }
    //交换两个元素
    private void swap(int i,int j){
        Key temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}

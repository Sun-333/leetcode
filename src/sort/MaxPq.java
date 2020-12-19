package sort;

import java.util.Arrays;

/**
 * @author yushansun
 * @title: MaxPq
 * @projectName leetcode
 * @description: 优先级队列 通过二叉堆实现 二叉堆实现方式为完全二叉树
 * @date 2020/12/1311:55 下午
 */
public class MaxPq<Key extends Comparable<Key>> {
    private Key[] arr;
    private int N;

    /**
     * 构造函数
     * @param n
     */
    public MaxPq(int n){
        arr = (Key[]) new Comparable[n+1];
    }

    public void insert(Key key){
        arr[++N]=key;
        swim(N);
    }

    public Key maxElem(){
        Key max = arr[1];
        exch(1,N);
        arr[N--]=null;
        sink(1);
        return max;
    }

    private int left(int i){
        return  i<<1;
    }

    private int right(int j){
        return (j<<1)+1;
    }

    private void exch(int i,int j){
        Key temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    private void exch(Comparable[] arr,int i,int j){
        Comparable temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    private boolean less(int i,int j){
        return arr[i].compareTo(arr[j])<0;
    }

    private void sink(int i){
        while (i*2<=N){
            int k=left(i);
            if(right(i)<=N&&less(left(i),right(i))) k=right(i);
            if(!less(i,k)) break;
            exch(i,k);
            i=k;
        }
    }
    private void sink(Comparable[] arr,int begin,int end){
        while (begin*2<=end){
            int k=left(begin);
            if(right(begin)<=end&&arr[left(begin)].compareTo(arr[right(begin)])<0) k=right(begin);
            if(arr[begin].compareTo(arr[k])>0) break;
            exch(arr,begin,k);
            begin=k;
        }
    }

    private void swim(int i){
        while (i>1&&less(i/2,i)){
            exch(i,i/2);
            i=i/2;
        }
    }

    /**
     * 堆排序
     */
    public void sort(Comparable[] arr){
        int N= arr.length-1;
        //构造堆
        for (int i=N/2;i>=1;i--){
            sink(arr,i,N);
        }
        System.out.println(Arrays.toString(arr));
        //堆排序
        while(N>1){
            exch(arr,1,N--);
            sink(arr,1,N);
        }
    }

    public static void main(String[] args) {
        MaxPq pq = new MaxPq(10);
        Integer[] arr = new Integer[]{10,1,2,3,4,5};
        for (Integer a: arr
             ) {
            pq.insert(a);
        }
        for (int i=0;i<6;i++)
            System.out.println(pq.maxElem());

        //堆排序测试
        Integer[] arr_2 = new Integer[]{null,10,1,2,3,4,5};
        pq.sort(arr_2);
        System.out.println(Arrays.toString(arr_2));
    }
}

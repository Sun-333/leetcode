package Tree;

import java.security.PublicKey;

/**
 * @author yushansun
 * @title: PriorityQueue
 * @projectName leetcode
 * @description: TODO
 * @date 2020/10/92:05 下午
 */
public class PriorityQueue<Key extends Comparable<Key>> {
    Key[] nums;
    int N;

    public PriorityQueue(int maxN){
        nums= (Key[]) new Comparable[maxN+1];
        N=0;
    }

    /**
     * 添加元素
     */
    public void add(Key key){
        N++;
        nums[N]=key;
        swim(N);
    }

    /**
     * 移除最大元素
     */
    public Key delMax(){
        Key ans = nums[1];
        exch(1,N);
        N--;
        sink(1);
        return ans;
    }

    /**
     * 上浮元素
     */
    private void swim(int index){
        while(index>0) {
            int parent = index/2;
            if(less(parent,index))
                exch(parent,index);
            index=parent;
        }
    }

    /**
     * 下沉元素
     */
    private void sink(int index){
        while (index<N){
            int max = Math.max(index,left(index));
            max=Math.max(max,right(index));
            if(max==index) return;
            exch(max,index);
            index=max;
        }
    }

    /**
     *交换两个元素的位置
     */
    private void exch(int i,int j){
        Key temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    /**
     *判断第一个元素是否小于第二个元素
     */
    private boolean less(int i,int j){
        return nums[i].compareTo(nums[j])<0;
    }

    /**
     *返回左节点索引
     */
    private int left(int index){
        return index<<1;
    }

    /**
     *返回右节点索引
     */
    private int right(int index){
        return index<<1+1;
    }

    private void sink(Key[] nums,int index){
        if(index>nums.length) return;
        if(less(index,left(index))&&less(index,right(index))) return;
         
    }
}

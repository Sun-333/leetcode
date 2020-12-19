package sort;

import java.util.Arrays;

/**
 * @author yushansun
 * @title: QuickSort
 * @projectName leetcode
 * @description: 快速排序算法
 * @date 2020/12/133:16 下午
 */
public class QuickSort {
    public static int partition(Comparable[] arr, int lo,int hi){
        //定义切分元并根据切分元将数组进行划分
        int i = lo,j=hi+1;
        Comparable v= arr[0];//切分元
        while (true){
            while (SortUtil.less(v,arr[--j])) if(j==lo) break;
            while (SortUtil.less(arr[++i],v)) if(i==hi) break;
            if(i>=j) break;
            SortUtil.exch(arr,i,j);
        }
        SortUtil.exch(arr,lo,j);
        return j;
    }
    public static void quickSort(Comparable[] arr){
        quickSort(arr,0,arr.length-1);
    }
    public static void quickSort(Comparable[] arr,int i,int j){
        if (i>=j) return;
        int k = partition(arr,i,j);
        quickSort(arr,i,k-1);
        quickSort(arr,k+1,j);
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{10,3,11,7};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}

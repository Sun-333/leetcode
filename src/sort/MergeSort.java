package sort;

import java.util.Arrays;

/**
 * @author yushansun
 * @title: MergeSort
 * @projectName leetcode
 * @description: 归并排序
 * @date 2020/12/132:43 下午
 */
public class MergeSort {
    public static void mergeSort(Comparable[] arr){
        mergeSort(arr,0,arr.length-1);
    }
    public static void mergeSort(Comparable[] arr,int lo,int hi){
        if (lo>=hi) return;
        int mid = (lo+hi)>>1;
        mergeSort(arr,lo,mid);
        mergeSort(arr,mid+1,hi);
        merge(arr,lo,hi,mid);
    }

    public static void merge(Comparable[] arr,int lo,int hi,int mid){
        int i = lo,j = mid+1;
        Comparable[] temp = Arrays.copyOf(arr,arr.length);
        for (int k=lo;k<=hi;k++){
            if (i>mid) arr[k]=temp[j++];
            else if (j>hi)  arr[k]=temp[i++];
            else if (SortUtil.less(temp[i],temp[j])) arr[k] = temp[i++];
            else arr[k] = temp[j++];
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{10,6,11,4,3};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}


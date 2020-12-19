package sort;

import java.util.Arrays;

/**
 * @author yushansun
 * @title: InsertSort
 * @projectName leetcode
 * @description: 插入排序 对基本有序数组性能较好
 * @date 2020/12/132:14 下午
 */
public class InsertSort {
    public static void sort(Comparable[] arr){
        for (int i=1;i<arr.length;i++){
            for (int j=i;j>0&&SortUtil.less(arr[j],arr[j-1]);j--)
                SortUtil.exch(arr,j,j-1);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{10,3,5,11,8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

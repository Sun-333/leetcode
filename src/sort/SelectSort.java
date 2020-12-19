package sort;

import java.util.Arrays;

/**
 * @author yushansun
 * @title: SelectSort
 * @projectName leetcode
 * @description: 快速排序 有序无序数组排序时间效率一样
 * @date 2020/12/132:02 下午
 */
public class SelectSort {
    public static void  sort(Comparable[] arr){
        for (int i=0;i<arr.length;i++){
            int min = i;
            for(int j=i+1;j<arr.length;j++){
                min = (SortUtil.less(arr[j],arr[min])==true)? j:min;
            }
            SortUtil.exch(arr,i,min);
        }
    }

    public static void main(String[] args) {
        Integer[]  a= new Integer[]{10,2,5,8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}

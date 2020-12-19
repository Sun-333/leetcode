package sort;

/**
 * @author yushansun
 * @title: SortUtil
 * @projectName leetcode
 * @description: 排序工具类
 * @date 2020/12/131:58 下午
 */
public class SortUtil {
    /**
     * 比较第一个元素是否小于第二个元素
     * @param a
     * @param b
     * @return
     */
    public static boolean less(Comparable a,Comparable b){
        return a.compareTo(b)<0;
    }

    /**
     * 交换i，j 元素的下标
     * @param arr
     * @param i
     * @param j
     */
    public static void exch(Comparable[] arr,int i,int j){
        Comparable temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}

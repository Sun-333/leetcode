package search;

/**
 * @author yushansun
 * @title: BinarySearch
 * @projectName leetcode
 * @description: 二分查找
 * @date 2020/12/169:51 下午
 */
public class BinarySearch {
    public int searchTargetIndex(Comparable[] arr,Comparable key){
        int left=0,right=arr.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            int cmp = key.compareTo(arr[mid]);
            if (cmp==0) return mid;
            else if(cmp>0) right=mid-1;
            else if (cmp<0) left=mid+1;
        }
        return -1;
    }
    public int searchLessTargetIndex(Comparable[] arr,Comparable key){
        int left=0,right=arr.length;
        while (left<right){
            int mid  = left+(right-left)/2;
            int cmp = key.compareTo(arr[mid]);
            if (cmp==0) right=mid;
            else if(cmp<0) left=mid+1;
            else if (cmp>0) right=mid;
        }
        return left;
    }
    public int searchBigTargetIndex(Comparable[] arr,Comparable key){
        int left=0,right=arr.length;
        while (left<right){
            int mid  = left+(right-left)/2;
            int cmp = key.compareTo(arr[mid]);
            if (cmp==0) left=mid+1;
            else if(cmp<0) left=mid+1;
            else if (cmp>0) right=mid;
        }
        return left-1;
    }
}

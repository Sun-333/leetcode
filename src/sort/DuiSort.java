package sort;

import java.util.Arrays;

/**
 * @author yushansun
 * @title: DuiSort
 * @projectName leetcode
 * @description: TODO
 * @date 2020/10/141:04 下午
 */
public class DuiSort {
    /**
     * 堆排序
     * @param a
     */
    public static void sort(Comparable[] a){
        int N=a.length-1;
        //构建大顶堆
        for(int k=N/2;k>=1;k--)
            sink(a,k,N);
        //下沉排序
        while (N>1){
            exch(a,1,N--);
            sink(a,1,N);
        }
    }

    public static void sink(Comparable[] a,int start,int end){
       while (2*start<=end){
           int j=2*start;
           if(j<end&&less(a,j,j+1)) j++;
           if(!less(a,start,j)) break;
           exch(a,start,j);
           start=j;
       }

    }

    private static int left(int index){
        return index/2;
    }
    private static int right(int index){
        return index/2+1;
    }

    private static boolean less(Comparable[] a,int i,int j){
        return (a[i].compareTo(a[j])<0)? true:false;
    }

    public static void exch(Comparable[] a,int i,int j){
        Comparable temp = a[i];
        a[i]=a[j];
        a[j]=temp;
    }


    /**
     * 选择排序
     */
    public static void selectSort(Comparable[] a){
        int N=a.length;
        for (int i=0;i<N;i++){
            int minIndex;
            for (int j=i;j<N;j++){

            }
        }
    }

    /**
     *  插入排序
     */
     public static void insertSort(Comparable[] a){
         int N=a.length;
        for(int i=1;i<N;i++){
            for (int j=i;j>0&&less(a,j,j-1);j--){
                exch(a,j,j-1);
            }
        }
     }

    /**
     * 希尔排序
     */
    public static void xierSort(Comparable[] a){
        int N=a.length;
        int h=1;
        while (h<N/3) h=3*h+1;
        while (h>=1){
            //让数组变为h有序
            for(int i=h;i<N;i++){
                for (int j=i;j>=h&&less(a,j,j-h);j-=h)
                    exch(a,j,j-h);
            }
            h=h/3;
        }
    }

    /**
     *归并排序
     */
    private static Comparable[] aux;
    public static void guibingSort(Comparable[] a){
        aux=new Comparable[a.length];
        giubingSort(a,0,a.length-1);
    }
    public static void giubingSort(Comparable[] a,int lo,int hi){
        if(lo>=hi) return;
        int mid = (lo+hi)/2;
        giubingSort(a,lo,mid);
        giubingSort(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }

    private static void merge(Comparable[] a,int lo,int mid,int hight){
        int i=lo,j=mid+1;
        for (int k=lo;k<=hight;k++){
            aux[k]=a[k];
        }

        //归并
        for (int k=lo;k<=hight;k++){
            if(i>mid) a[k]=aux[j++];
            else if(j>hight) a[k]=aux[i++];
            else if(less(a,i,j)) a[k]=aux[i++];
            else a[k]=aux[j++];
        }
    }

    /**
     * 快速排序
     */
    public static void quickSort(Comparable[] a){
        quickSork(a,0,a.length-1);
    }

    private static void quickSork(Comparable[] a,int lo,int hi){
        //base case
        if(lo>=hi) return;
        int index= partition(a,lo,hi);
        quickSork(a,lo,index-1);
        quickSork(a,index+1,hi);
    }

    private static int partition(Comparable[] a,int lo,int hi){
        int i=lo,j=hi+1;
        //定义切分元
        Comparable v= a[lo];
        while (true){
            while (less(a,++i,lo)) if(i==hi) break;
            while (less(a,lo,--j)) if(j==lo) break;
            if(i>=j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{5,33,17,31,7};
        //DuiSort.sort(a);
        //insertSort(a);
        //xierSort(a);
        //guibingSort(a);
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }
}

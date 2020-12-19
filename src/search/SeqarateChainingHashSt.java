package search;

/**
 * @author yushansun
 * @title: SeqarateChainingHashSt
 * @projectName leetcode
 * @description: 通过拉链法实现hashTb
 * @date 2020/10/161:08 下午
 */
public class SeqarateChainingHashSt<Key extends Comparable<Key>,Value> {
    private int N;  //键值对总数
    private int M;  //散列表大小
    private SequentialSearchST<Key,Value>[] sts;

    public SeqarateChainingHashSt(int M){
        this.M=M;
        sts=new SequentialSearchST[M];
        for (int i=0;i<M;i++){
            sts[i]=new SequentialSearchST<>();
        }
    }

    /**
     * 哈希散列函数，将key散列为31位正数，再模M
     * @param key
     * @return
     */
    private int hash(Key key){
        return (key.hashCode()& 0x7fffffff)%M;
    }

    /**
     * 更具key查询值
     * @return
     */
    public Value get(Key key){
        return  sts[hash(key)].get(key);
    }

    /**
     * 插入元素
     * @param key
     * @param val
     */
    public void put(Key key,Value val){
        sts[hash(key)].put(key,val);
    }

    public void delete(Key key){
        sts[hash(key)].delete(key);
    }
}

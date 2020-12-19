package search;

/**
 * @author yushansun
 * @title: SequentialSearchST
 * @projectName leetcode
 * @description: 顺序查找
 * @date 2020/10/159:27 下午
 */
public class SequentialSearchST<Key,Value> {
    private Node tail;

    public SequentialSearchST() {
        this.tail = new Node();
    }

    private class Node{
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
        public Node(){

        }
    }

    /**
     * 查询指定key的值
     * @return
     */
    public Value get(Key key){
        for(Node x=tail.next;x!=null;x=x.next){
            if(key.equals(x.key))
                return x.val;   //命中
        }
        return null;            //未命中
    }

    /**
     *存在k-v数据
     */
    public void put(Key key,Value val){
        for (Node x=tail.next;x!=null;x=x.next){
            if(key.equals(x.key)){
                x.val=val;              //命中跟新
                break;
            }
        }
        Node newNode = new Node(key,val,tail.next);     //未命中创建
        tail.next = newNode;
    }

    public void  delete(Key key){
        for (Node x=tail;x.next!=null;x=x.next){
            if(x.next.equals(key))
                x.next=x.next.next;
        }
    }
}

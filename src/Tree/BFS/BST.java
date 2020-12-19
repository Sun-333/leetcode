package Tree.BFS;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yushansun
 * @title: BST
 * @projectName leetcode
 * @description: TODO
 * @date 2020/10/77:55 下午
 */
public class BST<Key extends Comparable<Key>,Value> {
    private class Node{
         private Key key;
         private Value val;
         private Node left,right;
         private int N;//指示当前子树的节点总数，用于快速查找指定排名节点，或取得节点的排名

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }

    Node root;//二叉树的根节点

    /**
     * 判断当前二叉树的节点总数
     * @return
     */
    public int size(){
        return size(root);
    }

    private int size(Node root){
        if(root==null) return 0;
        return root.N;
    }

    /**
     * 查询固定key的值
     * @param key
     * @return
     */
    public Value get(Key key){
        //如果key存在直接返回，不存在则返回空
        if (key==null) return null;
        return get(root,key);
    }

    private Value get(Node root,Key key){
        if(root==null) return null;
        int cmp = key.compareTo(root.key);
        if(cmp<0) return get(root.left,key);
        else if(cmp>0) return get(root.right,key);
        else return root.val;
    }

    /**
     * 添加k-v
     */
    public  void put(Key key,Value val){
        //若不存在则添加，存在则修改
    }

    public Node put(Node root,Key key,Value val){
        if(root==null) return new Node(key,val,1);
        int cmp = key.compareTo(root.key);
        if(cmp<0) root.left=put(root.left,key,val);
        else if (cmp>0) root.right=put(root.right,key,val);
        else root.val=val;
        //修正节点总数量
        root.N=size(root.left)+size(root.right)+1;
        return root;
    }

    /**
     * 查询二叉数中最大Key对应的值
     */
    public Value max(){
        return max(root);
    }

    public Value max(Node root){
        if (root==null) return null;
        if(root.right==null) return root.val;
        return max(root.right);
    }

    /**
     * 查询二叉树中对用的最小值
     */
    public Value min(){
        Node node =  min(root);
        if (node!=null) return node.val;
        return null;
    }

    public Node min(Node root){
        if (root==null) return null;
        if(root.left==null) return root;
        return min(root.left);
    }

    /**
     * 查询小于等于Key 的最大值
     */
    public Value floor(Key key){
        return floor(root,key).val;
    }
    public Node floor(Node node ,Key key){
        if(node==null) return null;
        int cmp=key.compareTo(root.key);
        if(cmp<0) return floor(node.left,key);
        else if(cmp>0){
            Node find = floor(node.right,key);
            if(find!=null) return find;
            return node;
        }else return node;
    }

    /**
     * 查询大于等于Key的最小值
     */
    public Value ceiling(Key key){
        return ceiling(root,key).val;
    }
    public Node ceiling(Node node ,Key key){
        if(node==null) return null;
        int cmp=key.compareTo(root.key);
        if(cmp>0) return floor(node.right,key);
        else if(cmp<0){
            Node find = floor(node.left,key);
            if(find!=null) return find;
            return node;
        }else return node;
    }

    /**
     * 查询指定排名的值
     */
    public Value select(int rank){
        Node ans = select(root,rank);
        if(ans==null) return null;
        return ans.val;
    }

    private Node select(Node node,int rank){
        if(node==null) return null;
        if(size(node.left)==rank) return node;
        else if(size(node.left)>rank) return select(node.left,rank);
        else return select(node.right,rank-size(node.left)-1);
    }


    /**
     * 返回指定节点的排名
     */
    public int rank(Key rank){
        return rank(root,rank);
    }

    private int rank(Node root,Key key){
        if(root==null) return 0;
        int cmp=key.compareTo(root.key);
        if(cmp<0) return rank(root.left,key);
        else if (cmp>0) return rank(root.right,key)+size(root.left)+1;
        else  return  size(root.left);
    }

    /**
     *删除最小节点
     */
    public Key deleteMin(){
        Node ans = deleteMin(root);
        if (ans!=null) return ans.key;
        return null;
    }

    private Node deleteMin(Node node){
        if(node==null) return null;
        else if(node.left==null) return node.right;
        else {
            node.left=deleteMin(node.left);
            node.N=Math.max(size(node.left),size(node.right))+1;
        }
        return node;
    }

    /**
     * 删除任意节点
     */
    public Value delete(Key key){
       return delete(root,key).val;
    }

    private Node delete(Node node,Key key){
        if(node==null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp<0) node.left=delete(node.left,key);
        else if(cmp>0) node.right=delete(node.right,key);
        else {
            if (node.left==null) return node.right;
            if(node.right==null) return node.left;
            Node x=node;
            node=min(root.right);
            node.left=x.left;
            node.right=x.right;
            delete(node.right,key);
        }
        node.N=Math.max(size(node.left),size(node.right))+1;
        return node;
    }
    /**
     * 删除最大节点
     */
    public Value deleteMax(){
        return deleteMax(root).val;
    }

    private Node deleteMax(Node node){
        if(node==null) return null;
        if (node.right!=null) node.right=deleteMin(node.right);
        else return root.left;
        node.N=Math.max(size(node.left),size(node.right))+1;
        return node;
    }

    public Iterable<Key> keys(Key lo,Key hi){
        Queue<Key> queue = new LinkedList<>();
        keys(root,queue,lo,hi);
        return queue;
    }

    private void keys(Node node, Queue<Key> queue,Key lo,Key hi){
        if(node==null) return;
        int cmplo=lo.compareTo(root.key);
        int cmphi= hi.compareTo(root.key);
        if(cmplo<0) keys(node.left,queue,lo,hi);
        if(cmplo<=0&&cmphi>=0) queue.add(root.key);
        if(cmphi>0) keys(node.right,queue,lo,hi);
    }

}

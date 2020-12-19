package Tree;


/**
 * @author yushansun
 * @title: RedBlackTree
 * @projectName leetcode
 * @description: TODO
 * @date 2020/10/87:45 下午
 */
public class RedBlackTree<Key extends Comparable<Key>,Value> {
    private static boolean RED=true;
    private static boolean BLACK=false;
    private Node root;
    private class Node{
        private Key key;
        private Value val;
        private Node left,right;
        private int N;
        private boolean color;

        public Node(Key key, Value val, int n,boolean color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color=color;
        }
    }

    private boolean isRed(Node x){
        if(x==null) return false;
        return x.color==RED;
    }

    private int size(Node root){
        return (root==null)? 0:root.N;
    }

    /**
     * 在左旋后重制链接
     * @return
     */
    private Node leftRotate(Node y){
        Node x=y.right;
        x.left=y;
        y.right=x.left;
        x.color=y.color;
        y.color=RED;
        x.N=y.N;
        y.N=Math.max(size(y.left),size(y.right))+1;
        return x;
    }

    private Node rightRotate(Node y){
        Node x=y.left;
        y.left=x.right;
        x.right=y;
        x.color=y.color;
        y.color=RED;
        x.N=y.N;
        y.N=Math.max(size(y.left),size(y.right));
        return x;
    }
    void flipColor(Node node){
        node.color=RED;
        node.left.color=BLACK;
        node.right.color=BLACK;
    }

    public void put(Key key,Value value){
        put(root,key,value);
    }

    public Node put(Node node,Key key,Value val){
        //标准插入操作，并且插入节点用红色链接
        if(node==null) return new Node(key,val,1,RED);
        int cmp = key.compareTo(node.key);
        if(cmp<0) root.left=put(root.left,key,val);
        else if(cmp>0) root.right=put(root.right,key,val);
        else root.val=val;

        if(isRed(root.right)&&!isRed(root.left)) root=leftRotate(root);
        if(isRed(root.left)&&isRed(root.left.left)) root=rightRotate(root);
        if (isRed(root.left)&&isRed(root.right)) flipColor(root);

        root.N=Math.max(size(root.left),size(root.right));
        return root;
    }
}

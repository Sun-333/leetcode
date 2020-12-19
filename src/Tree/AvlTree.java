package Tree;

/**
 * @author yushansun
 * @title: AvlTree
 * @projectName leetcode
 * @description: TODO
 * @date 2020/10/71:54 下午
 */
public class AvlTree<E extends Comparable<E>> {
    private class Node{
        E val;
        Node left;
        Node right;
        int height;

        public Node(E val) {
            this.val = val;
        }
    }

    private Node root;
    private int size;

    public AvlTree(){
        root=null;
        size=0;
    }

    /**
     * 计算某一节点的高度
     */
    private int getHeight(Node node){
        if(root==null) return 0;
        return node.height;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    /**
     * 获取平衡因子
     */
    private int getBalanceFactory(Node node){
        if(node==null) return 0;
        return getHeight(node.left)-getHeight(node.right);
    }

    private boolean isBalance(Node node){
        if(node==null) return true;

        if(Math.abs(getBalanceFactory(node))>1) return false;

        return isBalance(node.left)&&isBalance(node.right);
    }

    /**
     *判断树是否平衡
     */
    public boolean isBalance(){
        return isBalance(root);
    }

    /**
     * LL 右旋转
     */
    private Node rightRotate(Node y){
        Node x=y.left;
        Node t3=x.right;
        x.right=y;
        y.left=t3;
        y.height=Math.max(getHeight(y.left),getHeight(y.right))+1;
        x.height=Math.max(getHeight(x.left),getHeight(x.right))+1;
        return x;
    }

    /**
     * RR 左旋
     */
    private Node leftRotate(Node y){
        Node x=y.left;
        Node t3=x.right;
        x.left=y;
        y.right=t3;
        y.height=Math.max(getHeight(y.left),getHeight(y.right))+1;
        x.height=Math.max(getHeight(x.left),getHeight(x.right))+1;
        return x;
    }

    /**
     * LR 先左旋转换成LL，再进行又旋
     */
    private Node leftRightRotate(Node y){
        Node x=y.left;
        y.left = leftRotate(x);
        return rightRotate(y);
    }

    /**
     * RL 先右旋转转化成RR，再左旋
     */
    private Node rightLeftRotate(Node y){
        Node x= y.right;
        y.right=rightRotate(x);
        return leftRotate(x);
    }

    /**
     * 添加节点
     */
    public void add(E e){
        add(root,e);
    }

    private Node add(Node root,E e){
        if(root==null){
            size++;
            return new Node(e);
        }
        /**
         * 插入节点
         */
        if(root.val.compareTo(e)<0) root.left=add(root.left,e);
        else if(root.val.compareTo(e)>0) root.right=add(root.right,e);
        /**
         * 更新高度
         */
        root.height=Math.max(getHeight(root.left),getHeight(root.right))+1;
        /**
         * 调整平衡
         */
        int balance = getBalanceFactory(root);

        if(Math.abs(balance)<1) return root;
        else{
            //LL
            if(balance>1&&getBalanceFactory(root.left)>0)
              return   rightRotate(root);
            //RR
            else if(balance<-1&&getBalanceFactory(root.right)<0)
                return leftRotate(root);
            //LR
            else if(balance>1&&getBalanceFactory(root.right)<0)
                return rightLeftRotate(root);
            //RL
            else
                return leftRotate(root);
        }
    }
}

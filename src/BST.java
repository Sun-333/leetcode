/*
public class BST {
    //判断BST的有效性
    public boolean isValidBST(TreedNode root){
        return isValidBST(root,null,null);
    }
    private boolean isValidBST(TreedNode root,TreedNode min ,TreedNode max){
        //root 需要做的事
        if(root==null) return true;
        if (min!=null&&min.val>root.val) return false;
        if (max!=null&&max.val<root.val) return false;

        //递归
        return isValidBST(root.left,min,root)&&isValidBST(root.right,root,max);
    }
    //查询某一元素是否已经存在
    public boolean isInBST(TreedNode root,int target){
        //root 做的事
        if (root.val==target)
            return true;
        if (root==null)
            return false;
        //递归（根据Bst的特殊性质不需要递归所有节点）
        if (target<root.val)
            return isInBST(root.left,target);
        else
            return isInBST(root.right,target);
    }
    //在BST中插入一个元素
    public TreedNode insert(TreedNode root,int e){
        //找到空位插入节点
        if(root==null) return new TreedNode(e);
        //递归查找空位
        if (e<root.val) root.left=insert(root.left,e);
        if (e>root.val) root.right=insert(root.right,e);
        return root;
    }
    //在BST中删除一个元素
    public TreedNode delElem(TreedNode root,int e){
        if (root.val==e){
            //为叶节点
            if (root.left==null) return root.right;
            if (root.right==null) return root.left;
            //左右节点都存在，选择右子树最小的
            TreedNode min =getMin(root);
            root.val=min.val;
            root.right = delElem(root.right,min.val);
        }
        if (e<root.val) root.left=delElem(root.left,e);
        if (e>root.val) root.right=delElem(root.right,e);
    }
    private TreedNode getMin(TreedNode root){
        while (root.left!=null) root=root.left;
        return root;
    }
}

class TreedNode{
    int val;
    TreedNode left,right;
    public TreedNode(int val){this.val=val;}

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreedNode getLeft() {
        return left;
    }

    public void setLeft(TreedNode left) {
        this.left = left;
    }

    public TreedNode getRight() {
        return right;
    }

    public void setRight(TreedNode right) {
        this.right = right;
    }
}
*/

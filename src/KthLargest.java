public class KthLargest {
    int index;
    TreeNode root;
    int count;
    public KthLargest(int k, int[] nums) {

        for(int i=0;i<nums.length;i++){
            createSearchTree(root, nums[i]);
        }
        index=k;
    }

    private void createSearchTree(TreeNode root,int number){
        if(root==null){
            root=new TreeNode(number);
            return;
        }
        if(number==root.val) root.num++;
        else if(number<root.val&&root.left==null) root.left=new TreeNode(number);
        else if(number<root.val&&root.left!=null)createSearchTree(root.left,number);

        else if(number>root.val&&root.right==null) root.right=new TreeNode(number);
        else createSearchTree(root.right,number);
        root.count++;
    }

    public int add(int val) {
        createSearchTree(root, val);
        return search(root, index);
    }

    private int search(TreeNode root,int k){
        if(root==null) return -1;
        int subRight=(root.right==null)? 0:root.right.count;
        if(k-subRight>0&&(k-subRight-root.num)<=0) return root.val;
        else if(k-subRight>0) return search(root.left, k-subRight-root.num);
        else return search(root.right, k);
    }
    class TreeNode{
        int val;
        int count;
        int num;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val=val;
            this.count=1;
            this.num=1;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", count=" + count +
                    ", num=" + num +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public void DFS(TreeNode root){
        if(root==null) return;
        DFS(root.left);
        System.out.println(root.toString());
        DFS(root.right);
    }

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3,new int[]{4,5,8,2});
        kthLargest.add(5);
        kthLargest.DFS(kthLargest.root);
    }
}
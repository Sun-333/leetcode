
   class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;

      TreeNode(int x) {
          val = x;
      }
  }
 public class Solution {
     int ans =-10000000;
    public int maxPathSum(TreeNode root) {
       oneSizeMax(root);
       return ans;
    }
    public int oneSizeMax(TreeNode root){
        if(root==null) return 0;
       int left = Math.max(0,oneSizeMax(root.left));
       int right=Math.max(0,oneSizeMax(root.right));
       ans=Math.max(ans,left+right+root.val);
       return Math.max(left,right)+root.val;
    }
}
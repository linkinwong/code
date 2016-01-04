import java.util.*;
/**
 *  * Definition for a binary tree node.
 *   * public class TreeNode {
 *    *     int val;
 *     *     TreeNode left;
 *      *     TreeNode right;
 *       *     TreeNode(int x) { val = x; }
 *        * }
 *         */
public class Solution {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x;}
	}

	TreeNode root; 
	public Solution(){
		//root = new TreeNode(3);
		//root.left = new TreeNode(9);
		//root.right = new TreeNode(20);
		//root.right.left = new TreeNode(15);
		//root.right.right = new TreeNode(7);
		root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(1);
	}
	public TreeNode getRoot(){ 
		return root;
	}

	public boolean hasPathSum(TreeNode root, int sum){
		if(root == null ) return false;
		if(root.left == null && root.right == null && sum == root.val) return true;
		else if(root.left == null && root.right == null && sum != root.val) return false;
		return hasPathSum(root.left, sum - root.val)|| hasPathSum(root.right, sum - root.val);
	} 	

    	public List<List<Integer>> levelOrder_v1(TreeNode root) {
        	List<List<Integer>> result = new ArrayList<List<Integer>>();
        	if(root == null) return result;
        	Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while( q.size()>0) {
			List<Integer> line = new ArrayList<>();
			int size = q.size();
			for(int i=0; i<size; i++) {
				TreeNode node = q.poll();
				line.add(node.val);
				if(node.left != null) q.add(node.left);
				if(node.right != null) q.add(node.right);
			}
			result.add(line);
			for (Integer t : line)
				System.out.print(t+"  ");
			System.out.println();
		}
		return result;
    	}
    	public List<List<Integer>> levelOrder(TreeNode root) {
		// recursion 
		List<List<Integer>> ll = new ArrayList<>();
		if(root == null) return ll;
		traversal(ll, root, 1);
		for (List<Integer> l : ll){
			for(Integer x : l){
				System.out.print(x+" ");
			}
			System.out.println();
		}
		return ll;
    	}
	public void traversal(List<List<Integer>> ll, TreeNode node, int depth) {
		if (node == null) return;
		if (ll.size() == depth -1) {
			List<Integer> l = new ArrayList<>();
			l.add(node.val);
			ll.add(l);
		}
		else if(ll.size() >= depth){
			ll.get(depth-1).add(node.val);
		}
		traversal(ll, node.left, depth+1);
		traversal(ll, node.right, depth+1);
	}
}

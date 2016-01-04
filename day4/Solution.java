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
	int[][] edges;
	int[] nums;
	public Solution(){
		//// The first tree
		//root = new TreeNode(3);
		//root.left = new TreeNode(9);
		//root.right = new TreeNode(20);
		//root.right.left = new TreeNode(15);
		//root.right.right = new TreeNode(7);
		
		//// The second tree
		/*root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(1);
		*/
		edges = new int[][] {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
		//nums = new int[4];
		nums = new int[] {0, 1, 2, 4,5,7};
	}

	public TreeNode getRoot(){ 
		return root;
	}
	public int[][]  getEdges(){ 
		return edges;
	}
	public int[]  getArray(){ 
		return nums;
	}

    	public List<String> summaryRanges(int[] nums) {


                if(nums == null) return null;
                List<Integer> node = new ArrayList<>();
                List<String> result = new ArrayList<>();
                if(nums.length==0) return result;
                node.add(0);
                int i=0, j=1, k=1;
                for(; j<nums.length; j++){
                        if(nums[j] == nums[i]+k){k++; }
                        else{
                                i = j;
                                node.add(i);
                                k=1;
                        }
                }
                node.add(nums.length);
                for(i=0; i<  node.size()-1; i++) {
                        if ( node.get(i) == node.get(i+1)-1 ) result.add( Integer.toString(nums[node.get(i)] )  );
                        else{
                                result.add( Integer.toString( nums[node.get(i)] ) + "->" + Integer.toString(nums[node.get(i+1)-1] )  );
                        }
                }

                for(String s : result) System.out.println(s);
                return result;
    	}
    	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		if(n==1) return Collections.singletonList(0);
		List<Set<Integer>> adj = new ArrayList<>(n); 
		for(int i=0; i<n; ++i) adj.add(new HashSet<Integer>() );
		List<Integer> leaves = new ArrayList<Integer>();
		for(int[] edge : edges){
			adj.get(edge[0]).add(edge[1]);       		
			adj.get(edge[1]).add(edge[0]);       		
		}
		for(int i=0; i<n; ++i){
			if(adj.get(i).size()==1 ){ 
				leaves.add(i);
			}
		}
		while(n >2){
			n = n - leaves.size();
			List<Integer> new_leaves = new ArrayList<>();
			for(int leave : leaves){
				int j = adj.get(leave).iterator().next();
				adj.get(j).remove(leave);
				if( adj.get(j).size() == 1){ 
					new_leaves.add(j);
				}
			}
			leaves = new_leaves;
		}	
		System.out.print(leaves.size());
		return leaves;
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

    	public int minDepth(TreeNode root) {
        	List<Integer> level = new ArrayList<>();
		if(root == null) level.add(0);
		traversal_min(level, root, 1);
		int min = Collections.min(level);
		System.out.println(min);
		return min;
    	}

	public void traversal_min(List<Integer> l_depth, TreeNode root, int depth){
		if (root == null) return;
		if(root.left == null && root.right == null){
			l_depth.add(depth);
			return;
		} else{
			traversal_min(l_depth, root.left, depth+1);
			traversal_min(l_depth, root.right, depth+1);
		}
	}
}

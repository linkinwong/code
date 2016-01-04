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
	HashSet<Integer> visited;
	public Solution(){
		edges = new int[][] {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
		nums = new int[] {0, 1, 2, 4,5,7};
	}

	public class SN implements Comparable<SN> {
		String ori;
		String sort;
		int ind;
		public SN(String a, String b){
			ori = a;
			sort = b;
		}
		public int compareTo(SN s){
			return (this.sort).compareTo(s.sort);
		}
	}

    	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new ArrayList<List<String>>();
        	if(strs.length == 0) return null;
		List<SN> ls = new ArrayList<SN>();
		for(String s : strs){
			char[] s_array = s.toCharArray();
			Arrays.sort(s_array);
			String sorted = new String(s_array);
			SN node = new SN(s, sorted);
			ls.add(node);
		}
		Collections.sort(ls);
		/*for (SN dot : ls){
			System.out.println("sorted list: " + dot.sort);
		} */
		
		SN pivot;
		List<String> lstr = new ArrayList<String>();
		if(ls.size()>0) pivot = ls.get(0);
		else{
			List<List<String>> res = new ArrayList<List<String>>();
			List<String> templ = new ArrayList<String>();
			templ.add ("");
			res.add(templ);
			return res;
		}	
		for( SN dot : ls){
			//System.out.println("pivot node:  " + dot.sort + "  vs  " + pivot.sort);
			if( (pivot.sort).equals(dot.sort)){
				lstr.add(dot.ori);
				//System.out.println("same node:  " + dot.sort);
				pivot = dot;
			}else{
				result.add(lstr);
				lstr = new ArrayList<String>();
				//lstr.clear();
				lstr.add(dot.ori);
				//System.out.println("different node:  " + dot.sort);
				pivot = dot;
			}
		}
		/*for (String s : lstr){
			System.out.println("sorted list: " + s);
		}*/
		result.add(lstr);
		List<List<String>> out = new ArrayList<List<String>>();
		for(List<String>  templ : result){
			Collections.sort(templ);
			out.add(templ);
		}
		return out;	
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

	public String addBinary(String a, String b) {
        	if(a.length() == 0) return b;	
        	if(b.length() == 0) return a;	
		int len_a = a.length();
		int len_b = b.length();
		int min = len_a > len_b ? len_b : len_a;
		int max = len_a < len_b ? len_b : len_a;
		String c = "";
		int j = len_b-1, sum=0;
		int carry = 0;
		for(int i = len_a-1; i>=len_a - max ; i--){
			if (i<0) {
				sum = '0' ^ b.charAt(j) ^ carry;
				carry = ( (b.charAt(j) - '0') + carry) /2;
			}else if(j<0) {
				sum = '0' ^ a.charAt(i) ^ carry;
				carry = ( (a.charAt(i) - '0') + carry) /2;
			}else {
				sum = a.charAt(i) ^ b.charAt(j) ^ carry;
				carry = ( (a.charAt(i) - '0') + (b.charAt(j)-'0') + carry) /2;
			}
			j--;
			if (sum==1) 	c = "1" + c; 
			else 		c = "0" + c;
		}
		if (carry==1) 	c = "1" + c;
		return c;
   	}
    	public boolean isInterleave(String s1, String s2, String s3) {
        	//HashMap<Character, Integer> = new HashMap();
       		visited = new HashSet<Integer>();
		if (s3.length()!=s1.length() + s2.length() ) return false;
		return isInterleaveRecur(s1,0,s2,0,s3,0); 
    	}

	public boolean isInterleaveRecur(String s1,int i1,  String s2, int i2, String s3, int i3){
		int hash = i1* s3.length() + i2;
		if(visited.contains(hash)) return false;
		if( s2.length() == i2 ) return s1.substring(i1).equals(s3.substring(i3));
		if( s1.length() == i1 ) return s2.substring(i2).equals(s3.substring(i3));
		if(s1.charAt(i1) == s3.charAt(i3) && isInterleaveRecur(s1, i1+1,s2,i2,s3,i3+1) || 
		   s2.charAt(i2) == s3.charAt(i3) && isInterleaveRecur(s1, i1,s2,i2+1,s3,i3+1) )
			return true;
		visited.add(hash);
		return false;
	}


    	public int romanToInt(String s) {
        	if(s == null || s.length() == 0) return 0;
		HashMap<Character, Integer> map = new HashMap<Character,Integer>();
		int len = s.length();
		map.put('I',  1);
		map.put('V' , 5);
		map.put('X', 10);
		map.put('L' , 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M',1000);
		int result =map.get( s.charAt(len-1) );
		int pivot = result;
		for(int i = len-2; i>=0; i--){
			int curr = map.get(s.charAt(i));
			if(curr>=pivot) result+= curr;
			else	result-=curr;
			pivot = curr;
		}
		return result;
    	}
    	/*public int compareVersion(String version1, String version2) {
        	List<String> ver1 = new ArrayList(); 
		for(String s1 : version1.split("\\.")) ver1.add(s1);
        	List<String> ver2 = new ArrayList(); 
		for(String s1 : version2.split("\\.") ) ver2.add(s1);
		System.out.print(ver1.size() +"  " + ver2.size());
		//if(ver1.length == 0) { ver1 = new String[1]; ver1[0] = version1;}
		//if(ver2.length == 0) { ver2 = new String[1]; ver2[0] = version2;}
		int i = 0;
		while(i<ver1.size() && i<ver2.size() ){
			int n1 = Integer.parseInt( ver1.get(i) );
			int n2 = Integer.parseInt( ver2.get(i) );
			i++;
			if(n1==n2) continue; 
			else if(n1>n2) return 1;
			else	return -1;
		}
		if( ver2.size() ==ver1.size() )  return 0;
		else if(ver1.size() > ver2.size()) {
			int sum = 0;
			for(int j=i; j< ver1.size(); j++){
				sum += Integer.parseInt(ver1.get(j));
			}
			if (sum == 0) 	return 0;
			else	return 1;
		}
		else{
			int sum = 0;
			for(int j=i; j< ver2.size(); j++){
				sum += Integer.parseInt(ver2.get(j));
				System.out.print("Right" + sum);
				
			}
			if (sum == 0) 	return 0;
			else	return -1;
		}
	 
    	}*/

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

import java.util.*;

public class test{
	//NumMatrix num_matrix;
	Solution sl;
	public test(){
		//int[][] input = {{2,-3,6}};
		//num_matrix = new NumMatrix( input);
		//int sq = num_matrix.sumRegion(0,1,0,1);
		//System.out.print(sq);
		sl = new Solution();
		//sl.create();
		//sl.reverseList(sl.getList());
		//sl.print(sl.getList());
		
		/*Scanner in = new Scanner(System.in);
		int sum = in.nextInt();
		while(sum != -1){
			if( sl.hasPathSum(sl.getRoot(),sum )){
				System.out.print(sum+ " has the path");
			} else  System.out.print(sum+ " doesn't have the path");
			sum = in.nextInt();
		}*/
		//sl.findMinHeightTrees(6, sl.getEdges());
		sl.summaryRanges(sl.getArray());
		
	}
	public static void main(String[] args){
		test t = new test();
	}
}


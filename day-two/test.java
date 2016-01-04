public class test{
	//NumMatrix num_matrix;
	Solution sl;
	public test(){
		//int[][] input = {{2,-3,6}};
		//num_matrix = new NumMatrix( input);
		//int sq = num_matrix.sumRegion(0,1,0,1);
		//System.out.print(sq);
		sl = new Solution();
		sl.create();
		sl.reverseList(sl.getList());
		sl.print(sl.getList());
	}

	public static void main(String[] args){
		test t = new test();
	}
}


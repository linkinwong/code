import java.util.*;

public class test{
	//NumMatrix num_matrix;
	Solution sl;
	public test(){
		//String[] strs = new String[2];
		//String[] str = new String[0];
		//strs[0] = "";
		//String str =  "w039459438010";
		//String str1 =  "4594380";
		int[] nums = {1,2,3,4,5,6};
			
		sl = new Solution();
		//sl.countAndSay(7) ;
		sl.rotate(nums, 2) ;
	}
	public static void main(String[] args){
		test t = new test();
	}
}


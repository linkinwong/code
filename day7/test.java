import java.util.*;

public class test{
	//NumMatrix num_matrix;
	Solution sl;
	public test(){
		sl = new Solution();
		String[] strs = new String[2];
		String[] str = new String[0];
		//strs[0] = "PAYPALISHIRING";
		strs[0] = "";
		strs[1] = "";
		//strs[1] = "PAYPAX";
		
		sl.longestCommonPrefix(strs) ;
	}
	public static void main(String[] args){
		test t = new test();
	}
}


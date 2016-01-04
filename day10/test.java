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
		int[] num1 = {1,2,3,4,5,6, 4};
		int[] num2 = {1,2,3,4,4,6, 4, 7, 8};
		int[] num3 = {1};
			
		sl = new Solution();
		//sl.generate(2) ;
		sl.generate(19) ;
		//System.out.println( sl.removeElement(num2, 1) ) ;
		//System.out.println( sl.removeElement(num3, 5) ) ;
	}
	public static void main(String[] args){
		test t = new test();
	}
}


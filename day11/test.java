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
		int[] num1 = {4,22,11,4,45,-10, 11};
		int[] num2 = {0,1,0,3,12};
		int[] num3 = {1};
			
		sl = new Solution();
		//sl.generate(2) ;
		//sl.containsNearbyDuplicate(num1, 3) ;
		//System.out.println( sl.containsNearbyAlmostDuplicate(num1, 2, 5) ) ;
		System.out.println( sl.containsNearbyAlmostDuplicate(num1, 2, 7) ) ;
		System.out.println( sl.containsNearbyAlmostDuplicate(num1, 3, 1) ) ;
		//System.out.println( sl.removeElement(num3, 5) ) ;
	}
	public static void main(String[] args){
		test t = new test();
	}
}


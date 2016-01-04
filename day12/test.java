import java.util.*;

public class test{
	//NumMatrix num_matrix;
	Solution sl;
	public test(){
		//String str1 =  "4594380";
		int[] num1 = {-10,0, 4,7,0,17, 22,45, 0, 0 , 0, 0, 0};
		int[] num2 = {0,3,7,12,38};
		int[] num3 = {1};
			
		sl = new Solution();
		//sl.majorityElement(num1);
		//sl.merge(num1, 7, num2, 5) ;
		System.out.println( sl.majorityElement(num1) ) ;
		//System.out.println( sl.containsNearbyAlmostDuplicate(num1, 2, 7) ) ;
	}
	public static void main(String[] args){
		test t = new test();
	}
}


import java.util.*;

public class test{

	Solution sl;

	public test(){
		int[] num1 = {-10,0, 4,7,0,17, 22,45, 0, 0 , 0, 0, 0};
		int[] num2 = {0,3,7,12,38};
		int[] num3 = {1};
			
		sl = new Solution();
    sl.reverseList_recursion(sl.getListHead());
	}
	public static void main(String[] args){
		test t = new test();
	}
}


import java.util.*;

public class test{
	//NumMatrix num_matrix;
	Solution sl;
	public test(){
		sl = new Solution();
		String[] strs =   {"eat", "tea" , "tan", "ate", "nat", "bat"};
		sl.groupAnagrams(strs);
	}
	public static void main(String[] args){
		test t = new test();
	}
}


import java.util.*;

public class Solution {
  public class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x;}
  }
  public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
  }

  Queue<Integer> q, q2;
  Stack<Integer> input, output;
  TreeNode root; 
  TreeNode root1; 
  TreeNode root2; 
  int[][] edges;
  int[] nums;
  ListNode head;
  HashSet<Integer> visited;
  ListNode stack;
  int min;

  public Solution(){
    input = new Stack<Integer>();
    output = new Stack<Integer>();
    TreeNode p = new TreeNode(1);   root = p;
    p = new TreeNode(2);   root.left = p;
    p = new TreeNode(3);   root.right = p;
    p = new TreeNode(5);   root.left.right = p;
    root.left.left = null;   root.right.left = null;
    root.right.right = null;
    root.left.right.right = null;
    root.left.right.left = null;
  }

    public static void main(String[] args){
        Solution sl = new Solution();
        List<String> out =  sl.binaryTreePaths(sl.root);
        //System.out.println(sl.wordPattern("abba","dog cat cat dog"));
        System.out.print(out);
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast=head, slow=head;
        ListNode third=head, fourth=head;
        boolean  is_circle = false;
        while(fast!=null && fast.next!=null){
            if(fast==slow){  
                is_circle = true;
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        if (is_circle == false) return null;
        slow = slow.next;
        third = third.next;
        while(slow != fast){
            slow = slow.next;
            third = third.next;
        }
        while(fourth != third){
            fourth = fourth.next;
            third = third.next;
        }
        return fourth;
    }


    public String removeDuplicateLetters(String s) {
//        char[] ss = s.toCharArray();
//        int[] out = new char[26];
//        Stack<Integer> [] st = new Stack<Integer>[26];
//        for(int i=0; i< ss.length; i++){
//            if(out[ss[i] - 'a'] == 0){
//                out[ss[i] - 'a'] == i+1;
//                st[ss[i] - 'a'].push(i);
//            }else {
//                st[ss[i] - 'a'].push(i);
//            }
//        }
//
//        int num_no0 = 0;
//        for(int j=0; j<26; j++){
//            if( out[j] !=0) num_no0++;
//        }
//        int[] abc = new abc[num_no0];
//        int i=0;
//        for(int j=0; j<26; j++){
//            if( out[j] !=0){ 
//                abc[i] = j;
//                i++;
//            }
//        }
//
//        int[] ans = new int[num_no0];
//
//        for(int i=0; i<num_no0; i++){
//            int min_i = Integer.MAX_VALUE;
//            for(int j=0; j<num_no0; j++)
//            {
//                if( out[ abc[i] ] -1 > st[ abc[j] ].peek()  ){
//                    
//                }
//            }

    return "OK";

    }

    public int nthSuperUglyNumber(int n, int[] primes){
        int len = primes.length;
        if(primes[0] == 1){
            len = len -1;
        }
        int[] p = new int[len];
        if(primes[0] !=1){
            p = primes;
        }else{
            for(int i=0; i< len; i++){
                p[i] = primes[i+1];
            }
        }
        if(n ==1) return n; 
        int[] ind = new int[len];
        int[] ug = new int[n+1];
        ug[1] = 1;
        for(int j=0; j<len; j++){
            ind[j] = 1;
        }
        
        for(int i = 2; i<=n; i++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j< len; j++){
                if(min> ug[ind[j]] * p[j] ) min = ug[ind[j]] * p[j];
            }
            for(int j = 0; j< len; j++){
                if(min == ug[ind[j]] * p[j] ) ind[j]++;
            }
        }
       return ug[n]; 

    }


    public int nthUglyNumber(int n) {
        if (n<3)   return n;
        int[] ugly = new int[n+1];
        ugly[1] = 1; ugly[2] = 2;
        int i2 = 2, i3=1, i5=1;
        for(int i = 3; i<=n; i++){
            int temp = Math.min( ugly[i2]*2, ugly[i3] *3   );
            ugly[i] = Math.min(temp, ugly[i5]*5 );
            if(ugly[i] == ugly[i2] *2)  i2++;
            if(ugly[i] == ugly[i3] *3)  i3++;
            if(ugly[i] == ugly[i5] *5)  i5++;
        }
        return ugly[n];
    }


    public List<String> binaryTreePaths(TreeNode root) {
        List<String> out = new ArrayList<String>();
        if(root == null)   return out;
        String path = ""  ;
        boolean is_root= true;
        binaryTreePaths(root, out,path, is_root );
        return out;
    }
    public void binaryTreePaths(TreeNode p , List<String> ls, String path, boolean is_root ){
        if(p==null) { 
            return;
        }
        if(p.left==null && p.right ==null){
            if(is_root){ 
                path = "" + p.val;
                is_root = false;
            }else path = path + "->" + p.val;
            ls.add(new String(path));
            return; 
        }
        if(is_root){ 
            path = "" + p.val;
            is_root = false;
        }
        else{
            path = path + "->" + p.val;
        }
        binaryTreePaths(p.left, ls, path, is_root);
        binaryTreePaths(p.right, ls, path, is_root);
    }
        

    public boolean wordPattern(String pattern, String str) {
        HashMap<String, Integer> word_m = new HashMap<String, Integer>();
        char[] pat = pattern.toCharArray();
        int[] pat_dic = new int[256];
        String[] parts =  str.split(" ");
        for(int i=0; i<pat.length; i++){
            if(pat_dic[pat[i]] == 0){
                pat_dic[pat[i]] = i+1;
                if(word_m.containsKey(parts[i]))    return false;
                else    word_m.put(parts[i], i+1);
            }
            else{
                //if(!word_m.containsKey(parts[i])
                if(word_m.get(parts[i]) != pat_dic[pat[i]] )    return false;
            }
        }
        return true;
    }


   /* public static void main(String[] args){
        Solution sl = new Solution();
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int secret = 0;
        for(int i=0; i< 4;i++){
            secret = secret*10 +  (rand.nextInt(9)+1);
        }
        String secret_str = ""+secret;
        int num = 9999;
        while(num != -1){
            num = scan.nextInt();
            scan.nextLine();
            String guess = ""+num;
            String result = sl.getHint(secret_str,guess);
            if(secret_str.equals(guess)){   
                System.out.println("Bingo! you are really smart! Bye bye");
                return;
            }else System.out.println("You are near there! the gap is -->  " + result);
        }  
    } */

    public String getHint(String secret, String guess){

        int len = secret.length();
        if(len == 0)    return "0A0B";
        int[] ms = new int[256];
        int[] mg = new int[256];
        int bull=0, cow=0;
        for(int i=0; i<len; i++){
            char cs = secret.charAt(i);
            char cg = guess.charAt(i);
            //if(ms[cs]==0)  ms[cs] = ;
            if(cg == cs)    bull++;
            else{
                ms[cs]++;
                mg[cg]++;
            }
        }
        for(int i=0; i<256;i++){
            cow += Math.min(ms[i],mg[i]);
        }

        String out = bull+"A"+cow+"B";
        return out;
    }


    public boolean isValidSudoku(char[][] board){
        int len = board.length;
        if(len!=9 || len != board[0].length) return false;
        for(int i = 0; i<len; i++){
            int[] m = new int[256];
            for(int j=0; j<len;j++){
                if(board[i][j]== '.' )  continue;
                if( m[ board[i][j] ] !=0) return false;
                else    m[board[i][j]] = (int) board[i][j]; 
            }
        }
        for(int i = 0; i<len; i++){
            int[] m = new int[256];
            for(int j=0; j<len;j++){
                if(board[j][i]== '.' )  continue;
                if( m[ board[j][i] ] !=0) return false;
                else    m[board[j][i]] = (int) board[j][i]; 
            }
        }
        for(int j = 0; j<len; j++){
            int[] m = new int[256];
            for(int i=0; i<len;i++){
                if( board[i%3+j/3*3][i/3+ j%3*3] =='.') continue;
                if( m[ board[i%3+j/3*3][i/3+ j%3*3] ] !=0) return false;
                else    m[board[i%3+j/3*3][i/3+ j%3*3]] = (int) board[i%3+j/3*3][i/3+ j%3*3]; 
            }
        }
        return true;
    }

  public boolean isIsomorphic(String s, String t) {
    HashMap<Character, Integer> ms = new HashMap<Character, Integer>();
    HashMap<Character, Integer> mt = new HashMap<Character, Integer>();
    if(s.length()<=1)   return true;
    for(int i=0; i<s.length(); i++){
        if(ms.containsKey(s.charAt(i) ) ==true)    ms.put(s.charAt(i), ms.get(i) +1 );
        else    ms.put(s.charAt(i),1);
        if(mt.containsKey(t.charAt(i) ) ==true)    mt.put(t.charAt(i), mt.get(i) +1 );
        else    mt.put(t.charAt(i),1);
    }
    for(int i = 0; i<s.length(); i++ ){
        if( ms.get( s.charAt(i)) >=1 ){
            ms.put( s.charAt(i), ms.get(i)-1);
            if(mt.get(t.charAt(i)) <1)  return false;
            else    mt.put( t.charAt(i), mt.get(i)-1);
        }    
    }
    return true;
  }

  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> out = new ArrayList<List<Integer>>();
    //Queue<Integer>[] q = new Queue<Integer>[100]
    if(root == null)    return out;
    depth(root, 1, out);
    return out;
  }

  public int trailingZeroes(int n) {
    int num0 = 0;
    if (n<0)  return  0;
    int num5 = n / 5;
    for(int i = num5 *5; i>4; i = i-5){
        int t = i;
        while(t>0){
            int yu = t%10;
            if(yu == 0){
                num0 +=1;
            }else {
                break;
            }
            t = t/10;
        }
    }
    return num0;
  }

  public boolean isPalindrome(int x) {
    if(x<0) return false;
    int n = x, out = 0;
    while(n>0){
        int temp = n%10;
        out += out*10 + temp;
        n = n/10;
    }
    if (out==x) return true;
    return false;
  }

  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    if(E>=C || G<=A || D<=F || B>=H)    return 0;
    
    return Math.min(E-C, A-G) * Math.min(F-D, B-H);
  }

  public int maxProfit(int[] prices) {
    int sum=0, position = 0, issold = 0, out =0;
    for(int i = 0; i<prices.length; i++){
        sum += prices[i];
        issold = 0;
        position = 1;
        for(int j=i+1; j<prices.length-1; j++){
            if(position==1 && issold == 0){
                sum += prices[j];
            }
            //if(position==1 && issold == 1){}
            if(position==0) return Math.max(prices[j+1] - prices[j], 0);
        } 
        if(sum > out)   out = sum;
        sum=0;
    }
    return out;
  }

    public void depth( TreeNode root,int d , List<List<Integer>> out){
        if(root == null)    return;
        if (out.size()<d){
            LinkedList<Integer> temp = new LinkedList<Integer>();
            temp.add( root.val);
            out.add(0,temp);
        }else{
            out.get(out.size()-d).add(root.val);
        }
        depth(root.left, d+1, out);
        depth(root.right, d+1, out);
    }

  public boolean isSymmetric(TreeNode root) {
    if(root == null)    return true;
    return isSymm(root.left, root.right) ;
  }

  public boolean isSymm(TreeNode l, TreeNode r){
    if(l == null && r ==null)  return true;
    if(r != null && l ==null)  return false;
    if(r == null && l !=null)  return false;
    if(r.val == l.val){
        return isSymm(r.right, l.left) && isSymm(r.left, l.right);
    }else return false; 

//isSymm(root.right);
//  isSymm(root.left);
  }

  public boolean isBalanced(TreeNode root) {
    if(root==null)  return true;
    return  isBalan(root.left, root.right);
  }

  public boolean isBalan(TreeNode l, TreeNode r){
    if(l == null && r==null)    return true;
    if(r == null ){
        if(l.left!=null){
            if(l.left.left != null || l.left.right != null) return false;
            else    return true;
        }else if(l.right != null){
            if(l.right.left != null || l.right.right != null) return false;
            else    return true;
        }else   return true;
    }else if(l == null ){
        if(r.left!=null){
            if(r.left.left != null || r.left.right != null) return false;
            else    return true;
        }else if(r.right != null){
            if(r.right.left != null || r.right.right != null) return false;
            else    return true;
        }else   return true;
    }
    return isBalan(l.left,l.right) && isBalan(r.left, r.right);
  }

  public ListNode oddEvenList(ListNode head) {
    if(head == null || head.next == null)    return head;
    ListNode odd = head, even=head.next, podd = odd, peven=even;
    while(peven != null && peven.next !=null && peven.next.next !=null){
        if(peven.next == null){
            peven = null;
            break;
        }
        if(peven.next.next == null){
            podd = peven.next;
            peven = null;
            break;
        }
        podd.next = peven.next;
        podd = peven.next;
        peven.next = podd.next;
        peven = podd.next;
    }
    podd.next = even;
    return odd;
  }


  public boolean hasCycle(ListNode head) {
    if(head==null)  return false;
    ListNode fast=head, slow=head;
    while(true){
      if(fast==null || fast.next==null || fast.next.next==null ) return false;
      fast = fast.next.next;
      slow = slow.next;
      if(fast == slow)  return true;
    }
    //return false;
  }

  public int reverse(int x) {
    if(x==0)  return 0;
    int y = Math.abs(x), out=0;
    
    while(y>0){
      int temp = y%10;
      out = out * 10 + temp;
      if(out>=Integer.MAX_VALUE)  return 0;
      y = y/10;
    }
    if(x<0) return -out;
    return out;
  }

  public int uniquePaths(int m, int n) {
    if(m==1 & n==1) return 1;
    int[][] max = new int[m+1][n+1];
    for(int i=0; i<m+1;i++) max[i][0] = 1;
    for(int j=0; j<n+1;j++) max[0][j] = 1;
    for(int i=1; i<=m; i++) {
      for(int j=1; j<=n; j++){
        max[i][j] = max[i-1][j] + max[i][j-1];
      }
    }
    return max[m][n];
  }

  public int rob(int[] nums) {
    if(nums.length == 0) return 0;
    int r0 = 0, r1 = nums[0], out=0;
    for(int i=1; i<nums.length; i++){
      out = nums[i]+r0 > r1 ? nums[i] + r0 : r1;
      r0 = r1;
      r1 = out;
    }
    return out;
  }

  public int climbStairs(int n) {
    int n1=0, n2=1, out =0;
    if(n<1) return n;
    for(int i = 1; i<=n; i++){
      out = n1+n2;
      n1 = n2;
      n2 = out;
    }
    return out;
  }

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> out = new LinkedList<List<Integer>>();
    List<Integer> sub = new LinkedList<Integer>();
    Arrays.sort(nums);
    int len = nums.length, n = (int) (Math.pow(2, len)) ;
    for(int i = 0; i<n; i++){
      int j = 0, k = i;
      sub.clear();
      while(j <len){
        if ((k & 1) ==1)  sub.add(nums[j]);
        k = k>>1;
        j++;
      }
      out.add(new LinkedList<Integer>(sub));
    }
    return out;
  }

  public int reverseBits(int n) {
    int size=32, out = 0, temp=0;
    while(size-- > 0){
      temp = (n & 1);
      out = out + temp;
      out = out << 1;
    }
    return out;
  }

  public int hammingWeight(int n) {
    //int[] out = new int[100];
    //unsigned int nn = (unsigned int) n;
    int sum = 0, size = 32;
    while(size-- > 0){
      sum +=  (n & 1) == 1 ? 1: 0;
      n /= 2;
      //i++;
    }
    return sum;
  }


  public int titleToNumber(String s) {
    int out =0;
    int len = s.length();
    for(int i = 0; i<len; i++){
      int temp = (int)( s.charAt(i)) - 64;
      out += temp * Math.pow(26,len-i-1);
    }
    return out;
  }

  String convertToTitle(int n) {
    int dividend;
    String out = "";
    while(n > 26){
      int weight = (n-1) % 26;
      n = (n-1)/26;
      char c = (char)(weight + 64 +1);
      out = c + out;
    } 
    char c = (char)(n + 64);
    out = c + out;
    return out;
  }

/* public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> out = new LinkedList<List<Integer>>();
    if(root==null)  return out;
    List<Integer> li = new LinkedList<Integer>();
    TreeNode p = root;
    int total = 0;
    li = pathSumRecur(p, sum, li, total ); 
    while(li!=null){
      out.add(li);
      li.clear();
      total = 0;
      p = root;
      li = pathSumRecur(p, sum, li, total ); 
    }
    return out;
  }

  public List<Integer> pathSumRecur(TreeNode p, int sum, List<Integer> l, int total) {
    if(p==null)  return null;
    if(p.left== null && p.right == null  && total+p.val  == sum){
      l.add(p.val);
      p = null;
      return l;
    }
    if(p.left== null && p.right == null  && total+p.val  != sum){
      p = null;
      return null;
    }
    total += p.val;
    l.add(p.val); 
    l = pathSumRecur(p.left, sum, l, total);
    l = pathSumRecur(p.right, sum, l, total);
    return null;
  }*/

  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> out = new LinkedList<List<Integer>>();
    if(root==null)  return out;
    int total = 0;
    List<Integer> li = new LinkedList<Integer>();
    out = pathSumRecur(root, sum, out, li,  total ); 
    return out;
  }

  public List<List<Integer>> pathSumRecur(TreeNode p, int sum, List<List<Integer>> r,  List<Integer> l, int total) {
    if(p==null)  return null;
    if(p.left== null && p.right == null  && total+p.val  == sum){
      l.add(p.val);
      r.add(l);
      return r;
    }
    if(p.left== null && p.right == null  && total+p.val  != sum){
      return null;
    }
    total += p.val;
    l.add(p.val); 
    r= pathSumRecur(p.left, sum,r, l, total);
    r = pathSumRecur(p.right, sum,r, l, total);
    return null;
  }
  public boolean isUgly(int num) {
    if(num<=0) return false;
    if(num==1) return true;
    while(true){
      if(num%2==0)  num=num/2;
      else if(num%5==0) num=num/5;
      else if(num%3==0) num=num/3;
      else  break;
    }
    if(num>5  ) return false;
    else return true;
  }

  public int countPrimes(int n) {
    int count = 0;
    for (int i = 1; i < n; i++) {
      if (isPrime(i)) count++;
    }
    return count;
  }

  private boolean isPrime(int num) {
    if (num <= 1) return false;
    //Loop's ending condition is i * i <= num instead of i <= sqrt(num)
    // to avoid repeatedly calling an expensive function sqrt().
    for (int i = 2; i * i <= num; i++) {
      if (num % i == 0) return false;
    }
    return true;
  }


  public TreeNode getRoot1(){   return root1;}
  public TreeNode getRoot2(){   return root2;}
  public boolean isSameTree(TreeNode p, TreeNode q) {
    if(p==null && q==null) return true;
    if(p==null && q!=null) return false;
    if(q==null && p!=null) return false;
    if(p.val != q.val)  return false;
    else{
      return isSameTree(p.right, q.right) && isSameTree(p.left, q.left);
    }
  }

  public int bulbSwitch(int n) {
    /*HashMap<Integer,Boolean> mp = new HashMap<Integer,Boolean>();
    for (int i =1;i<=n; i++){
      mp.put(i, true);
    }
    for (int k = 2; k<=n; k+=2){
      mp.put (k, false);
    }
    for (int j =3 ;j<=n; j++){
      for(int i=j; i<=n; i+=j){
        if(mp.get(i) == true )  mp.put(i, false);
        else  mp.put(i,true);
      }
    } */

    HashMap<Integer,Integer> mp = new HashMap<Integer,Integer>();
    for (int i =1;i<=n; i++){
      mp.put(i, 1);
    }

    for (int j =2 ;j<=n; j++){
      for(int i=j; i<=n; i+=j){
        mp.put(i,  mp.get(i) +1 );
      }
    }


    int sum = 0;
    for(int i = 1;i<=n;i++){
      if(mp.get(i)%2 == 1) sum += 1;
    }
    return sum;
  }

  public boolean isHappy(int n) {
    int cur=0;
    int iter = 0;
    while (iter<8){
      if (cur== 1)  return true;
      if (cur< 100) iter++;
    }
    return false;
  }

  public int getNum(int n){
    Stack<Integer> st = new Stack<Integer>();
    int i = n;
    int yu;
    do {
      yu = i%10;
      i = (i-yu) /10;
      st.push(yu);
    }while( i - yu != 0 );
    int sum = 0;
    while(st.empty() == false){
      sum += Math.pow(st.pop(), 2 ); 
    }
    return sum;
  }


  public List<List<Integer>> levelOrder_queue(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    List<List<Integer>> out = new LinkedList< List<Integer> >();
    if(root == null)  return out;
    q.add(root);
    TreeNode p = root;
    while(q.isEmpty() == false){
      int level_num = q.size();
      List<Integer> level_l = new LinkedList<Integer>();
      for(int i=0; i<level_num; i++){
        p = q.poll();
        if(p.left != null)  q.offer(p.left );
        if(p.right != null)  q.offer(p.right );
        level_l.add(p.val);
      }
      out.add(level_l);
    }
    return out;
  }


  public int minDepth_another_recursion(TreeNode root) {
    if(root==null)  return 0;
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    if(left > right)  return left +1;
    else return right +1;
  }

  public int maxDepth(TreeNode root) {
    int level=0;
    if(root == null) return level;
    TreeNode p = root;
    //level++;
    return maxDepth(p,level);
  }
  public int maxDepth(TreeNode p, int level){
    if(p==null) return level++;
    level++;
    int left = maxDepth(p.left, level); 
    int right = maxDepth(p.right, level); 
    if( left > right)
      return 1 + left;
    else return 1 + right;
  }

  public boolean isAdditiveNumber(String num) {
    int len = num.length();
    for(int i=1; i<=len/2; i++){
      if(num.charAt(0) == '0' && i>1) return false;
      for(int j=1; Math.max(j,i) <= len-i-j;j++){
        if(num.charAt(i) == '0' && j>1  ) break;
        Long n1 = Long.parseLong( num.substring(0,i ) );
        Long n2 = Long.parseLong( num.substring(i,i+j ) );
        System.out.println( n1 + " + "+ n2  );
        if( isAdditive(n1, n2, i+j,num) ){
          return true;
        }
      }
    }
    return false;
  }
  private boolean isAdditive(Long n1,Long n2,int n3_start, String num){
    if(n3_start == num.length())  return true;
    Long sum = n1+n2;
    System.out.println( n1 + " + "+ n2 + " = "+ sum );
    String n3_s =Long.toString(sum);
    n1 = n2;
    n2 = sum;
    return num.startsWith(n3_s, n3_start) && isAdditive(n1, n2, n3_start+ n3_s.length(), num);
  }


  public int mySqrt(int x) {
    // very important trick: int should be casted to double in case that the divide doesn't work correctly.
    double y = 1.0, ERR = 1.0e-4;
    double s = (double) x;
    while(Math.abs(y*y - s) > ERR){
      y = y*0.5 + 0.5*(s/y);
    }
    return (int)y;

    /*if(x<0) return -1;
    double low = 0, up=Integer.MAX_VALUE, mid = low + 0.5*(up-low);
    double ERR = 1.0e-5 , diff=10000;
    while(up>low){
      diff =  mid*mid - x ;
      if(diff ==0 )
      { ERR =0.0001;}
      return (int)mid;
      else if(diff>0) up = mid;
      else  low = mid;
      //System.out.print("  " + mid);
      if(Math.abs(diff)<ERR) {
        //System.out.print(" mid  " + mid + "  diff   " + diff);
        if(Math.round(mid) - mid > 1.0e-7)
          return (int)mid;
        else{
          return (int)Math.round(mid);
        }
      }
      mid = low+ 0.5*(up-low);
    }
    return -1; */
  }


  public int strStr(String haystack, String needle) {
    
    int len_hay = haystack.length(), len_need = needle.length();
    if(len_hay<len_need)	return -1;
    else if(len_need == 0)	return 0;
    for(int i = 0; i<len_hay; i++){
      if(haystack.charAt(i)==needle.charAt(0)){
        if(len_hay-i<len_need)	return	-1;
        if( needle.equals(haystack.substring(i,i+len_need))){
          return i;
        }	
      }
    }
    return -1;
  }

  public String simplifyPath(String path) {
    Stack<String> folder = new Stack<String>();
    String[] spl = path.split("/");
    String out="";
    for(String s:spl){
      if(s.equals(".") )  continue;
      else  if(s.equals("..")) {
        if (folder.empty()==true) { 
          //out = "/"; 
          //return out;
        }  else   {folder.pop();}
      }else {
        folder.push(s);
      }
      System.out.print("  " + s);
    }
    System.out.println();
    while(folder.empty() ==false){
      String t = folder.pop();
      if(t.length()>0){
        out = "/" + t  + out;
      }
    }
    //out = "/" + out; 
    System.out.println("  " + out);
    return out;
  }

  public TreeNode invertTree(TreeNode root) {
    Stack<TreeNode> save = new Stack<TreeNode>();
    if (root == null) return null;
    TreeNode p = root;
    save.push(p);
    while(p!=null){
      p = save.pop();
      TreeNode temp = p.left;
      p.left = p.right;
      p.right = temp;
      save.push(p.left);
      save.push(p.right);
    }
    return root;
  }
/*  public TreeNode invertTree(TreeNode root) {
    if (root = null) return null;
    TreeNode temp;
    temp = root.right;
    root.right = invertTree(root.left);
    root.left = invertTree(temp);
    return root;
  } */

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode s = root;
    while(s!=null){
      if(s.val>p.val && s.val >q.val) s=s.left;
      else if (s.val<p.val && s.val <q.val) s=s.right;
      else  return s;
    }
      return null;
  }

  // Push element x to the back of queue.
  public void push(int x) {
    input.push(x);
  }

  // Removes the element from in front of queue.
  public void pop() {
    peek();
    output.pop();
  }

  // Get the front element.
  public int peek() {
    if(output.empty()){
      while(!input.empty()){
        output.push(input.pop());
      }
    }
    return output.peek();
  }

  // Return whether the queue is empty.
  public boolean empty() {
    return output.empty() && input.empty();
  }

  public void printQueue() {
    if(empty()==true ) {
      System.out.println("stack is already empty");
      return;
    } else{
      System.out.println(" "+ peek());
    }
  }

/*  public void push(int x) {
    while(q.peek()!=null){
      q2.add(q.poll());
    }
    q.add(x);
    while(q2.peek()!=null){
      q.add(q2.poll());
    }
  }

  //Removes the element on top of the stack.
  public void pop() {
    q.poll(); 
  }

  // Get the top element.
  public int top() {
    return q.peek();
  }

  // Return whether the stack is empty.
  public boolean empty() {
    if(q.peek()==null)  return true;
    return false;
  }

  public void printQueue() {
    if(empty()==true ) {
      System.out.println("stack is already empty");
      return;
    }
    while(q.peek()!=null){
      System.out.print(" "+ q.peek());
      q2.add(q.poll());
    }
    while(q2.peek()!=null){
      q.add(q2.poll());
    }
    System.out.println("");
  }

  public boolean isEmpty(ListNode top){
    if(top==null) return true;
    else  return false;
  }
*/

  //
//  public void push(int x) {
//    ListNode nd = new ListNode(x);
//    ListNode save = new ListNode(min);
//    if(x<=min){
//      save.next = stack;
//      nd.next = save;
//      stack = nd;
//      min = x;
//    }else {
//      nd.next = stack;
//      stack = nd;
//    }
//  }
//
//  public void pop() {
//    if(isEmpty(stack)==true ) {
//      System.out.println("stack is already empty");
//      return;
//    }
//    if(stack.val == min ) {
//      min = stack.next.val;
//      stack=stack.next.next;
//    }else{
//      stack = stack.next;
//    } 
//  }
//
//  public int top() {
//    if(isEmpty(stack)==false) {
//      return stack.val;
//    }else{ 
//      System.out.println("stack is already empty");
//      return -1;
//    }
//  }
//
//  public int getMin() {
//    if(isEmpty(stack)==false) {
//      return min;
//    }else{ 
//      System.out.println("stack is already empty");
//      return -1;
//    }
//  }

//  public void printStack() {
//    if(isEmpty(stack)==true ) {
//      System.out.println("stack is already empty");
//      return;
//    }
//    ListNode p = stack;
//    while(p!=null){
//      System.out.print(" "+ p.val);
//      p = p.next;
//    }
//    System.out.println("");
//  }
//  
//  public ListNode getListHead(){
//    return head;
//  }

  public ListNode swapPairs(ListNode head) {
    if(head==null || head.next==null) return head;
    ListNode cur=head, next;
    next = head.next;
    cur.next=next.next;
    next.next = cur;
    head = next;
    while(cur!=null &&cur.next!=null && cur.next.next!=null){
      next=cur.next.next;
      cur.next.next = next.next;
      next.next=cur.next;
      cur.next = next;
      cur = next.next;
    }
    return head;
  }

  public void deleteNode(ListNode node) {
    node.val = node.next.val;
    ListNode pn = node.next;
    node.next = pn.next;
  }

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if(headA==null || headB==null)  return null;
    ListNode  pa=headA, pb=headB;
    int lena=0, lenb=0;
    while(pa!=null){ 
      lena++;
      pa=pa.next;
    }
    while(pb!=null){ 
      lenb++;
      pb=pb.next;
    }
    pa=headA; 
    pb=headB;
    if(lena<lenb){
      int diff = lenb-lena;
      for(int i=0;i<diff;i++) pb=pb.next;
    }else{
      int diff = lena-lenb;
      for(int i=0;i<diff;i++) pa=pa.next;
    }
    while(pa!=null && pb!=null){
      if(pa==pb)  return pa;
      pa=pa.next;
      pb=pb.next;
    }
    return null;
  }

  public ListNode reverseList_recursion(ListNode head) {
    if(head ==null) return null;
    else if(head.next==null){
      return head; 
    } else{
      ListNode temp;
      temp = reverseList_recursion(head.next);
      while(temp.next!=null) temp = temp.next;
      temp.next = head;
      head.next=null;
      return temp;
    }
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    //if(l1==null && l2==null)  return null;
    //ListNode head = new ListNode(0);
    //ListNode cur=head;
    if(l1==null){
      return l2;
    }else if(l2==null){
      return l1;
    }else if(l1.val<=l2.val){ 
      ListNode temp;
      temp =  mergeTwoLists(l1.next, l2);
      l1.next = temp;
      return l1;
    }
    else{
      ListNode temp;
      temp =  mergeTwoLists(l1, l2.next);
      l2.next = temp;
      return l2;
    }
  }
  public ListNode mergeTwoLists_iterative(ListNode l1, ListNode l2) {
    if(l1==null && l2==null)  return null;
    //else  if(l2==null)  return l1;
    //else  if(l1==null)  return l2;
    ListNode head = new ListNode(0);
    ListNode cur=head;
    while(l1!=null && l2!=null){
      if(l1.val<=l2.val){
        cur.next=l1;
        cur = l1;
        l1=l1.next;
        cur.next=null;
      } else{
        cur.next=l2;
        cur=l2;
        l2=l2.next;
        cur.next=null;
      }
    }
    if(l1==null){
      cur.next=l2;
    }
    if(l2==null){
      cur.next=l1;
    }
    return head.next;
  }

  public ListNode reverseList(ListNode head) {
    if(head==null || head.next==null) return head;
    ListNode  cur=head, rev=null, temp=null;
    while(cur!=null){
     temp= rev;
     rev=cur;
     cur=cur.next;
     rev.next=temp;
    }
    return rev;
  }

  public boolean isPalindrome(ListNode head) {
    if(head==null)  return true;
    if(head.next==null) return true;
    ListNode  fast=head, slow=head; 
    ListNode rev=null, temp;
    while(fast!=null && fast.next!=null){
      fast=fast.next.next;
      temp=rev;
      rev=slow;
      slow=slow.next;
      rev.next=temp;
    }
    if(fast!=null )
      slow=slow.next;

    /*while(rev!=null && slow!=null){
     *       System.out.println(rev.val + "  vs  " +slow.val);
     *             rev=rev.next;
     *                   slow=slow.next;
     *                       }*/

    while(rev!=null && slow!=null){
      if(rev.val!=slow.val) return false;
      rev=rev.next;
      slow=slow.next;
    }
    return true;
  }


  public ListNode deleteDuplicates(ListNode head) {
    if(head==null || head.next==null) return head;
    ListNode cur=head.next, pivot;
    while(cur!=null && head.val == cur.val ){
      head = head.next;
      cur = cur.next;
    }
    pivot = head;
    cur = head.next;
    while(cur!=null){
      if(pivot.val==cur.val){
        pivot.next=cur.next;
      }else	pivot = cur;
      cur = cur.next;
    }
    return head;
  }

  public ListNode removeElements(ListNode head, int val) {
    if(head==null) return null;
    while(head!=null&&head.val==val){
      head = head.next;
    }
    ListNode cur=head, pivot=null;
    while(cur!=null){
      if(cur.val==val){
        pivot.next=cur.next;
        cur=cur.next;
        continue;
      }
      pivot = cur;
      cur = cur.next;
    }
    return head;
  }

  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode nd=head, pivot;
    int len=0;
    while(nd!=null){
      len ++;
      nd = nd.next;
    }
    if(len <=1) return null;
    nd = head;
    pivot = null;
    for(int i=1; i<len+1-n;i++){
      pivot = nd;
      nd=nd.next;
    }
    if(pivot==null){
      return head.next;
    }else{
      pivot.next = nd.next;
      return head;
    }
  }


  public int majorityElement(int[] nums) {
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    if(nums.length == 1)	return nums[0];
    for(int i=0; i<nums.length;i++){
      if(!map.containsKey(nums[i])){
        map.put(nums[i],1);
      }else{
        int update = map.get(nums[i]) +1;
        map.put(nums[i],update);
        System.out.println(" value "+ nums[i] + " updates  " + update);
        if(update>nums.length/2)	return nums[i];
      }
    }
    return -1;
  }


  public void merge(int[] nums1, int m, int[] nums2, int n) {
    if(nums1==null || nums1.length==0 )	return;
    int j = 0, i=0;
    int[]  nums = new int[m+n];
    for(int k=0;k<m+n;k++){
      if(i>=m){	nums[k]=nums2[j]; j++; continue;}
      if(j>=n){	nums[k]=nums1[i]; i++; continue;}
      if(nums1[i]<=nums2[j]){	
        nums[k] = nums1[i];
        i++;
      }else{
        nums[k] = nums2[j];
        j++;
      }
    }
    for(int k=0;k<m+n;k++){
      System.out.print("  "+ nums[k]);
      nums1[k]=nums[k];
    }	
  }

  public List<Integer> getRow(int rowIndex) {
    List<Integer> li = new ArrayList<Integer>();
    if(rowIndex<0)      return li;
    li.add(1);     
    if(rowIndex==0)  return li;
    li.add(1);      
    if(rowIndex==1) return li; 
    int[] pivot  = new int[rowIndex+1];
    pivot[0] = 1; pivot[1] =1;
    int[] curr  = new int[rowIndex+1];
    for(int i=2; i<=rowIndex;i++){
      curr[0] = 1; curr[i] = 1;     
      for(int k=1; k<i; k++){
        curr[k] = pivot[k-1] + pivot[k];
      }
      for(int k=0; k<= i; k++){
        pivot[k] = curr[k];
      }       
    } 
    for(int k=0; k<= rowIndex; k++){
      System.out.print("  " + curr[k]);
      li.add(curr[k]);
    }       
    return li;
  }


  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    if(nums==null || nums.length==0)	return false;
    //HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    int len=nums.length;
    for(int j=0; j<len;j++){
      int i = Math.max(0,j-k);
      for(int l=i;l<j;l++){
        if(Math.abs(nums[l]- nums[j])<=t)	return true;
      }
    }
    return false;
  }


  public boolean containsNearbyDuplicate(int[] nums, int k) {
    if(nums==null || nums.length==0)	return false;
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    int len=nums.length;
    for(int i=0; i<len;i++){
      if(map.containsKey(nums[i])){
        if(i-map.get(nums[i]) <= k){
          return true;
        }else	map.put(nums[i],i);
      }else	map.put(nums[i],i);
    }
    return false;
  }


  public boolean containsDuplicate(int[] nums) {
    HashSet<Integer> set = new HashSet<Integer>();
    if(nums==null || nums.length==0)	return false;
    for(int it:nums){
      if(set.contains(it))	return true;
      set.add(it);
    }
    return false;
  }

  public void moveZeroes(int[] nums) {
    if(nums==null || nums.length==0)	return;
    int len=nums.length, j=0;
    for(int i=0; i<len; i++){
      while(j<len){
        if(nums[j]!=0)	break;
        else if(j==len-1){	
          for(int k:nums)	System.out.print(" " + k);
          return;
        }
        j++;
      }
      if(i>=j){
        j++;
        continue;
      }
      if(nums[i]==0){
        nums[i] = nums[j];
        nums[j] = 0;
      }
    }
    for(int k:nums)	System.out.print(" " + k);
  }

  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> out = new  ArrayList<List<Integer>>();
    if(numRows==0)      return out;
    List<Integer> li = new ArrayList<Integer>();
    int[]  pivot = {1,1};
    int[] curr ;
    li.add(1);      out.add(li);
    if(numRows==1)  return out;

    li = new ArrayList<Integer>(); li.add(1);      li.add(1);      out.add(li);    if(numRows==2) return out; for(int i=3; i<=numRows;i++){
      li = new ArrayList<Integer>();
      curr = new int[i];
      curr[0] = 1; curr[i-1] = 1;     
      for(int k=1; k<i-1; k++){
        curr[k] = pivot[k-1] + pivot[k];
      }
      pivot = new int[i];
      for(int k=0; k< i; k++){
        pivot[k] = curr[k];
        li.add(curr[k]);
      }       
      out.add(li);
    } 

    return out;
  }


  public int[] plusOne(int[] digits) {
    if(digits==null || digits.length==0) return digits;
    int len = digits.length, carry = 1;
    boolean all_9 = true;
    for(int i:digits){
      if(i != 9) {	all_9=false; break;}
    }
    if(all_9){
      int[] out9 = new int[len+1];
      out9[0] = 1;
      for(int k=1; k<=len; k++)	out9[k] = 0;
      return	out9;
    }
    for(int j=len-1; j>=0; j--){
      if(carry+digits[j]>9){
        digits[j] = 0;
        carry = 1;
      }else{
        digits[j] +=carry;
        System.out.print("which one " + j+ " value: " +digits[j]);
        carry=0;
      }	
    }
    for(int i: digits)	System.out.print(  "  " + i );
    return digits;
  }


  public int removeElement(int[] nums, int val) {
    int len = nums.length, j = len-1, count=0 ;
    if(len==0)	return 0;
    boolean f_goon = true;
    for(int i=0; i<len; i++){
      if(val==nums[i]){
        if(i>=j && f_goon == true){
          //for(int k:nums)	System.out.print("  "+ k);
          System.out.println();
          f_goon=false;
        }	
        count++;
        if(f_goon == false)	continue;
        while(j>i ){
          if(nums[j]==val){
            j--;
            continue;
          }else{
            nums[i] = nums[j];
            j--;
            break;
          }
        }
      }	
    }
    //for(int k:nums)	System.out.print("  "+ k);
    return len-count;
  }


  public void rotate(int[] nums, int k) {
    int n = nums.length, t = k%n, swap;
    //Set<Integer> set = new HashSet<Integer>();
    if(nums.length == 0 || nums.length==1)	return;
    if(t==0)	return;
    int j=0, swap_ind=0;
    swap = nums[j];

    for(int i=0;i<n;i++){
      System.out.println(nums[(n+j-t)%n] + " -> "+ nums[j]   );
      //set.add(j);

      if( swap_ind == (n+j-t)%n)  {	
        nums[j] = swap;
        int p = (n+j-t+1)%n;
        //while( set.contains(p)) p= (p+1)%n;
        j = p;
        swap_ind = p;
        swap = nums[j];
      } else{ 	
        nums[j] =  nums[( n + j -t)%n];
        j = ( n + j -t)%n;
      }
    }
    for(int i:nums)	System.out.print("  "+ i);
  }

  public int strStr1(String haystack, String needle) {
    int len_hay = haystack.length(), len_need = needle.length();
    if(len_hay<len_need)	return -1;
    else if(len_need == 0)	return 0;
    for(int i = 0; i<len_hay; i++){
      if(haystack.charAt(i)==needle.charAt(0)){
        if(len_hay-i<len_need)	return	-1;
        if( needle.equals(haystack.substring(i,i+len_need))){
          return i;
        }	
      }
    }
    return -1;
  }

  public int myAtoi(String str) {
    if(str.length()==0)	return 0;
    int start_ind = 0, end_ind = 0;
    boolean start = false, f_blank = false;
    String s =  str.trim(); 
    if(s.length()==0)	return 0;
    char f = s.charAt(0);
    if(f!='+'&&f!='-'&&!Character.isDigit(f) ) {
      return 0;
    }
    //System.out.println(s);
    for(int i = 0; i<=s.length(); i++){
      if(start == true && i==s.length())	{ end_ind=i;break;}
      char c = 'd';
      if(i<s.length())	c =s.charAt(i);
      if((c=='+'||c=='-'||Character.isDigit(c)) && (start == false)) {
        start = true;
        start_ind = i;
        continue;
      }
      if(start==true){
        if( !Character.isDigit(c) )	{
          end_ind=i; 
          break;
        }
      }
    }
    if(start==false) return 0;
    System.out.println("Indics:  " + start_ind + "  " + end_ind);
    String sub = s.substring(start_ind,end_ind);
    if(sub.equals( "+") || sub.equals("-"))	return 0;
    Double d = new Double("2.0");
    double r =(Double) d.parseDouble(sub);
    if(r>Integer.MAX_VALUE) return Integer.MAX_VALUE;
    if(r<Integer.MIN_VALUE) return Integer.MIN_VALUE;
    int out= Integer.parseInt(sub);
    System.out.println(out);
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
  public boolean isPalindrome(String s) {
    if(s.length() == 0) return true;
    //System.out.println(cs);
    int i=0, j=s.length()-1;
    boolean flag = true;
    while(i<j){
      //System.out.println(s.charAt(i)+" vs " + s.charAt(j)  );
      if( Character.isLetterOrDigit(s.charAt(i)) == false){
        //System.out.println("i is not alphanumeric " + s.charAt(i)  );
        i++;continue;
      }
      if( Character.isLetterOrDigit(s.charAt(j)) == false){ 
        //System.out.println("j is not alphanumeric:" + s.charAt(j)  );
        j--;continue;
      }
      if( s.charAt(i) == s.charAt(j) || Math.abs( s.charAt(i)-s.charAt(j)) == 32  ) {
        if( (Character.isDigit(s.charAt(i)) && Character.isLetter(s.charAt(j))) || ( Character.isDigit(s.charAt(j)) && Character.isLetter(s.charAt(i))) ){
          flag = false; break;
        } 
        flag = true;
        i++; j--;
      }
      else{
        flag = false;
        //System.out.println(s.charAt(i)+" vs " + s.charAt(j)  );
        break;
      }
    }
    System.out.println(flag);
    return flag;
  }

  public boolean isValid(String s) {
    Stack<Character> st = new Stack<Character>();
    if(s==null || s.length()==0) return true;
    boolean flag = false;
    for(int i = 0; i<s.length();i++){
      //System.out.println("character is:" + s.charAt(i) );
      if(st.empty()==false  ) {
        char thes = s.charAt(i);
        switch( st.peek()){
          case '[':  
            if(thes==']')	{st.pop(); flag = true;}
          case '(':  
            if(thes==')')	{st.pop(); flag = true;}
          case '{':  
            if(thes=='}')	{st.pop(); flag = true;}
            //System.out.println("flag is:" + flag );
        }				
      }
      if(flag==true) {
        flag =false;
        continue;
      }
      else{	
        st.push( s.charAt(i) );
        //System.out.println("The stack top is:");
        //System.out.println(st.peek());
      }
    }
    return st.empty();
  }


  public String countAndSay(int n) {
    String out = "";
    String pivot = "1";
    if(n<1) return null;
    if(n==1)  return pivot;
    for(int k=0; k<n-1; k++){
      int i=0, j=1, step = 1;
      out = "";
      while(j<pivot.length()+1){
        if ( j==pivot.length() ) {
          out=out+step+pivot.charAt(i);
          i=j;
          break;
        }
        if(pivot.charAt(j) != pivot.charAt(i)  ) {
          out = out + step + pivot.charAt(i);
          //System.out.println("step "+ step+ "out" + out );
          i=j;
          j++;
          step = 1;
        }
        else {
          step++;
          j++;
        }
      }
      if(i<pivot.length())	out= out+"1" + pivot.charAt(i);
      pivot = out;
      //System.out.println("result:" + out);
    }
    return out;
  }


  public String longestCommonPrefix(String[] strs) {
    int ind = 0;
    String out = "";
    if (strs == null) return null;
    if (strs.length == 0) return out;
    if (strs.length == 1) return strs[0];
    boolean ret = false;
    for(int i = 0; i< Integer.MAX_VALUE; i++) {
      if(ret == true)  { break; }
      for( String s : strs) {
        if( s.length() <= i ) { ret = true; ind =i; break;  }
        else{
          if(strs[0].charAt(i) != s.charAt(i)) { 	ind = i; ret = true; break;}  
        }
      }
    }
    System.out.println(ind   );
    if(ind == 0) out = "";
    else   		out = strs[0].substring(0, ind);
    System.out.println(ind + "   "+ out  );
    return out;
  }


  public String convert(String s, int numRows) {
    if( s.length() <= numRows || numRows == 1) return s;
    String[] sa = new String[numRows];
    for(int i=0; i<sa.length; i++){
      sa[i]="";
    }
    String out = "";
    for (int i=0; i<s.length(); i++){
      //System.out.println(i);
      int idx = i % (2*numRows -2);
      String str = Character.toString( s.charAt(i));
      if (idx<numRows){ 
        sa[idx] = sa[idx] + str;
      }
      else{  
        sa[2*numRows-2-idx] = sa[2*numRows-2-idx] + str;
        //System.out.println(sa[2*numRows-2-idx] );
      }	
    }
    for (String ss : sa){
      out += ss;
      //System.out.println(out);
    } 
    System.out.println(out);
    return out;
  }

  public int lengthOfLastWord(String s) {
    String blank = " ";
    if(s == null) return -1;
    //if(s.endsWith(blank) == true) return 0;
    //List<String> ls = new ArrayList();
    String[] ls = s.split(blank);
    int last = ls.length-1;
    for (String str : ls){
      System.out.println(str+" ");
    }
    if(last<0) return 0;
    else
      return ls[last].length();
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
    List<Integer> node = new ArrayList<Integer>();
    List<String> result = new ArrayList<String>();
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
    List<Set<Integer>> adj = new ArrayList<Set<Integer>>(n); 
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
      List<Integer> new_leaves = new ArrayList<Integer>();
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
      List<Integer> line = new ArrayList<Integer>();
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
    List<List<Integer>> ll = new ArrayList<List<Integer>>();
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
      List<Integer> l = new ArrayList<Integer>();
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
    List<Integer> level = new ArrayList<Integer>();
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

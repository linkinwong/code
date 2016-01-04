import java.util.*;
public class Solution {
	public class ListNode{
		int val;
		ListNode next;
		ListNode(int x) {val = x;}
	}    

	ListNode head;

	public Solution(){ head = null; }

	public ListNode getList(){  return head;}
	public ListNode push(ListNode a_node){
		if(null == head){ 
			a_node.next = null;
			head = a_node;
			return head;
		}
		a_node.next = head;
		head = a_node;
		return head;
	}
	public void print(ListNode head){
		ListNode p = head;
		if(null == head) System.out.println("empty list");
		else {
			while(p !=null){
				System.out.println(p.val+" ");
				p = p.next;
			}
		}
		System.out.println();
	}
	public ListNode create(){
		Scanner in = new Scanner(System.in);
		int integer = in.nextInt();
		while(integer != -100) {
			ListNode new_node = new ListNode(integer);
			head = push(new_node);
			print(head);
			integer = in.nextInt();
		}	
		return head;
	}

/*	public ListNode reverseList(ListNode h) {
		if(h.next == null || h == null) { 
			head = h;
			return head;
		}
		ListNode prev = null;
		ListNode curr=h;
		ListNode nextTemp;
        	while(curr != null){
			nextTemp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = nextTemp;
		}
		head = prev;
		return head;
    	}*/
	public ListNode reverseList(ListNode h) {
		if(h.next == null || h == null) { 
			head = h;
			return head;
		}
		ListNode p = reverseList(h.next);
		h.next.next = h;
		h.next = null;
		head = p;
		return p;
    	}
}

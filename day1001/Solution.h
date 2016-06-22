#ifndef SOLUTION_H
#define SOLUTION_H
#include <iostream>
#include <string>
#include <set>
#include <vector>
#include <algorithm>
using namespace std;
class Solution{
	public: 
		bool isPowerOfFour(int num);
		std::string reverseString(std::string s) ;
		std::string reverseVowels(std::string s) ;
		std::vector<int> intersection(std::vector<int>& nums1, std::vector<int>& nums2  );
		std::vector<int> intersect(std::vector<int>& nums1, std::vector<int>& nums2  );
		int sumRange(int i, int j, std::vector<int>& nums );
		bool isIsomorphic(string s, string t); 
		void Print();
};

std::string Solution::reverseString(std::string s){
	
	int i=0, j=s.size()-1;
	char c;
	while(i<j){
		c = s[i];
		s[i] = s[j];
		s[j] = c;
		i++;
		j--;
	}
	return s;
}

std::vector<int> Solution::intersection(std::vector<int>& nums1, std::vector<int>& nums2  ) {
	std::set<int> aset, bset;
	std::vector<int> out;
	std::vector<int>::iterator p;
	std::set<int>::iterator p_set;
	for(std::vector<int>::iterator q = nums1.begin(); q != nums1.end(); q++){
		aset.insert(*q);	
	} 
	for(p = nums2.begin(); p!=nums2.end(); p++){
		bset.insert(*p);
	}
	for(p_set = aset.begin(); p_set!=aset.end(); p_set++){
		if(bset.count(*p_set) ){
			out.push_back(*p_set);
		}
	}
	return out;
}


std::vector<int> Solution::intersect(std::vector<int>& nums1, std::vector<int>& nums2  ) {
	std::vector<int> out;
	std::vector<int>::iterator p, q;
	sort(nums1.begin(),nums1.end() );
	sort(nums2.begin(),nums2.end() );
	
	int i=0, j=0;
	while(nums1.size()>0 && nums2.size()>0 && i<nums1.size() && j<nums2.size() ){
		if( nums1[i] < nums2[j]) {
			i++;
		} else if( nums1[i] > nums2[j]){
			j++;
		}else{
			out.push_back(nums1[i]);
			i++;
			j++;
		}
	}
	return out;
}

std::string Solution::reverseVowels(std::string s){
	
	int i=0, j=s.size()-1;
	char chars[] = {'a','e', 'i','o', 'u'};
	std::set<char> vow_set(chars, chars + sizeof(chars)/sizeof(char) );
	char c;
	while(i<j){
		if( vow_set.count( s[i])== 0 ){
			i++;
			continue;
		}
		if( vow_set.count( s[j])== 0 ){
			j--;
			continue;
		}
		c = s[i];
		s[i] = s[j];
		s[j] = c;
		i++;
		j--;
	}
	return s;
}

bool Solution::isPowerOfFour(int num){
	if(num<=0) return false;
	if(num==1) return true;
	int n =  num, remain;
	if(n%10 != 4 && n%10 !=6){
		return false;
	}else{
		while(n/4 > 0){
			remain = n%4;
			n = n/4;
			std::cout<<"remain is "<<remain<<std::endl;
			if( remain != 0 ){	return false; }
		}
		//if( n == 1 ){	return true; }
	}
	return true;
}

int Solution::sumRange(int i, int j, std::vector<int>& nums ){
	std::vector<int> sum;
	sum.push_back(0);

	//for(int num: nums){
	for(int ii=0; ii< nums.size(); ii++){
		sum.push_back( nums[ii] + sum.back() );
		std::cout<<sum.back()<<std::endl;
	}

	return sum.at(j+1) - sum.at(i);

}


bool Solution::isIsomorphic(string s, string t) {
	const int N = s.size();
	int* ms = new int(256);
	int* mt = new int(256);
	for(int i=0; i<N; i++){
		if( ms[s[i]] ==0 && mt[t[i]]== 0  ){
			ms[s[i]] = i+1;
			mt[t[i]] = i+1;
		} else{
			if(ms[s[i]] != mt[t[i]] )   return false;
		}
	}
	return true;
}


/*
void Solution::Print(){
	//string s = "qwertyuiop[]asdfghjkl;'\\zxcvbnm,./";
	//string t = "',.pyfgcrl/=aoeuidhtns-\\;qjkxbmwvz";
	string s = "foo";
	string t = "bar";
	cout<< isIsomorphic(s,t)<<endl;

	//std::vector<int>  nums = {-2,0,3,-5,2,-1};
	//cout<<"result"<<sumRange(0,2,  nums )<<endl;
	//std::string out;
	//std::string in = "containers";
	//out = reverseVowels(in);
	//std::cout<<out;
//	std::vector<int> out;
//    int nums1[] = {16,2,77,9,2};
//    int nums2[] = {6,2,7,9,2};
//    std::vector<int>::iterator p_vec;
//    std::vector<int> numsa(nums1, nums1+ sizeof(nums1)/sizeof(int) );
//    std::vector<int> numsb(nums2, nums2+ sizeof(nums2)/sizeof(int) );
//    out = intersect(numsa,numsb);
//    for(p_vec = out.begin(); p_vec != out.end(); p_vec++){
//        std::cout<<*p_vec<<std::endl;
//    }
}

*/

#endif

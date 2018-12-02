package com.mirak.leetcode.individual.medium;


public class FindTheDuplicateNumber {
	public int findDuplicate(int[] nums) {
		int slow = 0;
		int fast = 0;
		while (slow != fast || slow == 0) {
			slow = nums[slow];
			fast = nums[nums[fast]];
		}
		
		slow = 0;
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[fast];
		}
		return slow;
	}
}

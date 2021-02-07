package com.practice.search;

public class RotatedSortedArray {

        public int search(int[] nums, int target) {
            if(nums.length == 0 ) {
                return -1;
            }
            if(nums.length == 1 ) {
                if(nums[0] == target) {
                    return 0;
                }
                return -1;
            }
            int pivotIndex = findPivot(nums);
            if(target < nums[0] && target > nums[pivotIndex]) {
                return search(nums, 0, pivotIndex, target);
            } else {
                return search(nums, pivotIndex + 1, nums.length - 1, target);
            }

        }
        private int search(int[] nums, int low, int high, int target) {
            while(low <= high) {
                int mid = low + (high - low)/2;
                if(nums[mid] == target) {
                    return mid;
                }
                if(nums[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return -1;
        }
        private int findPivot(int[] nums) {
            int low = 0;
            int high = nums.length -1;
            while(low <= high) {
                int mid = low + (high - low)/2;
                if(nums[mid] > nums[mid + 1]) {
                    return mid;
                }
                if(nums[mid] > nums[low]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            System.out.println(low);
            return low;
        }
    public static void main(String args[]) {
            RotatedSortedArray rotatedSortedArray = new RotatedSortedArray();
            int[] nums = {4,5,6,7,0,1,2};
            rotatedSortedArray.search(nums, -1);
    }
}

class Solution {
    public int missingNumber(int[] nums) {
        int ans = 0;
        for (int i = 1; i <= nums.length; ++i) {
            ans ^= i ^ nums[i - 1];
        }
        return ans;
    }
}
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var exchange = function (nums) {
    let j = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] & 1) {
            const t = nums[i];
            nums[i] = nums[j];
            nums[j++] = t;
        }
    }
    return nums;
};

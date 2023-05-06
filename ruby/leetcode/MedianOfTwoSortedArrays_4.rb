#4 ruby Median Of Two Sorted Arrays

# @param {Integer[]} nums1
# @param {Integer[]} nums2
# @return {Float}
def find_median_sorted_arrays(nums1, nums2)
  nums = (nums1 + nums2).flatten.sort
  len = nums.length
  return nums[len/2] if len&1 == 1
  ((nums[(len-1)/2] + nums[len/2]).to_f/2)
end

nums1 = [1, 3]
nums2 = [2, 4]

p find_median_sorted_arrays(nums1, nums2)
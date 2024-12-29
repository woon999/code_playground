# 496 Next Greater Element 1

# @param {Integer[]} nums1
# @param {Integer[]} nums2
# @return {Integer[]}
def next_greater_element(nums1, nums2)
  nums1.map do |num|
    idx = nums2.find_index num
    nums2[idx+1..].find { _1 > num } || -1
  end
end

nums1 = [4,1,2]
nums2 = [1,3,4,2]
p next_greater_element nums1, nums2
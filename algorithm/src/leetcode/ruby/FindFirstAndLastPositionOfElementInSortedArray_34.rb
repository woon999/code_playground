# 34 Find First and Last Position of Element in Sorted Array

# @param {Integer[]} nums
# @param {Integer} target
# @return {Integer[]}
def search_range(nums, target)
  s = nums.index(target)
  return [-1, -1] unless s

  nums = nums.reverse
  e = nums.length - 1 - nums.index(target)

  [s, e]
end

nums = [5,7,7,8,8,10]
target = 8
p search_range(nums, target)
# 908 Smallest Range I

# @param {Integer[]} nums
# @param {Integer} k
# @return {Integer}
def smallest_range_i(nums, k)
  t = 2 * k
  min = nums.min
  max = nums.max
  l = max - min
  if l <= t
    return 0
  end

  max - min - t
end

nums = [1, 3, 6]
k = 3
p smallest_range_i nums, k
# 16 two pointer 3Sum Closet

# @param {Integer[]} nums
# @param {Integer} target
# @return {Integer}
def three_sum_closest(nums, target)
  nums = nums.sort
  result = nums[0..2].sum
  0.upto(nums.length - 2) do |i|
    l, r = i + 1, nums.length - 1
    while l < r do
      sum = nums[i] + nums[l] + nums[r]
      result = sum if (target - sum).abs < (target - result).abs
      sum < target ? l += 1 : r -= 1
    end
  end

  result
end

nums = [-1, 2, 1, -4]
target = 1
p three_sum_closest(nums, target)
# 2574 Left and Right Sum Differences

# @param {Integer[]} nums
# @return {Integer[]}
def left_right_difference(nums)
  left_sum, right_sum = 0, nums.sum
  res = []
  nums.each do |n|
    right_sum -= n
    res << (left_sum - right_sum).abs
    left_sum += n
  end
  res
end

nums = [10, 4, 8, 3]
p left_right_difference nums
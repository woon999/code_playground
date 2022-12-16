# 561 Array Partition

# @param {Integer[]} nums
# @return {Integer}
def array_pair_sum(nums)
  nums.sort!

  nums.select.each_with_index { |_, idx| idx.even? }.sum
end

nums = [6, 2, 6, 5, 1, 2]
p array_pair_sum nums
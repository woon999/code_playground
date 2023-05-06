# 2535 Difference Between Element Sum and Digit Sum of An Array

# @param {Integer[]} nums
# @return {Integer}
def difference_of_sum(nums)
  element_sum = nums.sum
  digit_sum = nums.map { |num| num.to_s.split('').map(&:to_i).sum }.sum

  element_sum - digit_sum
end

nums = [1,15,6,3]
p difference_of_sum nums
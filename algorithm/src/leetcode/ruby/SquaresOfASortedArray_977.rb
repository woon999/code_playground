# 977 Squares of a Sorted Array

# @param {Integer[]} nums
# @return {Integer[]}
def sorted_squares(nums)
  nums.map { |n| n**2 }.sort
end

nums = [-4, -1, 0, 3, 10]
p sorted_squares nums
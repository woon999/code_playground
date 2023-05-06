# 268 Missing Number

# @param {Integer[]} nums
# @return {Integer}
def missing_number(nums)
  n = nums.size
  sum = 0
  0.upto(n-1).each do |idx|
    sum += (idx+1) - nums[idx]
  end

  sum
end

nums = [3,0,1]
p missing_number nums
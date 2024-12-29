

# @param {Integer[]} nums
# @return {Integer}

def max_sub_array(nums)
  sum = max = nums.first
  nums[1..].each do |num|
    sum = if sum.negative?
            num
          else
            sum + num
          end
    max = [sum, max].max
  end
  max
end

# nums = [1]
nums = [-2,1,-3,4,-1,2,1,-5,4]
p max_sub_array(nums)

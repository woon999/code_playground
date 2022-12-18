# 414 Third Maximum Number

# @param {Integer[]} nums
# @return {Integer}
def third_max(nums)
  nums = nums.sort.reverse.uniq

  nums.size >= 3 ? nums[2] : nums.first
end

nums = [2,2,3,1]
p third_max nums
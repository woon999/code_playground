# 485 Max Consecutive Ones

# @param {Integer[]} nums
# @return {Integer}
def find_max_consecutive_ones(nums)
  return 0 if nums.nil? || nums.empty?

  n = nums.length
  max = 0
  cur_max = 0

  0.upto(n - 1).each do |i|
    if nums[i] == 1
      cur_max += 1
      max = [max, cur_max].max
    else
      cur_max = 0
    end
  end

  max
end

nums = [1, 1, 0, 1, 1, 1]
p find_max_consecutive_ones nums
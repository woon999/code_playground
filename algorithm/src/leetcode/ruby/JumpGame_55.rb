# 55 Jump Game

# @param {Integer[]} nums
# @return {Boolean}
def can_jump(nums)
  max, n = 0, nums.size
  n.times do |i|
    return false if max < i
    max = [max, i + nums[i]].max
  end
  true
end

nums = [2, 2, 0, 1, 4]
p can_jump nums
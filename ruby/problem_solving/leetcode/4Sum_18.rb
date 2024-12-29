# 18 4Sum

# @param {Integer[]} nums
# @param {Integer} target
# @return {Integer[][]}
def four_sum(nums, target)
  length = nums.length - 1
  return [] if length < 3

  res = []
  nums.sort!

  (0..length-3).each do |i|

    (i+1..length-2).each do |j|
      l = j + 1
      k = length

      while l < k
        tmp = nums[i] + nums[j] + nums[k] + nums[l]

        if tmp == target
          res << [nums[i], nums[j], nums[k], nums[l]]
          l += 1
          k -= 1

          l += 1 while nums[l] == nums[l-1]
          k -= 1 while nums[k] == nums[k+1]
        elsif tmp > target
          k -= 1
        else
          l += 1
        end
      end
    end
  end

  res.uniq!

  res
end

nums = [1,0,-1,0,-2,2]
target = 0
p four_sum(nums, target)
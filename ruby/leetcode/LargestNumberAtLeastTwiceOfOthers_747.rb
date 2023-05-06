# 747 Largest Number At Least Twice of Others

# @param {Integer[]} nums
# @return {Integer}
def dominant_index(nums)
  max = nums.max
  idx = nums.index(max)

  nums.each do |num|
    next if num == max || num == 0

    return -1 if max < num * 2
  end

  idx
end

nums = [1, 2, 3, 4]
p dominant_index nums
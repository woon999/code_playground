# #75 ruby Sort Colors

# @param {Integer[]} nums
# @param {Integer} target
# @return {Integer[]}
def two_sum(nums, target)
  dict = {}
  nums.each_with_index do |num, i|
    if dict[target - num]
      return dict[target - num], i
    end
    dict[num] = i
  end
end

nums = [2,7,11,15]
target = 9
p two_sum(nums, target)
# 39 Combination Sum

# @param {Integer[]} candidates
# @param {Integer} target
# @return {Integer[][]}
def combination_sum(candidates, target)
  res = []
  return res if !candidates || candidates.empty?

  min = candidates.min
  generate_combination_sum(res, candidates, [], target, 0, min)

  res
end

def generate_combination_sum(res, nums, tmp, remain, index, min)
  res << tmp.clone if remain == 0
  return if remain < min # optimization

  (index...nums.length).each do |i|
    tmp << nums[i]
    generate_combination_sum(res, nums, tmp, remain - nums[i], i, min)

    tmp.pop
  end
end

candidates = [2, 3, 6, 7]
target = 7
p combination_sum(candidates, target)

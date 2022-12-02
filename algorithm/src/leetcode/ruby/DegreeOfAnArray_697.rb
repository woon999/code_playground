# 697 Degree Of An Array

# @param {Integer[]} nums
# @return {Integer}
def find_shortest_sub_array(nums)
  h = Hash.new { |h, k| h[k] = [0, nil, nil] }
  nums.each_with_index do |num, i|
    h[num][0] += 1  # count
    h[num][1] ||= i # from
    h[num][2] = i   # to
  end
  _, from, to = h.values.max_by { |cnt, from, to| [cnt, from - to] }

  to - from + 1
end

nums = [1,2,2,3,1,4,2]
p find_shortest_sub_array nums

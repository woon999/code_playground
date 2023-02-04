

# @param {Integer[]} arr
# @return {Integer[][]}
def minimum_abs_difference(arr)
  arr.sort.each_cons(2).group_by{ |a, b| b - a}.min.pop
end

arr = [4,2,1,3]
p minimum_abs_difference arr
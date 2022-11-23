# 77 Combinations

# @param {Integer} n
# @param {Integer} k
# @return {Integer[][]}
def combine(n, k)
  result = []

  combination(result, [], 1, n, k)

  result
end

def combination(result, box, start, n, k)
  return result << box.clone if box.size == k

  (start..n).each do |i|
    box.push i
    combination(result, box, i + 1, n, k)
    box.pop
  end
end

n = 4
k = 2
p combine n,k
# 7 ruby Reverse Integer

# @param {Integer} x
# @return {Integer}
def reverse(x)
  x = x.to_s

  neg = ''
  if x[0] == '-'
    neg = x[0]
    x = x[1...]
  end

  x = x.reverse.to_i
  return 0 if x >= 2**31-1 || x < -2**31
  neg.empty? ? x : -x
end

x = 1534236469
p reverse(x)

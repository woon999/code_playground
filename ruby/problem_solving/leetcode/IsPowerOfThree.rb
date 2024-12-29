# 326 Power of Three

# @param {Integer} n
# @return {Boolean}
def is_power_of_three(n)
  n > 0 && 3 ** Math.log(n+1, 3).to_i == n
end

n= 27
p Math.log(n+1, 3).to_i
p is_power_of_three n
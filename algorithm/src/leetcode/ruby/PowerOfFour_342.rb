# 342 Power of Four

# @param {Integer} n
# @return {Boolean}
def is_power_of_four(n)
  n > 0 && (Math.log(n) / Math.log(4) % 1 == 0)
end

n = 4
p is_power_of_four n

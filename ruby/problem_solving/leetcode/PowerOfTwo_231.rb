# 231 Power Of Two

# @param {Integer} n
# @return {Boolean}
def is_power_of_two(n)
  return n <= 0 ? false : (2**63 % n == 0)
end

n = 16
p is_power_of_two n
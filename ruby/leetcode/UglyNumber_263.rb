# 263 Ugly Number

# @param {Integer} n
# @return {Boolean}
def is_ugly(num)
  return false if num < 1

  while 1
    return true if num == 1
    if num%3 == 0
      num /= 3
      next
    elsif num%5 == 0
      num /= 5
      next
    elsif num%2 == 0
      num /= 2
      next
    else
      return false
    end
  end
end

n = 14
p is_ugly n
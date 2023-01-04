# 507 Perfect Number

# @param {Integer} num
# @return {Boolean}
def check_perfect_number(num)
  return false if 1 == num

  sum = 1
  i = 2

  # 2 <= num 이므로 i*i (i 최소 2) <= num 까지만 탐색
  while i*i <= num
    sum += i + num/i if num%i == 0
    i += 1
  end

  sum == num
end

num = 28
p check_perfect_number num
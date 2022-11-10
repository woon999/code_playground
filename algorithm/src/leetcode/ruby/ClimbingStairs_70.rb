# ruby 70 Climbing Stairs

# @param {Integer} n
# @return {Integer}
def climb_stairs(n)
  return 1 if n == 1
  return 2 if n == 2

  dp = []
  dp[1] = 1
  dp[2] = 2
  3.upto(n) do |num|
    dp[num] = dp[num-1] + dp[num-2]
  end

  dp.last
end

n = 2
p climb_stairs(n)
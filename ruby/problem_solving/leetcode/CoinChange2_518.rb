# 518 Coin Change 2

# @param {Integer} amount
# @param {Integer[]} coins
# @return {Integer}
def change(amount, coins)
  return 1 if amount == 0
  n = coins.size
  dp = Array.new(amount + 1, 0)

  dp[0] = 1
  for i in 0...n
    for j in coins[i]...amount+1
      dp[j] += dp[j-coins[i]]
    end
  end

  dp[-1]
end

amount = 5
coins = [1,2,5]
p change amount, coins

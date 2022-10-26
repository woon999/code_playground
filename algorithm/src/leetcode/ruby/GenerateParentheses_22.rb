# 22 Generate Parentheses

# @param {Integer} n
# @return {String[]}

@arr
def generate_parenthesis(n)
  @arr = []

  comb(1, 1, 2 * n, '(')

  @arr
end

def comb(n, pair, limit, s)
  return if pair < 0

  @arr << s if pair == 0 && n == limit

  if n < limit
    comb(n + 1, pair - 1, limit, s + ')')
    comb(n + 1, pair + 1, limit, s + '(')
  end
end

n = 1
p generate_parenthesis(n)
# 50 Pow(x, n)

# @param {Float} x
# @param {Integer} n
# @return {Float}
def my_pow(x, n)
  return 1 if n == 0

  if n.positive?
    return pow(x,n)
  else
    return pow(1/x,-n)
  end

end

def pow (x, n)
  return x if n == 1

  result = pow(x,n/2)
  result *= result

  if n.odd?
    result *=  x
  end

  return result
end

x = 2.0000
n = 10
p my_pow(x, n)
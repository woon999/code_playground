# 69 ruby Sqrt X

# @param {Integer} x
# @return {Integer}
def my_sqrt(x)
  l, r = 0, x
  while l <= r
    mid = (l+r)/2

    if mid * mid > x
      r = mid - 1
    elsif mid * mid < x
      l = mid + 1
    else
      return mid
    end
  end

  r
end

x = 8
p my_sqrt x
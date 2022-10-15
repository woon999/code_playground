# 11 ruby Container With Most Water

# @param {Integer[]} height
# @return {Integer}
def max_area(height)
  s, e  = 0, height.length-1
  max = 0

  while(s < e)
    h = [height[s], height[e]].min
    w = (e-s)*h
    max = [w, max].max

    if(height[s] > height[e])
      e -= 1
    else
      s += 1
    end
  end

  max
end

height = [1,8,6,2,5,4,8,3,7]
p max_area(height)

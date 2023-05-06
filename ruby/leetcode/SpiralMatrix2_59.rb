# 59 Spiral Matrix2

# @param {Integer} n
# @return {Integer[][]}
def generate_matrix(n)
  arr = Array.new(n){ Array.new(n) }

  y, x, dy, dx = 0, 0, 0, 1
  (0...n*n).each do |k|
    arr[y][x] = k + 1

    dy, dx = dx, -dy if arr[(y+dy)%n][(x+dx)%n]

    y += dy
    x += dx
  end

  arr
end

n = 3
p generate_matrix n
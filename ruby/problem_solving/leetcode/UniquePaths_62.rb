# 62 Unique Paths

# @param {Integer} m
# @param {Integer} n
# @return {Integer}
def unique_paths(m, n)
  map = Array.new(n) { Array.new(m) }

  (0...m).each do |col|
    map[0][col] = 1
  end

  (0...n).each do |row|
    map[row][0] = 1
  end

  (1...n).each do |row|
    (1...m).each do |col|
      map[row][col] = map[row][col-1] + map[row-1][col]
    end
  end

  map[-1][-1]
end

p unique_paths(3, 7)
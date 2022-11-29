# 120 Triangle

# @param {Integer[][]} triangle
# @return {Integer}
def minimum_total(triangle)
  return 0 if triangle.empty?
  (1...triangle.size).each do |row|
    prev = row - 1
    (0...triangle[row].size).each do |col|
      box = []
      box.push triangle[prev][col] if col < triangle[prev].size
      box.push triangle[prev][col-1] if col >= 1
      triangle[row][col] += box.min
    end
  end

  triangle.last.min
end

triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
p minimum_total triangle
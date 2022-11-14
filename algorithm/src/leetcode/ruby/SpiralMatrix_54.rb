# 54 Spiral Matrix

# @param {Integer[][]} matrix
# @return {Integer[]}
def spiral_order(matrix)
  result = []

  while matrix.length > 0
    result.append *matrix.shift

    matrix.each do |row|
      result << row.pop
    end

    result.append *matrix.pop&.reverse
    matrix.reverse.each do |row|
      result << row.shift
    end
  end

  result.compact
end

matrix = [[1,2,3],[4,5,6],[7,8,9]]
p spiral_order matrix
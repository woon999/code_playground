# 73 Set Matrix Zeroes

# @param {Integer[][]} matrix
# @return {Void} Do not return anything, modify matrix in-place instead.
def set_zeroes(matrix)
  f_row, f_col = false, false
  matrix.each_with_index do |col, y|
    col.each_with_index do |row, x|
       if row.zero?
         f_row = true if y == 0
         f_col = true if x == 0

         matrix[0][x] = 0
         matrix[y][0] = 0
       end
    end
  end

  1.upto(matrix.size-1).each do |y|
    1.upto(matrix[0].size-1).each do |x|
      matrix[y][x] = 0 if matrix[y][0] == 0 || matrix[0][x] == 0
    end
  end

  0.upto(matrix.size-1).each {|y| matrix[y][0] = 0 } if f_col
  0.upto(matrix[0].size-1).each { |x| matrix[0][x] = 0 } if f_row

  matrix
end

# matrix = [[1,1,1],[1,0,1],[1,1,1]]
# p set_zeroes matrix

# matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
# p set_zeroes matrix

matrix = [[1,0,3]]
p set_zeroes matrix
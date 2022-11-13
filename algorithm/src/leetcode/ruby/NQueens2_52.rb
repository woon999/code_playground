# 52 backtracking N_Queens 2

#  n = 4
#    col 1 2 3 4
#  row
#   1    # # # #
#   2    # # # #
#   3    # # # #
#   4    # # # #

# @param {Integer} n
# @return {Integer}
def total_n_queens(n)
  @res = []
  arr =[]
  n.times { |i| arr.push('.'*n)}
  row = 0
  backtracking(arr, row, n)

  @res.size
end

def backtracking(arr, row, n)
  return @res.push(arr.map { |s| s.dup }) if row == n

  n.times do |col|
    if is_valid_position(arr, row, col, n)
      arr[row][col] = 'Q'
      backtracking(arr, row + 1, n)
      arr[row][col] = '.'
    end
  end
end

def is_valid_position(arr, row, col, n)
  # 위
  r = row
  while r >= 0
    return false if arr[r][col] == 'Q'
    r -= 1
  end

  # 왼쪽 위 대각선
  r, c = row, col
  while c >= 0 && r >= 0
    return false if arr[r][c] == 'Q'
    r -= 1
    c -= 1
  end

  # 오른쪽 위 대각선
  r, c = row, col
  while c < n && r >= 0
    return false if arr[r][c] == 'Q'
    r -= 1
    c += 1
  end
  true
end

n = 4
p total_n_queens n
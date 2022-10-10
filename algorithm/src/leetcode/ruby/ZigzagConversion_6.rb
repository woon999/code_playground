# 6 ruby convert

# @param {String} s
# @param {Integer} num_rows
# @return {String}
def convert(s, num_rows)
  return s if num_rows == 1 || s.length == 0 || s.length == 1

  block = {}
  (0...num_rows).each do |idx|
    block[idx] = ""
  end

  isRight = true
  idx, row = 0, 0
  while(idx < s.length)
    word = s[idx]

    block[row] += word
    if isRight
      row += 1
      if(row == num_rows-1)
        isRight = false
      end
    else
      row -= 1
      if(row == 0)
        isRight = true
      end
    end
    idx += 1
  end

  answer = ""
  block.values.flatten.map { |str| answer += str }.last
end

s = "ABC"
num_rows = 1

p convert(s, num_rows)
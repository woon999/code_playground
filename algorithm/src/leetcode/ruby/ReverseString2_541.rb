# 541 Reverse String 2

def solution1(k, s)
  s.chars.each_slice(2 * k).map do |num|
    if num.size == 2 * k
      num[0...k].reverse + num[k..-1]
    elsif num.size >= k
      num[0...k].reverse + num[k..-1]
    else
      num.reverse
    end
  end.flatten.join
end

def solution2(k, s)
  0.step(s.length, 2 * k) do |i|
    s[i...(i + k)] = s[i...(i + k)].reverse
  end
  s
end

# @param {String} s
# @param {Integer} k
# @return {String}
def reverse_str(s, k)
  # solution1(k, s)
  solution2(k, s)
end

s = "abcd"
k = 2
p reverse_str s, k
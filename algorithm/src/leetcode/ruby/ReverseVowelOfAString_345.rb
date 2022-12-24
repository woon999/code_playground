# 345 Reverse Of A String

def reverse_vowels(s)
  indices, chars = [],[]
  (0...s.size).each do |i|
    char = s[i]
    if /[aeiou]/i === char
      indices.push(i)
      chars.push(char)
    end
  end

  chars.reverse!
  (0...indices.size).each do |i|
    s[indices[i]] = chars[i]
  end
  s
end

s = "hello"
p reverse_vowels s

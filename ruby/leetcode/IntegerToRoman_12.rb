# #12 Int to Roman

# @param {Integer} num
# @return {String}
def int_to_roman(num)
  data = { 1 => "I", 4 => "IV", 5 => "V", 9 => "IX", 10 => "X", 40 => "XL",
           50 => "L", 90 => "XC", 100 => "C", 400 => "CD", 500 => "D", 900 => "CM", 1000 => "M" }

  output = ""
  return data[num] if data.key?(num)

  data.keys.reverse.each do |value|
    while (num >= value)
      return output + "#{data[num]}" if data.key?(num)

      num -= value
      map_val = data[value]
      output += "#{map_val}"
    end
  end

  output
end

num = 3
p int_to_roman(num)
# 13 Roman To Int

# @param {String} s
# @return {Integer}
def roman_to_int(s)
  data = {  "I" => 1,
            "V" => 5,
            "X" => 10,
            "L" => 50,
            "C" => 100,
            "D" => 500,
            "M" => 1000 }
  # "I" => 1,  "IV" => 4, "V" => 5, "IX" => 9, "X" => 10, "XL" => 40,
  #   "L" => 50, "XC" => 90, "C" => 100, "CD" => 400, "D" => 500, "CM" => 900, "M" => 1000

  output = 0
  tmp = 0
  s.chars.each_with_index do |e, idx|
    if data[s[idx+1]] && data[e] < data[s[idx+1]]
      tmp = data[e]
    else
      output += (data[e]-tmp)
      tmp = 0
    end
  end

  output
end

s = "MCMXCIV"
p roman_to_int(s)
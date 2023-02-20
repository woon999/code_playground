# 131 Palindrome Partitioning

# @param {String} s
# @return {String[][]}
@mem = {"" => [[]]}

def partition(s)
  @mem[s] ||= (1..s.size).flat_map do |l|
    ss = s[0, l]
    ss == ss.reverse ? partition(s[l..]).map { |rest| [ss, *rest] } : []
  end
end

s = "aab"
p partition s
p @mem
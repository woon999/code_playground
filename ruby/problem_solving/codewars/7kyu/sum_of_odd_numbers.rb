def row_sum_odd_numbers(n)
  start_number = n * n - n + 1
  (0...n).map { |i| start_number + 2 * i }.sum
end

p row_sum_odd_numbers(1) == 1
p row_sum_odd_numbers(2) == 8
p row_sum_odd_numbers(13) == 2197
p row_sum_odd_numbers(19) == 6859
p row_sum_odd_numbers(41) == 68921
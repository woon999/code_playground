def before_count_positives_sum_negatives(lst)
  return [] if lst.empty?

  [lst.select { |n| n > 0 }.size, lst.select { |n| n < 0 }.sum]
end

def count_positives_sum_negatives(lst)
  return [] if lst.empty?

  [lst.count(&:positive?), lst.select(&:negative?).reduce(0, :+)]
end

puts count_positives_sum_negatives([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14, -15]) == [10, -65]
puts count_positives_sum_negatives([0, 2, 3, 0, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14]) == [8, -50]
puts count_positives_sum_negatives([1]) == [1, 0]
puts count_positives_sum_negatives([0, 0, 0, 0, 0, 0, 0, 0, 0]) == [0, 0]
puts count_positives_sum_negatives([]) == []
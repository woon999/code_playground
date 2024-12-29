# 56 merge intervals

# @param {Integer[][]} intervals
# @return {Integer[][]}
def merge(intervals)
  intervals.sort!
  result = [intervals.first]
  intervals[1...].each do |arr|
    result.last.last >= arr.first ? result.last[1] = [result.last.last, arr.last].max : result << arr
  end

  result
end

# intervals = [[1,3],[2,6],[8,10],[15,18]]
intervals = [[1,4],[0,4]]
p merge intervals
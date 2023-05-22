=begin

루비 파이버(Fiber)
- 경량 스레드를 사용하는 매우 단순한 코루틴 매커니즘
- 루비 1.9 이상에서 사용 가능

=end

require 'benchmark'

counts = Hash.new(0)
File.foreach("testfile") do |line|
  line.scan(/\w+/) do |word|
    word = word.downcase
    counts[word] += 1
  end
end
counts.keys.sort.each {|k| print "#{k}:#{counts[k]} "}


puts "\n---------------------------------"

# resume 메서드를 하면 블록 코드를 실행한다.
# Fiber.yield를 만나면 현재 블록의 실행을 중지하고, resume 메서드에 값을 반환한다.
counts = Hash.new(0)
words = Fiber.new do
  File.foreach("testfile") do |line|
    line.scan(/\w+/) do |word|
      Fiber.yield word.downcase
    end
  end
  nil
end

while word = words.resume
  counts[word] += 1
end
counts.keys.sort.each {|k| print "#{k}:#{counts[k]} "}

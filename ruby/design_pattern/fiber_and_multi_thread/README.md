# 파이버
쓰레드가 경량 프로세스라면, 파이버는 경량 쓰레드다.
파이버는 쓰레드와 달리 자발적으로 실행을 중지해야 양보할 수 있으며 커널이 아닌 사용자 공간에서 이루어진다. (비선점형, 협력적 멀티 태스킹)
따라서 같은 쓰레드의 파이버끼리의 전환은 컨텍스트 스위칭 비용은 매우 적다.

![image](https://github.com/loosie/code_playground/assets/54282927/863dfb62-dac0-41af-bfff-1979c7bbc34c)

# 코루틴
코루틴은 일시중단(suspend)했다가 재개(resume)할 수 있는 제어요소다. (try/catch, if, while같은 것이라 보면 된다)

파이버와 코루틴은 쓰레드/함수 관계와 비슷하며, 
유저수준에서 가장 큰 차이는 코루틴은 서브 루틴처럼 Caller-Callee 사이에서 전환되지만, 
파이버는 스케쥴러에 의해 순서가 정해질 수 있다.

caller가 함수를 call하고, 
함수가 caller에게 값을 return하면서 종료하는 것에 더해 return하는 대신 suspend(혹은 yield)하면 caller가 나중에 resume하여 중단된 지점부터 실행을 이어갈 수 있다.

![image](https://github.com/loosie/code_playground/assets/54282927/c73b22ab-55bc-4384-a068-97e78dd21573)


# 루비 파이버(Fiber)
- 경량 스레드를 사용하는 매우 단순한 코루틴 매커니즘
- 루비 1.9 이상에서 사용 가능
``` ruby
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
```

=begin

루비 1.9 이전의 버전에서 스레드는 이른바 그린 스레드로 구현되엇다. 이는 스레드 간의 제어권은 완전히 인터프리터 안에서만 이루어진다는 의미다.

루비 1.9에서는 운영 체제를 통해 스레드의 제어권을 변경할 수 있도록 달라졌다. 이는 분명히 개선이라고 말할 수 있지만, 아마 일반적인 기대를 충족할 만큼의 개선은 아닐 것이다.
운영체제 네이티브 스레드를 사용해서 멀티프로세서(또는 단일 프로세서의 멀티코어)에 의해 높은 처리 능력을 활용할 수 있게 되었지만 여전히 큰 문제가 남아있다.
대부분의 루비 확장 라이브러리는 오래된 스레드 모델에 맞춰 개발되었기 때문에 스레드 안전이라고 할 수 없다. (GIL이 있기 때문에 기존에는 스레드 안전 고려 x)
루비는 이에 대한 타협으로 운영 체제 네이티브 스레드를 사용하면서도, 동시에 다수의 스레드를 실행하지 않도록 되어 있다.
즉 같은 애플리케이션에서는 결코 두 개의 스레드가 병렬로 실행되는 일은 없다. (하지만 어떤 스레드들이 I/O 처리를 실행하는 동안에 다른 스레드에서 루비 코드를 실행할 수는 있다. 이 점이 운영체제 네이티브 스레드에 의해 실현된 중요한 장점 중 하나다.)

=end


require 'net/http'

pages = %w( www.rubycentral.org slashdot.org www.google.com )
threads = pages.map do |page_to_fetch|
  Thread.new(page_to_fetch) do |url|
    http = Net::HTTP.new(url, 80)
    print "Fetching: #{url}\n"
    resp = http.get('/')
    print "Got #{url}: #{resp.message}\n"
  end
end

threads.each {|thr| thr.join }

=begin

스레드는 그 스레드를 생성하는 시점에 존재하는 전역 변수, 인스턴스 변수, 지역 변수를 공유한다.
공유한다는 것이 항상 좋지만은 않다. 위 경우 세 개의 스레드에서 모두 page_to_fetch 변수를 공유하고 있다.
이는 잠재적 위험을 지닌다.

하지만 스레드 블록 내에서 작성된 지역 변수를 사용하면 이런 문제를 피할 수 있다.

=end

count = 0
thread = 10.times.map do |i|
  Thread.new do
    sleep(rand(0.1))
    Thread.current[:mycount] = count
    count += 1
  end
end

thread.each {|t| t.join; print t[:mycount], ", " }
puts "count = #{count}"
# 3, 4, 5, 9, 7, 6, 1, 8, 2, 0, count = 10
# 메인 스레드는 하위 스레드들이 끝나기를 기다린다. 그리고 각 스레드가 가진 count 값을 출력한다. 하지만 이 값은 0에서 9까지의 숫자가 아니다.


# 상호 배제
sum = 0
threads = 10.times.map do
  Thread.new do
    100_000.times do
      new_value = sum + 1
      print "#{new_value} " if new_value % 250_000 == 0
      sum = new_value
    end
  end
end
threads.each(&:join)
puts "\nsum = #{sum}"
# 250000 500000 750000
# sum = 800000

=begin
위의 값은 1,000,000 보다 작은 수가 나온다. 이는 스레드가 상호 배제를 하지 않기 때문이다.

이는 Mutex를 사용해 동기화 영역을 만들어 해결하면 된다. Mutex는 Mutual Exclusion의 약자로 상호 배제를 의미한다.
- 화장실 가려면 적절한 허가증을 가지고 있어야 한다. 이 허가증은 한 사람에게만 주어진다. 이 허가증을 가지고 있으면 화장실에 들어갈 수 있고, 화장실을 사용하는 동안에는 다른 사람이 들어올 수 없다.
- 뮤텍스는 화장실 이용 허가증이랑 비슷하다. 리소스에 대한 접근을 제어하기 위해 뮤텍스를 생성하고, 리소스를 사용할 때 뮤텍스를 잠그고, 사용이 끝나면 뮤텍스를 풀어야 한다.
=end


sum = 0
mutex = Mutex.new
threads = 10.times.map do
  Thread.new do
    100_000.times do
      mutex.lock
      new_value = sum + 1
      print "#{new_value} " if new_value % 250_000 == 0
      sum = new_value
      mutex.unlock
    end
  end
end

threads.each(&:join)
puts "\nsum = #{sum}"
# 250000 500000 750000 1000000
# sum = 1000000

# 이러한 패턴은 자주사용되므로 Mutex#synchronize 메서드를 사용하면 더 간단하게 작성할 수 있다.
sum = 0
mutex = Mutex.new
threads = 10.times.map do
  Thread.new do
    100_000.times do
      mutex.synchronize do
        new_value = sum + 1
        print "#{new_value} " if new_value % 250_000 == 0
        sum = new_value
      end
    end
  end
end

threads.each(&:join)
puts "\nsum = #{sum}"
# 250000 500000 750000 1000000
# sum = 1000000


=begin

Mutex#try_lock 뮤텍스가 잠겨있더라도 스레드 실행을 멈추고 싶지 않을 경우 사용
뮤텍스가 잠겨있으면 try_lock은 false를 반환한다.
뮤텍스가 잠겨있지 않으면 try_lock은 뮤텍스를 잠근다.

``` ruby
rate_mutex = Mutex.new
exchange_rates = ExchangeRates.new
exchange_rates.update_from_online_feed

Thread.new do
  loop do
    rate_mutex.synchronize do
      exchange_rates.update_from_online_feed
    end
  end
end

loop do
  print "Enter currency code and amount: "
  line = gets
  if rate_mutex.try_lock
    puts(exchange_rates.convert(line)) ensure rate_mutex.unlock
  else
    puts "Sorry, rates are being updated. Try again in a moment."
  end
end
```

=end



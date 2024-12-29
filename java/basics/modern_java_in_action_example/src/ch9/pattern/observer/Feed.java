package ch9.pattern.observer;

import java.util.ArrayList;
import java.util.List;

// 어떤 이벤트가 발생했을 때 한 객체가 다른 객체 리스트에 자동으로 알림을 보내야 하는 상황에서 옵저버 디자인 패턴을 사용한다.
interface Observer{
	void notify(String tweet);
}

class NYTimes implements Observer{
	@Override public void notify(String tweet) {
		if(tweet != null && tweet.contains("money")){
			System.out.println("Breaking news in NY! " + tweet);
		}
	}
}

class Guardian implements Observer{
	@Override public void notify(String tweet) {
		if(tweet != null && tweet.contains("queen")){
			System.out.println("Yet more news from London... " + tweet);
		}
	}
}

class LeMonde implements Observer{
	@Override public void notify(String tweet) {
		if(tweet != null && tweet.contains("wine")){
			System.out.println("Today cheese, wine and news! " + tweet);
		}
	}
}

interface Subject{
	void registerObserver(Observer o);
	void notifyObservers(String tweet);
}

public class Feed implements Subject{
	private final List<Observer> observers = new ArrayList<>();
	@Override public void registerObserver(Observer o) {
		this.observers.add(o);
	}

	@Override public void notifyObservers(String tweet) {
		observers.forEach(o -> o.notify(tweet));
	}

	public static void main(String[] args) {
		Feed f = new Feed();
		f.registerObserver(new NYTimes());
		f.registerObserver(new Guardian());
		f.registerObserver(new LeMonde());
		f.notifyObservers("The queen said her favourite book is Modern Java in Action!");
	}
}

package ch9.pattern.chainOfResponsibility;

// 작업 처리 객체의 체인(동작 체인 등)을 만들 때는 의무 체인 패턴을 사용한다.
// 한 객체가 어떤 작업을 처리한 다음 다른 객체로 결과를 전달하고, 다른 객체도 해야 할 작업을 처리한 다음에 또 다른 객체로 전달하는 식이다.
abstract class ProcessingObject<T>{
	protected ProcessingObject<T> successor;
	public void setSuccessor(ProcessingObject<T> successor){
		this.successor = successor;
	}

	public T handle(T input){
		T r = handleWork(input);
		if(successor != null){
			return successor.handle(r);
		}
		return r;
	}

	abstract protected T handleWork(T input);
}

class HeaderTextProcessing extends ProcessingObject<String>{
	@Override protected String handleWork(String text) {
		return "From Raoul, Mario and Alan: " + text;
	}
}

class SpellCheckerProcessing extends ProcessingObject<String>{
	@Override protected String handleWork(String text) {
		return text.replaceAll("labda", "lambda");
	}
}


public class Main {
	public static void main(String[] args) {
		ProcessingObject<String> p1 = new HeaderTextProcessing();
		ProcessingObject<String> p2 = new SpellCheckerProcessing();
		p1.setSuccessor(p2);
		String result = p1.handle("Aren't labdas really sexy?!");
		System.out.println("result = " + result);
	}
}

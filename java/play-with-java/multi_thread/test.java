import java.lang.reflect.Method;

@interface DoRepeat100{}

class Hello {

	@DoRepeat100
	public void hello(){
		System.out.println("hello!");
	}
}

public class test {
	public static void main(String[] args) {
		Hello hello = new Hello();

		try{
			Method method = hello.getClass().getDeclaredMethod("hello");

			System.out.println("어노테이션이 존재합니다.");

			if (method.isAnnotationPresent(DoRepeat100.class)) {
				for(int i=0; i<100; i++){
					hello.hello();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

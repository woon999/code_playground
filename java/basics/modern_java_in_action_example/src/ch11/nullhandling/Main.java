package ch11.nullhandling;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * null 때문에 발생하는 문제
 * 1. 에러의 근원이다.
 * 2. 코드를 어지럽힌다.
 * 3. 아무 의미가 없다.
 * 4. 자바 철학에 위배된다.
 * 5. 형식 시스템에 구멍을 만든다.
 */

public class Main {
	public String getCarInsuranceName(Person person){
		return person.getCar().getInsurance().getName();
	}

	@Test
	void NullPointerException(){
		Person person = new Person();
		assertThrows(NullPointerException.class, () -> getCarInsuranceName(person));
	}

	public String getCarInsuranceName_v2(Person person){
		if (person != null){
			Car car = person.getCar();
			if (car != null) {
				Insurance insurance = car.getInsurance();
				if (insurance != null) {
					return insurance.getName();
				}
			}
		}
		return "Unknown";
	}

	@Test
	void NullPointerException_v2(){
		Person person = new Person();
		assertEquals(getCarInsuranceName_v2(person), "Unknown");
	}

	public String getCarInsuranceName_v3(Person person){
		if (person == null) {
			return "Unknown";
		}

		Car car = person.getCar();
		if (car == null) {
			return "Unknown";
		}

		Insurance insurance = car.getInsurance();
		if (insurance == null) {
			return "Unknown";
		}
		return insurance.getName();
	}

	@Test
	void NullPointerException_v3(){
		Person person = new Person();
		assertEquals(getCarInsuranceName_v3(person), "Unknown");
	}


}

package ch11.optional;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Main {

	Person person;
	Insurance insurance;
	Car car;

	@BeforeEach
	void setUp(){
		person = new Person();
		car = new Car();
		insurance = new Insurance();

		insurance.setName("aInsurance");
		car.setInsurance(Optional.of(insurance));
		person.setCar(Optional.of(car));

	}

	@Test
	void optional_practice(){
		// 빈 Optional
		Optional<Car> optCar = Optional.empty();
		System.out.println(optCar);

		// null이 아닌 값으로 Optional 만들기
		Car car = new Car();
		Optional<Car> optCar2 = Optional.of(car);
		System.out.println(optCar2);

		// null값으로 Optional 만들기
		Optional<Car> optCar3 = Optional.ofNullable(car);
		System.out.println(optCar3);
	}

	@Test
	void map_optional(){
		// 기존 객체 null값 확인하는 로직
		Insurance insurance = new Insurance();
		String name = null;
		if(insurance != null){
			name = insurance.getName();
		}
		System.out.println(name);
		// Optional 객체 null값 확인하는 로직
		Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
		Optional<String> name2 = optInsurance.map(Insurance::getName);
		System.out.println(name2);
	}

	@Test
	void flatMap_optional(){
		Optional<Person> optPerson = Optional.of(person);
		System.out.println("optPerson = " + getCarInsuranceName(optPerson));
	}

	private String getCarInsuranceName(Optional<Person> optPerson) {
		return optPerson.flatMap(Person::getCar)
											 .flatMap(Car::getInsurance)
											 .map(Insurance::getName)
			 								 .orElse("Unknown");
	}

	@Test
	void optional_stream(){
		List<Person> persons = List.of(person);
		Set<String> carInsuranceNames = getCarInsuranceNames(persons);
		System.out.println("carInsuranceNames = " + carInsuranceNames);
	}


	public Set<String> getCarInsuranceNames(List<Person> persons){
		return persons.stream()
			.map(Person::getCar)
			.map(optCar -> optCar.flatMap(Car::getInsurance))
			.map(optIns -> optIns.map(Insurance::getName))
			.flatMap(Optional::stream)
			.collect(toSet());
	}


}

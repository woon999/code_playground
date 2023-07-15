package ch5.item33;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.HashSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("test")
public class Practice {
	@Test
	void hashMap(){
		HashSet set = new HashSet();
		set.add("1");
		set.add(1);

		set.stream().forEach(s -> System.out.println(s));
	}


	static Annotation getAnnotation(AnnotatedElement element, String annotationTypeName){
		Class<?> annotationType = null; // 비한정적 타입 토큰
		try{
			annotationType = Class.forName(annotationTypeName);
		} catch (Exception e){
			throw new IllegalArgumentException(e);
		}

		return element.getAnnotation(annotationType.asSubclass(Annotation.class));
	}
}

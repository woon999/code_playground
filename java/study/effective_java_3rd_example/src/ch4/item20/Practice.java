package ch4.item20;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ch4.item20.artist.Artist;
import ch4.item20.artist.SingerSongwriter;

public class Practice {

	@Test
	public void singerSongwriter_test(){
		SingerSongwriter artist = new Artist("alice", "piano");
		artist.sing(artist.compose(1));
		artist.strum();
		artist.actSensitive();
	}

	@Test
	public void templatemethod_test(){
		List<Integer> list = intArrayList(new int[]{1, 2, 3});
		list.stream().forEach(i -> System.out.print(i+" "));

		assertEquals(list.set(1, 10), 2);
		assertEquals(list.get(1), 10);
		assertEquals(list.size(), 3);

	}

	static List<Integer> intArrayList(int[] a){
		Objects.requireNonNull(a);

		// 다이아몬드 연산자를 이렇게 사용하는 건 자바 9부터 가능하다.
		// 더 낮은 버전을 사용한다면 <Integer>로 수정하자.
		return new AbstractList<>() {
			@Override public Integer get(int index) {
				return a[index]; // 오토박싱 (아이템 6)
			}

			@Override
			public Integer set(int index, Integer element) {
				int oldval = a[index];
				a[index] = element;
				return oldval;
			}

			@Override public int size() {
				return a.length;
			}
		};
	}
}

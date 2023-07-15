package ch2.item1.collection_collections;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        /**
         *     Type   인터페이스 -    Types    동반 클래스
         * Collection 인터페이스 - Collections 동반 클래스
         */
        Collection<String> empty1 = Collections.emptyList();
        Collection<String> empty2 = Collections.emptySet();
        Map<Object, Object> empty3 = Collections.emptyMap();

        /**
         * List 인터페이스 -  Lists 동반 클래스
         * 정적 팩토리 메서드로 객체 생성
         */
        List<Integer> emptyList = Lists.of(); // List.of()와 동일
        System.out.println(emptyList.getClass().getTypeName());
    }

    // ImmutableCollections의 emptyList()의 간략한 형태 구현
    static class Lists{
        private Lists(){}
        static <E> List<E> of() {
            return (List<E>) java.util.Collections.EMPTY_LIST; // Collections 하위타입 List 반환
        }
    }
}

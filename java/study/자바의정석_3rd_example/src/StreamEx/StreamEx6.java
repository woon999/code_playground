package StreamEx;


// 최종 연산 - collect()
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx6 {
    public static void main(String[] args) {
        Student3[] stuArr = {
                new Student3("이자바", 3, 300),
                new Student3("김자바", 1, 200),
                new Student3("안자바", 2, 100),
                new Student3("박자바", 2, 150),
                new Student3("소자바", 1, 200),
                new Student3("나자바", 3, 290),
                new Student3("감자바", 3, 180)
        };

        // 학생 이름만 뽑아서 List<String>에 저장
        List<String> names = Stream.of(stuArr).map(Student3::getName)
                        .collect(Collectors.toList());
        System.out.println("names = " + names);

        // 스트림을 배열로 변환
        Student3[] stuArr2 = Stream.of(stuArr).toArray(Student3[]::new);

        for (Student3 s : stuArr2) {
            System.out.println(s);
        }

        // 스트림을 Map<String, String>로 변환. 학생 이름이 key
        Map<String, Student3> stuMap = Stream.of(stuArr)
                                    .collect(Collectors.toMap(s->s.getName(), p->p));


        for (String s : stuMap.keySet()) {
            System.out.println("name = " + stuMap.get(s));
        }
        
        long count = Stream.of(stuArr).collect(Collectors.counting());
        long totalScore = Stream.of(stuArr)
                                .collect(Collectors.summingInt(Student3::getTotalScore));
        System.out.println("count = " + count);
        System.out.println("totalScore = " + totalScore);

        totalScore = Stream.of(stuArr)
                .collect(Collectors.reducing(0, Student3::getTotalScore, Integer::sum));
        System.out.println("totalScore = " + totalScore);
        
        Optional<Student3> topStudent = Stream.of(stuArr)
                                        .collect(Collectors.maxBy(Comparator.comparingInt(Student3::getTotalScore)));
        System.out.println("topStudent = " + topStudent);

        IntSummaryStatistics stat = Stream.of(stuArr)
                            .collect(Collectors.summarizingInt(Student3::getTotalScore));
        System.out.println("stat = " + stat);

        String stuNames = Stream.of(stuArr).map(Student3::getName)
                .collect(Collectors.joining(",","{","}"));
        System.out.println(stuNames);

        

    }
}

class Student3 implements Comparable<Student3>{
    String name;
    int ban;
    int totalScore;
    Student3(String name, int ban, int totalScore){
        this.name = name;
        this.ban = ban;
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", ban=" + ban +
                ", totalScore=" + totalScore +
                '}';
    }

    String getName() { return name;}
    int getBan() { return ban;}
    int getTotalScore() { return totalScore;}

    // 총점 내림차순을 기본 정렬로 한다.
    public int compareTo(Student3 s) {
        return s.totalScore - this.totalScore;
    }

}

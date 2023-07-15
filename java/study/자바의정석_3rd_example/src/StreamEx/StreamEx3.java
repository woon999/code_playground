package StreamEx;


// 중간연산 - mapToInt()
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx3 {
    public static void main(String[] args) {
        Student2[] stuArr= {
                new Student2("이자바", 3, 300),
                new Student2("김자바", 1, 200),
                new Student2("안자바", 2, 100),
                new Student2("박자바", 2, 150),
                new Student2("소자바", 1, 200),
                new Student2("나자바", 3, 290),
                new Student2("감자바", 3, 180)
        };


        Stream<Student2> stuStream = Stream.of(stuArr);
        stuStream.sorted(Comparator.comparing(Student2::getBan)
                .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);

        stuStream = Stream.of(stuArr);
        IntStream stuScoreStream= stuStream.mapToInt(Student2::getBan);
        IntSummaryStatistics stat = stuScoreStream.summaryStatistics();
        System.out.println("count="+stat.getCount());
        System.out.println("sum="+stat.getSum());
        System.out.println("average="+stat.getAverage());
        System.out.println("min="+stat.getMin());
        System.out.println("max="+stat.getMax());
    }
}

class Student2 implements Comparable<Student2>{
    String name;
    int ban;
    int totalScore;
    Student2(String name, int ban, int totalScore){
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
    public int compareTo(Student2 s) {
        return s.totalScore - this.totalScore;
    }

}

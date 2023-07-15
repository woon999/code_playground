package StreamEx;


// 최종 연산 - collect- groupingBy()

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx8 {
    public static void main(String[] args) {
        Student4[] stuArr = {
                new Student4("나자바", true, 1, 1, 300),
                new Student4("김지미", false, 1, 1, 250),
                new Student4("김자바", true, 1, 1, 200),
                new Student4("이지미", false,1, 2, 150),
                new Student4("남자바", true,1, 2, 100),
                new Student4("안지미", false,1, 2, 50),
                new Student4("황지미", false,1, 3, 100),
                new Student4("강지미", false,1, 3, 150),
                new Student4("이자바", true,1, 3, 200),

                new Student4("나자바", true, 2, 1, 300),
                new Student4("김지미", false, 2, 1, 250),
                new Student4("김자바", true, 2, 1, 200),
                new Student4("이지미", false,2, 2, 150),
                new Student4("남자바", true,2, 2, 100),
                new Student4("안지미", false,2, 2, 50),
                new Student4("황지미", false,2, 3, 100),
                new Student4("강지미", false,2, 3, 150),
                new Student4("이자바", true,2, 3, 200)
        };

        System.out.println("1. 단순그룹화(반별로 그룹화)");
        Map<Integer, List<Student4>> stuByBan = Stream.of(stuArr)
                .collect(Collectors.groupingBy(Student4::getBan));

        for(List<Student4> ban : stuByBan.values()){
            for(Student4 s : ban){
                System.out.println(s);
            }
        }

        System.out.println("\n2. 단순그룹화(성적별로 그룹화)");
        Map<Student4.Level, List<Student4>> stuByLevel = Stream.of(stuArr)
                .collect(Collectors.groupingBy(s-> {
                    if(s.getScore() >= 200) return Student4.Level.HIGH;
                    else if(s.getScore() >= 100) return Student4.Level.MID;
                    else return Student4.Level.LOW;
                }));

        TreeSet<Student4.Level> keySet = new TreeSet<>(stuByLevel.keySet());
        for (Student4.Level key : keySet) {
            System.out.println("[" + key + "]");

            for(Student4 s : stuByLevel.get(key)){
                System.out.println(s);
            }
            System.out.println();
        }

        System.out.println("\n3. 단순그룹화 + 통계(성적별 학생수)");
        Map<Student4.Level, Long> stuCntByLevel = Stream.of(stuArr)
                .collect(Collectors.groupingBy(s -> {
                    if(s.getScore() >= 200) return Student4.Level.HIGH;
                    else if(s.getScore() >= 100) return Student4.Level.MID;
                    else return Student4.Level.LOW;
                }, Collectors.counting()));

        for (Student4.Level key : stuCntByLevel.keySet()) {
            System.out.printf("[%s] - %d명, ", key, stuCntByLevel.get(key));
        }
        System.out.println();

        /*
        for(List<Student4> level : stuByLevel.values()){
            System.out.println();
            for(Student4 s : level){
                System.out.println(s);
            }
        }
        */

        // Map<Hak, Map<Ban, List>>
        System.out.println("\n4. 다중그룹화(학년별, 반별)");
        Map<Integer, Map<Integer, List<Student4>>> stuByHakAndBan = Stream.of(stuArr)
                .collect(Collectors.groupingBy(Student4::getHak,
                        Collectors.groupingBy(Student4::getBan)
                ));

        for(Map<Integer, List<Student4>> hak : stuByHakAndBan.values()){
            for(List<Student4> ban : hak.values()){
                for(Student4 s : ban){
                    System.out.println(s);
                }
                System.out.println();
            }
        }

        System.out.println("\n5. 다중그룹화 + 통계(학년별, 반별 1등)");
        Map<Integer, Map<Integer,Student4>> topStuByHakAndBan = Stream.of(stuArr)
                .collect(Collectors.groupingBy(Student4::getHak,
                        Collectors.groupingBy(Student4::getBan,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(Student4::getScore))
                                        , Optional::get
                                ))
                ));

        for(Map<Integer, Student4> ban : topStuByHakAndBan.values()){
            for(Student4 s : ban.values())
                System.out.println(s);
        }


        System.out.println("\n6. 다중그룹화 + 통계(학년별, 반별 성적그룹)");
        Map<String, Set<Student4.Level>> stuByScoreGroup = Stream.of(stuArr)
                .collect(Collectors.groupingBy(s-> s.getHak() + "-" +s.getBan(),
                        Collectors.mapping(s-> {
                            if(s.getScore() >= 200) return Student4.Level.HIGH;
                            else if(s.getScore() >= 100) return Student4.Level.MID;
                            else return Student4.Level.LOW;
                        },Collectors.toSet())
                ));

        Set<String> keySet2 = stuByScoreGroup.keySet();
        for(String key : keySet2){
            System.out.println("["+key+"]" +  stuByScoreGroup.get(key));
        }




    }
}


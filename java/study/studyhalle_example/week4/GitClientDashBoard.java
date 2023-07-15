package com.example.study.livestudy.week4;

import org.kohsuke.github.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class GitClientDashBoard {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력값 ( OauthToken, 조회 repo 이름)
        String my_personal_token ="--";
        String repo_name = "whiteship/live-study";
        Map<String, Integer> attendees = new HashMap<>();

        // 깃헙 인증
        GitHub github = new GitHubBuilder().withOAuthToken(my_personal_token).build();
        github.checkApiUrlValidity();

        // repo의 issue 조회
        GHRepository repo = github.getRepository(repo_name);
        List<GHIssue> issues = repo.getIssues(GHIssueState.ALL);

        for(GHIssue issue : issues){
//            System.out.println(issue.getComments());
            List<GHIssueComment> comments = issue.getComments();
            System.out.println("scanning... issueNum #"+ issue.getNumber());
            if(issue.getNumber()<16) {
                for (GHIssueComment comment : comments) {
//                    System.out.println("comment = " + comment.getUser().getLogin());
                    String username = comment.getUser().getLogin();
                    attendees.put(username, attendees.getOrDefault(username, 0) + 1);
                }
            }


        }

        // 사용자 정렬
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(attendees.entrySet());

        // 비교함수 Comparator를 사용하여 내림 차순으로 정렬
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            // compare로 값을 비교
            public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2)
            {
                // 내림 차순으로 정렬
                return obj2.getValue().compareTo(obj1.getValue());
            }
        });


        // 출력
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> member : list) {
            String attendName = member.getKey();
            int attendNum = member.getValue()>15 ? 15 : member.getValue();
            double attendRate = ((double)attendNum/15)*100;

            sb.append(attendName +"님의 참석 횟수 : 15/ "+attendNum + ", 참석율 :" + String.format("%.2f",attendRate) +"%");
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.close();
    }

}

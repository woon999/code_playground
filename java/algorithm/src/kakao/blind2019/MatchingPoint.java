package kakao.blind2019;

//blind #6 매칭 점수 - 정규식 공부
// 처음 써보는 정규식들 열심히 공부했다! 19년도 문제는 다 어려운 것 같다ㅎㅎ

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchingPoint {

	public static void main(String[] args) {
//		String word = "blind";
//		String[] pages = {
//				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n <a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
//				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
//				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>" };

		String word = "Muzi";
		String[] pages = {
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>" };

		System.out.println(solution(word, pages));
	}

	public static int solution(String word, String[] pages) {
		int numOfPages = pages.length;

		Map<String, Page> pageData = new HashMap<>();
		Map<String, List<String>> pageLink = new HashMap<>();
		Pattern pageUrlPattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
		Pattern outUrlPattern = Pattern.compile("<a href=\"(\\S*)\"");
		Pattern wordPattern = Pattern.compile("\\b(?i)"+word+"\\b");
		Matcher matcher;
		String home = "";
		String body = "";
		for (int i = 0; i < numOfPages; i++) {
			String page = pages[i];
			matcher = pageUrlPattern.matcher(page);
			if (matcher.find()) {
				home = matcher.group(1);
			}
			
			matcher = outUrlPattern.matcher(page);
			List<String> urlList = new ArrayList<>();
			while (matcher.find()) {
				String out = matcher.group(1);
				urlList.add(out);
			}
			pageLink.put(home, urlList);

			body = page.split("<body>")[1];
			body = body.split("</body>")[0].replaceAll("[0-9]", " ");
			matcher = wordPattern.matcher(body.toLowerCase());
			int basicScore = 0;
			while (matcher.find()) {
				basicScore++;
			}

//			System.out.println(home);
//			System.out.println("기본 점수 : " + basicScore);
//			System.out.println("외부 링크 점수 : " + pageLink.get(home).size());
//			System.out.println("다른 페이지에게 적용할 링크 점수 : " + ((double)basicScore/pageLink.get(home).size()) );
			pageData.put(home,
					new Page(i, ((double) basicScore / pageLink.get(home).size()), basicScore));
		}

		for (String key : pageLink.keySet()) {
			Page posPage = pageData.get(key);
//			System.out.println("#" + key +" , " + posPage.idx);
			for (String out : pageLink.get(key)) {
				if(pageData.containsKey(out)) {
//					System.out.println("##" + out);
					Page outPage = pageData.get(out);
					outPage.total += posPage.out;
				}
			}
		}

//		for (String key : pageLink.keySet()) {
//			Page posPage = pageData.get(key);
//			System.out.println(posPage.total);
//		}

		List<Page> res = new ArrayList<>(pageData.values());
		Collections.sort(res);
//		System.out.println(res.get(0).idx);

		return res.get(0).idx;
	}

	static class Page implements Comparable<Page> {
		int idx;
		double out;
		double total;

		public Page(int idx,double out, double total) {
			this.idx = idx;
			this.out = out;
			this.total = total;
		}

		@Override
		public int compareTo(Page o) {
			// TODO Auto-generated method stub
			double a = o.total-this.total;
			if(a==0) {
				return Integer.compare(this.idx, o.idx);
			}else {
				return Double.compare(o.total, this.total);	
			}
		}
	}
}

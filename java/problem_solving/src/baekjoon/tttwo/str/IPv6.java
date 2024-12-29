package baekjoon.tttwo.str;

// #3107 str IPv6 
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class IPv6 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ipv6 = br.readLine();
		if(ipv6.contains("::")) {
			ipv6 = ipv6.replace("::", ":group:");
		}
		
		List<String> ipv6s  = Stream.of(ipv6.split(":")).collect(Collectors.toList()); 
		List<String> fullIps = new ArrayList<>();
		for(int i=0; i<ipv6s.size(); i++) {
			String s = ipv6s.get(i);
			if(s.isEmpty()) continue;
			while(s.length() <4) {
				s = "0"+s;
			}
			fullIps.add(s);
		}
		
		
		int groupLen = 8 - fullIps.size() + 1;
		String[] ipFullAddresses = new String[8];
		int idx = 0;
		for(String ip : fullIps) {
			if(ip.equals("group")) {
				while(groupLen-- > 0) {
					ipFullAddresses[idx++] = "0000";
				}
			}else {
				ipFullAddresses[idx++] = ip;
			}
		}
		String res = String.join(":", ipFullAddresses);
		System.out.println(res);
	}
}

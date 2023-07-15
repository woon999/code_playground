package ch2.item5;

import java.util.HashSet;
import java.util.Set;

public class Lexicon {
	private Set<String> dict;

	public Lexicon(){
		init();
	}

	public void init(){
		dict = new HashSet<>();
		for(int i=0; i<26; i++){
			dict.add((char)(i+65)+"");
		}
	}

}

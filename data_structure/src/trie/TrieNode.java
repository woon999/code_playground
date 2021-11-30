package trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
	private Map<Character, TrieNode> child;
	private char key; // 디버깅용
	private boolean output;

	public TrieNode(){}

	public Map<Character, TrieNode> getChildNodes(){
		if(child==null){
			child = new HashMap<>();
		}
		return this.child;
	}

	public void setContent(char key){
		this.key = key;
	}

	public char getContent(){
		return key;
	}
	public boolean isOutput(){
		return this.output;
	}

	public void setOutput(boolean output){
		this.output = output; }

}

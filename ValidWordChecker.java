package games;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ValidWordChecker {
	List<String> wordList;
	public ValidWordChecker() {
		BufferedReader dict;
		String fileName = "C:\\Users\\pc\\Desktop\\web_1400_honeymouse\\workspace\\Stuff\\src\\games\\wordlist2.txt";
		try {
			dict = new BufferedReader(new FileReader(fileName));
			wordList = new ArrayList<String>();
			String line;
			while((line = dict.readLine()) != null) {
			    wordList.add(line);
			}
			dict.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isValid(String word) {
		int i = wordList.indexOf(word);
		if (i == -1) return false;
		wordList.remove(i);
		return true;
	}
	
	public static void main(String[] args) {
		ValidWordChecker checker = new ValidWordChecker();
		Scanner scan = new Scanner(System.in);
		
	}
}

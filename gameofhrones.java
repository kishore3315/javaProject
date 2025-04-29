package day11;
import java.util.HashMap;
public class gameofhrones {
	public static void main(String[] args) {
		String s="aabbccddef";
		String res=canFormPalindrome(s);
		System.out.println(res);
	}
	private static String canFormPalindrome(String s) {
		HashMap<Character, Integer> charcount= new HashMap<Character, Integer>();
		for(char c:s.toCharArray()){
			int f=charcount.getOrDefault(c, 0)+1;
			charcount.put(c,f);
		}
		System.out.println(charcount);
		int oddc=0;
		for(int count:charcount.values())
		{
			if(count%2!=0)
				oddc++;
			if(oddc>1)
				return "No";
		}
		return "Yes";
	}
	
}

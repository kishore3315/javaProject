package day11;

import java.util.HashSet;

public class longsubstringwithoutduplicates {
	public static void main(String[] args) {
		String s="bbbbb";
		int len=LongestSub(s);
		System.out.println(len);
	}
	private static int LongestSub(String s) {
		// TODO Auto-generated method stub
		HashSet<Character> hs= new HashSet<Character>();
		int i=0,res=0;
		for(int j=0;j<s.length();j++)
		{
			while(hs.contains(s.charAt(j)))
			{
				hs.remove(s.charAt((i)));
				i++;
			}
			hs.add(s.charAt(j));
			res=Math.max(res, j-i+1);
		}
		return res;
	}
}

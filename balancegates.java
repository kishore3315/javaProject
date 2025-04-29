package day11;

public class balancegates {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
String s=")))(((";
int c=0;
for(int i=0;i<s.length();i++)
{
	if(s.charAt(i)=='(')
		c++;
	else
		c--;
	if(c==-1)
		break;
}

if(c==0)
	System.out.println("Balance gates");
else
	System.out.println("Not Balance gates");
	
	
	}
	
}

package day11;
import java.util.Arrays;
public class largestinarray {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {1,8,7,4,6,3};
		int n=arr.length;
		//with O(nlogn) time complexity
		/*Arrays.sort(arr);
		System.out.println(arr[n-1]);*/
		//withO(n) time complexity
		int max=Integer.MIN_VALUE;
		for(int i=0;i<n;i++)
		{
			if(arr[i]>max)
			{
				max=arr[i];
			}
		}
		System.out.println(max);
	}
}
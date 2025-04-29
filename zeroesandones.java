package day11;
import java.util.Arrays;
public class zeroesandones {

	public static void main(String[] args) {
		int[] a= {1,0,1,0,1};
		int n=a.length;
		/*with time complexity of O(nlogn)
		Arrays.sort(a);
		for(int i=0;i<n;i++) {
			System.out.print(a[i]+" ");
		}*/
		int l=0,r=n-1;
		while(l<=r) {
			if(a[l]==0) {
				l++;
			}
			else {
				int temp=a[l];
				a[l]=a[r];
				a[r]=temp;
			}
			if(a[r]==1) {
				r--;
			}
		}
		for(int i=0;i<n;i++) {
			System.out.print(a[i]+" ");
		}
	}

}

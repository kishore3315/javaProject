package day11;

public class superiorelement {
	public static void main(String[] args) {
		int[] a= {10,22,12,3,0,6};
		int n=a.length;		
		/*boolean b=true;
		for (int i=0;i<n;i++) {
			b=true;
			for(int j=i+1;j<n;j++) {
				if(a[j]>a[i]) {
					b=false;
					break;
				}
			}
			if(b==true) {
				System.out.println(a[i]);
			}
		}*/
		int max=a[n-1];
		System.out.println(max);
		for(int i=n-2;i>0;i--) {
			if(a[i]>max) {
				max=a[i];
				System.out.println(max);
			}
		}
	}
}
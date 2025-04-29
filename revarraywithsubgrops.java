package day11;
public class revarraywithsubgrops {
	public static void main(String[] args) {
		int a[]= {1,2,3,4,5,6,7,8,9,10,11,12};
		int n=a.length;
		int k=3;
		int l;
		int r;
		for(int i=0;i<n;i=i+k) {
			l=i;
			r=i+k-1;
			while(l<=r) {
			int temp=a[l];
			a[l]=a[r];
			a[r]=temp;
			l++;
			r--;
			}
		}
		for(int i=0;i<n;i++) {
			System.out.print(a[i]+" ");
		}
		/*if k is not multiple of k
		int arr[]= {1,2,3,4,5,6,7,8,9,10,11,12};
		int n=arr.length;
		int k=10;
		int l;
		int r;
		for(int i=0;i<n;i=i+k)
		{
			 l=i;
			 if (i+k-1<n-1)
				 r=i+k-1;
			 else
				 r=n-1;
			while(l<=r)
			{
				int temp=arr[l];
				arr[l]=arr[r];
				arr[r]=temp;
				l++;
				r--;
			}

		}
		for(int i=0;i<n;i++){
			System.out.print(arr[i]+" ");
		}*/
	}
}
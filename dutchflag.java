package day11;

public class dutchflag {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {0,1,1,1,0,1,2,1,2};
		int n=arr.length;
		int low=0,mid=0,high=n-1;
		while(mid<=high) {
			switch(arr[mid]){
			case 0:
				swap(arr,mid,low);
				low++;
				mid--;
				break;
			case 1:
				mid++;
				break;
			case 2:
				high--;
				break;
			}
		}
	}
	private static void swap(int arr[],int i,int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
}

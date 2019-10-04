import java.util.*;
// problem is [10,20,30,40] we have to divide the array  into  k partitions such that max sum of partition is minimum 
class PartitionProblem
{
	// function to sum from i to j both inclusive
	static int sum(int arr[],int i,int j)
	{
		int s=0;
		for(int k=i;k<=j;k++)
		{
			s+=arr[k];
		}
		return s;
	}
	// simple recursion to calculate above
	// concept is based on that first calculate min of(k-1 partitions)[calculated due to recursion] and one left one partition is found by sum
	// ex [10,20,30,40] K=3 then 
	// in the for loop
	// initial->0
	// sum from arr[initial:i] + partition(k-1's sum from i+1 : arr.length-1)
	static int inefficient(int arr[],int initial,int k,int best)
	{

		if(k==1){return sum(arr,initial, arr.length-1);}
		else if(initial==arr.length){return best;}
		for(int i=initial;i<=arr.length-k;i++)
		{
			best=Math.min(best,Math.max(inefficient(arr,i+1,k-1,best),sum(arr,initial,i)));
		}
		return best;
	}
	// n^3 time complexity
	static int painterpartitionWithDp(int arr[],int K)
	{
		
		int dp[][]=new int[K+1][arr.length+1];
		int sum=arr[0];
		// below is the base case ie when the board(or k) is 1 then ans is simply sum of all the eleents upto i(index in array)
		dp[1][1]=sum;
		for(int i=2;i<=arr.length;i++)
		{
			sum+=arr[i-1];
			dp[1][i]=sum;
		}
		// second base case is that board is k and element is 1 then ans is first element
		for(int i=1;i<=K;i++)
		{
			dp[i][1]=arr[0];
		}
		// when k is more than 2 
		for(int k=2;k<=K;k++)
		{
			// when arr length is more than 2
			for(int j=2;j<=arr.length;j++)
			{
				int best=Integer.MAX_VALUE;
				// partition array[1:j] into k starting from dp[k][p] to arr[p:j];
				for(int p=1;p<=j;p++)
				{
					best=Math.min(best,Math.max(dp[k-1][p],sum(arr,p,arr.length-1)));
				}
				dp[k][j]=best;

			}
			
		}
		return dp[K][arr.length];	
	}
	public static void main(String args[])
	{
		int arr[]={40,20,30,40};
		System.out.println(inefficient(arr,0,2,Integer.MAX_VALUE));
		System.out.println(dp(arr, 2));

	}
}
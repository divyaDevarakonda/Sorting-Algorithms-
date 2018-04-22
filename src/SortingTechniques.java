
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SortingTechniques {
	

		final int MAX_SIZE = 10000000;
		public static int depth=0;
		// Set this to true if you wish the arrays to be printed.
		final static boolean OUTPUT_DATA = false;
		
		public static String sortAlg= null;
		public static  int size = 0;
		
		public static void main(String[] args) {
			readInput();
			int [] data = new int[size];
			GenerateSortedData(data, size);
			Sort(data, size,"Sorted Data");

			GenerateNearlySortedData(data, size);
			Sort(data, size,"Nearly Sorted Data");
			
			GenerateReverselySortedData(data, size);
			Sort(data, size,"Reversely Sorted Data");
			
			GenerateRandomData(data, size);
			Sort(data, size,"Random Data");
				
			System.out.println("\nProgram Completed Successfully.");
			
		}
		
		@SuppressWarnings("resource")
		public static void readInput(){
			System.out.println("  I:\tInsertion Sort");
			System.out.println("  M:\tMergeSort");
			System.out.println("  Q:\tQuickSort");
			System.out.println("  S:\tSTLSort");
		    System.out.println("Enter sorting algorithm:");
		    Scanner reader = new Scanner(System.in);
		    sortAlg = reader.next();
		    System.out.println(sortAlg);
			String sortAlgName = "";
			
			if(sortAlg.equals("I"))
				sortAlgName = "Insertion Sort";
			else if(sortAlg.equals("M"))
				sortAlgName = "MergeSort";
			else if(sortAlg.equals("Q")){
				sortAlgName = "QuickSort";
				
			}
			else if(sortAlg.equals("S"))
				sortAlgName = "STLSort";
			else {
				System.out.println("Unrecognized sorting algorithm Code:"+sortAlg);
				System.exit(1);
			}
			System.out.println("Enter input size: ");
		    size = reader.nextInt();
			System.out.println("\nSorting Algorithm: " + sortAlgName);
	        System.out.println("\nInput Size = "  + size);
		}
		
		/******************************************************************************/

		public static void GenerateSortedData(int data[], int size)
		{
			int i;
			
			for(i=0; i<size; i++)
				data[i] = i * 3 + 5;
		}
		/*****************************************************************************/
		public static void GenerateNearlySortedData(int data[], int size)
		{
			int i;
			
			GenerateSortedData(data, size);
			
			for(i=0; i<size; i++)
				if(i % 10 == 0)
					if(i+1 < size)
						data[i] = data[i+1] + 7;
		}
		/*****************************************************************************/

		public static void GenerateReverselySortedData(int data[], int size)
		{
			int i;
			
			for(i = 0; i < size; i++)
				data[i] = (size-i) * 2 + 3;
		}
		/*****************************************************************************/

		public static void GenerateRandomData(int data[], int size)
		{
			int i;
			for(i = 0; i < size; i++)
				data[i] = new Random().nextInt(10000000);
		}
		/*****************************************************************************/

		
		public static void Sort(int[] data, int size,  String string)
		{

			System.out.print("\n"+string+":");
			if (OUTPUT_DATA)
			{
				printData(data, size, "Data before sorting:");
			}

			// Sorting is about to begin ... start the timer!
			long start_time = System.nanoTime();
				if (sortAlg.equals("I"))
				{
				InsertionSort(data, size);
				}
				else if (sortAlg.equals("M"))
				{
				MergeSort(data, 0, size-1);
				}
				else if (sortAlg.equals("Q"))
				{
				QuickSort(data, 0, size-1);
				System.out.println("The depth of the tree is: " +depth);
				}
				else if (sortAlg.equals("S"))
				{
				STLSort(data, size);
				}
			else
			{
				System.out.print("Invalid sorting algorithm!");
				System.out.print("\n");
				System.exit(1);
			}

			// Sorting has finished ... stop the timer!
			
			double elapsed = System.nanoTime()-start_time;
			elapsed=elapsed/1000000;


			if (OUTPUT_DATA)
			{
				printData(data, size, "Data after sorting:");
			}


			if (IsSorted(data, size))
			{
				System.out.print("\nCorrectly sorted ");
				System.out.print(size);
				System.out.print(" elements in ");
				System.out.print(elapsed);
				System.out.print("ms");
			}
			else
			{
				System.out.print("ERROR!: INCORRECT SORTING!");
				System.out.print("\n");
			}
			System.out.print("\n-------------------------------------------------------------\n");
		}
		
		/*****************************************************************************/

		public static boolean IsSorted(int data[], int size)
		{
			int i;
			
			for(i=0; i<(size-1); i++)
			{
				if(data[i] > data[i+1])
					return false;
			}
			return true;
		}
		
		/*****************************************************************************/

		public static void InsertionSort(int data[], int size)
			{
				//Write your code here
				int temp,j;
				for(int i=1;i<data.length;i++){
					temp= data[i];
						for(j=i-1;j>=0;j--)
						{
							if(data[j] > temp)
								data[j+1]= data[j];
							else
								break;
						}
					data[j+1] = temp;
				}
				
				//System.out.println("InserionSort");
				
			}
		/*****************************************************************************/

		public static void MergeSort(int data[], int lo, int hi)
		{
			//Write your code here
			if(lo<hi){
			int mid = (lo + hi)/2;
			MergeSort(data, lo, mid);
			MergeSort(data, mid+1, hi);
			Merge(data, lo, mid, hi);
			//You may create other functions if needed 
			//System.out.println("MergeSort");
			
			
			}
		}
		private static void Merge(int[] data, int lo, int mid, int hi) {

			int MAX;
			int i=0, j=0;
			int k=0;
			int n1= mid-lo+1;
			int n2= hi-mid;
			//System.out.println("n1 "+n1+"n2 "+n2);
			
			int[] Left= new int[n1];
			int[] Right= new int[n2];
			MAX = Math.max(Left[n1-1], Right[n2-1]);
			Left[n1-1]= MAX;
			Right[n2-1]=MAX;
			for(i=0; i<n1;i++)
				Left[i] = data[lo+i];
			for(j=0; j<n2;j++)
				Right[j] = data[mid+1+j];
			
			k=lo;
			for(i=0,j=0;i<n1 && j<n2;k++)
			{
				if(Left[i]<= Right[j])
				{
					data[k]=Left[i];
					i++;
				}
				else
				{
					data[k]=Right[j];
					j++;
				}
			
			}
			while(i<n1)
			{
				data[k]=Left[i];
				i++;
				k++;
			}
			while(j<n2)
			{
				data[k]=Right[j];
				j++;
				k++;
			}
		
		}

		/*****************************************************************************/

		public static int QuickSort(int data[], int lo, int hi)
		{
			//Write your code here
			
			if(lo >= hi)
				return 0;
//			if((hi-lo) <= 40){
//				InsertionSort(data, data.length);
//				return 1;
//			}
				int pivot= partition(data, lo, hi);
				//int pivot = partitionWithMedian(data, lo, hi);
				int leftSort=1+QuickSort(data,lo,pivot-1);
				int rightSort=1+QuickSort(data, pivot+1, hi);
				if(leftSort<rightSort){
					depth = rightSort;
					return rightSort;
				}	
				else{
					depth = leftSort;
					return leftSort;
				}
			
		}
		
		private static int partition(int[] data, int lo, int hi) {
			//int q = data[hi];    pivot at last quick sort. uncomment this to take pivot as last element
			int range = (hi-lo) +1;
			int random = (int) ((Math.random()* range)+lo);
			swap(random,hi,data);
			int i= lo -1;
			for(int j=lo;j<=hi-1;j++){
				if(data[j]<data[hi])
				{
					swap(j, i+1, data);
					i++;
				}
			}
			swap(hi, i+1, data);
			return i+1;
		}

		/*****************************************************************************/

		
		public static int partitionWithMedian(int[] data, int lo, int hi)//Part 2(2)
		{
		int range = (hi-lo) +1;
		int[] r= new int[3];
		for(int a=0; a<3; a++)
		{
			r[a]=(int) ((Math.random()* range)+lo);
		}
		int median;
		median= Math.max (Math.min (r[0],r[1]), Math.min (Math.max(r[0],r[1]),r[2]));
		swap(median,hi,data);
		int j=lo-1;
		for(int i=lo; i<hi;i++)
		{
			if(data[i]< data[hi])
					{
					swap(i,j+1,data);
					j++;
					}
		
		}
		swap(hi,j+1,data);
		return j+1;
		}

		
		public static void STLSort(int data[], int size)
		{
			//Write your code here
			//Your code should simply call the STL sorting function  
			Arrays.sort(data);
			//System.out.println("STLSort");
			
		}
		/*****************************************************************************/
		
		public static void swap(int x , int y ,int data[])
		{
			int temp = data[x];
			data[x] = data[y];
		    data[y] = temp;
		}
		
		/*****************************************************************************/
		
		public static void printData(int[] data, int size, String title)
		{
			int i;

			System.out.print("\n");
			System.out.print(title);
			System.out.print("\n");
			for (i = 0; i < size; i++)
			{
				System.out.print(data[i]);
				System.out.print(" ");
				if (i % 10 == 9 && size > 10)
				{
					System.out.print("\n");
				}
			}
		}


}

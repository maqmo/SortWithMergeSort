package mergesort;

import java.util.Arrays;
import java.util.Random;

public class MergeSorter {

	int[] intArray 				= null;

	MergeSorter (int[] intArray) {
		this.intArray = intArray;
	}

	public int[] sort() {
		//int[] result = intArray.clone();
		return sortRecurse(intArray);

	}

	private int[] sortRecurse(int[] input) {

		if (input.length < 2)																//corner case
			return input;

		int[] left;
		int[] right;
		int midpoint = input.length / 2;

		if (input.length % 2 != 0)
			midpoint ++;

		left = Arrays.copyOfRange(input, 0, midpoint);
		right = Arrays.copyOfRange(input, midpoint, input.length);

		sortRecurse(left);
		sortRecurse(right);

		merge( left, right, input);
		return input;
	}

	private void merge(int[] source1, int[] source2, int[] destination) {

		int index1 = 0;
		int index2 = 0;
		boolean addFromLeft = false;

		for (int index = 0; index < destination.length; index ++) {

			//three cases: (index1 pointer not at the edge yet )	(add? depends), 
			//(left value bigger than right) 						(always add), 
			// (index2 pointer not at edge yet)						(always add); 
			//-->													 depends && (always|| always)	
			//cant compare array values until making sure index isnt over the edge yet; else, ArrayIndexOutOfBoundsException

			if ( index1 < source1.length && index2 < source2.length)	{
				addFromLeft = source1[index1] > source2[index2]; }
			else if (index1 > source1.length - 1) 	
				addFromLeft = false; 
			else
				addFromLeft = true;


			if(addFromLeft) {
				destination[index] = source1[index1];
				index1++;
			}
			else {
				destination[index] = source2[index2];
				index2 ++;
			}

		}
	}

	public static int[] generateRandomArray(int length) {
		Random rand = new Random();
		int[] result = null;
		if (length > 0){
			result = new int[length];

			for (int i = 0; i < result.length; i ++) {
				result[i] = rand.nextInt(length +8);
			}	
			return result;
		}
		return result;

	}

	public static void main(String[] args) {

		double before = System.currentTimeMillis();
		int[] testArr = MergeSorter.generateRandomArray(20);
		System.out.println("Before Sorting: " + Arrays.toString(testArr));
		MergeSorter test = new MergeSorter(testArr);
		int [] sorted = test.sort();
		double after = System.currentTimeMillis();
		//System.out.println("Original Array: " + Arrays.toString(testArr));
		System.out.println("Result: " + Arrays.toString(sorted) );
		//System.out.println("Original Array First, Mid, and Last Values: " + sorted[0] + " " +sorted[sorted.length / 2] + " " + sorted[sorted.length-1]);
		System.out.println((double)(after - before) / 1000 + "s");

	}
}

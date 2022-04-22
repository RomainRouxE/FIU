/*
 * Challenge 5 : Comparing Sorting Algorithms
 * PID: 6322237 Section: COP 3804 Due: 03/28/2022
 * SortingAlgos class: driver.
 *
 * Summary: Sort array using different sorting algo and compare the sorting time
 */
package sortingalgos;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

// SortingAlgos class
public class SortingAlgos {

    public static int[] myOriginalUnsortedArray1;
    public static int[] myCopyUnsortedArray2;
    public static long bubbleSortDuration, quickSortDuration, selectionSortDuration, insertionSortDuration;

    // main method: call every function
    public static void main(String[] args) {
        // call func to generate array of 10 number
        myOriginalUnsortedArray1 = generateRanNums(10);
        myCopyUnsortedArray2 = new int[myOriginalUnsortedArray1.length];

        // Selection sort
        System.out.println("Selection Sort");
        copyRanNums(myOriginalUnsortedArray1, myCopyUnsortedArray2);
        System.out.println("Before Selection Sort: ");
        printArray(myCopyUnsortedArray2);
        selectionSort(myCopyUnsortedArray2);
        System.out.println("After Selection Sort: ");
        printArray(myCopyUnsortedArray2);
        System.out.println();

        // Insertion sort
        System.out.println("Insertion Sort");
        copyRanNums(myOriginalUnsortedArray1, myCopyUnsortedArray2);
        System.out.println("Before Insertion Sort: ");
        printArray(myCopyUnsortedArray2);
        insertionSort(myCopyUnsortedArray2);
        System.out.println("After Insertion Sort: ");
        printArray(myCopyUnsortedArray2);
        System.out.println();

        // Bubble sort
        System.out.println("Bubble Sort");
        copyRanNums(myOriginalUnsortedArray1, myCopyUnsortedArray2);
        System.out.println("Before Bubble Sort: ");
        printArray(myCopyUnsortedArray2);
        bubbleSort(myCopyUnsortedArray2);
        System.out.println("After Bubble Sort: ");
        printArray(myCopyUnsortedArray2);
        System.out.println();

        // Quick sort
        // since quick sort is recursive i calc the time here
        System.out.println("Quick Sort");
        copyRanNums(myOriginalUnsortedArray1, myCopyUnsortedArray2);
        System.out.println("Before Quick Sort: ");
        printArray(myCopyUnsortedArray2);
        long quickStart, quickEnd;
        quickStart = System.nanoTime();
        quickSort(myCopyUnsortedArray2, 0, myCopyUnsortedArray2.length - 1);
        quickEnd = System.nanoTime();
        quickSortDuration = quickEnd - quickStart;
        System.out.println("After Quick Sort: ");
        printArray(myCopyUnsortedArray2);
        System.out.println();

        // Compare time method
        compareSortTimes();

        // call func to generate array of 10 number
        myOriginalUnsortedArray1 = generateRanNums(20000);
        myCopyUnsortedArray2 = new int[myOriginalUnsortedArray1.length];

        // Selection sort
        copyRanNums(myOriginalUnsortedArray1, myCopyUnsortedArray2);
        selectionSort(myCopyUnsortedArray2);

        // Insertion sort
        copyRanNums(myOriginalUnsortedArray1, myCopyUnsortedArray2);
        insertionSort(myCopyUnsortedArray2);

        // Bubble sort
        copyRanNums(myOriginalUnsortedArray1, myCopyUnsortedArray2);
        bubbleSort(myCopyUnsortedArray2);

        // quick sort - again calc time here since its recursive
        copyRanNums(myOriginalUnsortedArray1, myCopyUnsortedArray2);
        quickStart = System.nanoTime();
        quickSort(myCopyUnsortedArray2, 0, myCopyUnsortedArray2.length - 1); // Extra Credit!
        quickEnd = System.nanoTime();
        quickSortDuration = quickEnd - quickStart;

        // Compare time method
        compareSortTimes();
    }

    // printArray method: print each value in array
    public static void printArray(int[] anArray) {
        // for-loop to print array
        for (int i = 0; i < anArray.length; i++) {
            System.out.print(anArray[i] + " ");
        }
        System.out.println();

    }

    // generateRanNums method: generate random num array depanding of the size
    public static int[] generateRanNums(int num) {
        int[] anArray;
        // 1. Using Random class, generate in a for-loop 20K numbers in the range of 1
        // to 5000
        Random myRan = new Random();

        if (num == 10) {
            anArray = new int[num];
            // create a 10-element array
            // for-loop for generating 10 numbers between 1 & 100
            for (int i = 0; i < anArray.length; i++) {
                anArray[i] = myRan.nextInt(100) + 1;
            }
        } else {
            anArray = new int[20000];
            // create a 20,000 - element array
            // for-loop for generating 20,000 numbers between 1 & 5000
            for (int i = 0; i < anArray.length; i++) {
                anArray[i] = myRan.nextInt(5000) + 1;
            }
        }
        return anArray;
    }

    // cpoyRanNums method: copy array1 into array 2
    public static void copyRanNums(int[] fromArray, int[] toArray) {
        for (int i = 0; i < fromArray.length; i++) {
            toArray[i] = fromArray[i];
        }
    }

    // selectionSort method: selection sotr algo
    public static void selectionSort(int[] anyArray) {
        long startSort, endSort;
        startSort = System.nanoTime();

        for (int i = 0; i < anyArray.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < anyArray.length; j++) {
                if (anyArray[j] < anyArray[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = anyArray[minIndex];
            anyArray[minIndex] = anyArray[i];
            anyArray[i] = temp;
        }
        endSort = System.nanoTime();
        selectionSortDuration = endSort - startSort;
        // System.out.println(selectionSortDuration + " nanosec");
    }

    // insertionSort method: insertionSort algo
    public static void insertionSort(int[] anyArray) {
        int j;
        long startSort, endSort;
        startSort = System.nanoTime();

        for (int i = 1; i < anyArray.length; ++i) {
            int key = anyArray[i];

            for (j = i - 1; j >= 0 && anyArray[j] > key; j--) {
                anyArray[j + 1] = anyArray[j];
            }
            anyArray[j + 1] = key;
        }
        endSort = System.nanoTime();
        insertionSortDuration = endSort - startSort;
    }

    // bubbleSort method: bubbleSort algo
    public static void bubbleSort(int[] anyArray) {
        long startSort, endSort;
        startSort = System.nanoTime();

        for (int i = 0; i < anyArray.length - 1; i++) {
            for (int j = 0; j < anyArray.length - i - 1; j++) {
                if (anyArray[j] > anyArray[j + 1]) {
                    int temp = anyArray[j];
                    anyArray[j] = anyArray[j + 1];
                    anyArray[j + 1] = temp;
                }
            }
        }
        endSort = System.nanoTime();
        bubbleSortDuration = endSort - startSort;
    }

    // quickSort method: quickSort algo
    public static void quickSort(int[] anyArray, int left, int right) {
        int idx = partition(anyArray, left, right);
        if (left < idx - 1) {
            quickSort(anyArray, left, idx - 1);
        }
        if (right > idx) {
            quickSort(anyArray, idx, right);
        }
    }

    // partition method: part of quickSort algo
    public static int partition(int[] array, int left, int right) {
        int pivot = array[left];
        while (left <= right) {
            while (array[left] < pivot) {
                left++;
            }
            while (array[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
                left++;
                right--;
            }
        }
        return left;
    }

    // compareSortTimes method: compare each time and display rank
    public static void compareSortTimes() {
        // Rank the time of each sort method
        // Use of hashmap and treemap to rank because... why not :) funnier than forest
        // of if
        Map<String, Long> map = new HashMap<>();
        map.put("bubbleSortDuration", bubbleSortDuration);
        map.put("quickSortDuration", quickSortDuration);
        map.put("selectionSortDuration", selectionSortDuration);
        map.put("insertionSortDuration", insertionSortDuration);

        TreeMap<String, Long> sorted = new TreeMap<>();
        sorted.putAll(map);

        int i = 1;
        System.out.println("\nSort time rank for " + myOriginalUnsortedArray1.length + " numbers:");
        for (Map.Entry<String, Long> entry : sorted.entrySet()) {
            System.out.println(i + "- Sort: " + entry.getKey() + ", time: " + entry.getValue() + "ns");
            i++;
        }
    }
}

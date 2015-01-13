import java.lang.Integer;

public class Heaps {

  // Have to hard code this in there
  private static int heapSize = 10;

  public static void main(String[] args) {
    // int[] arr = {0, 16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
    // printArray(maxHeapify(arr, 2));

    int[] arr2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    printArray(buildMaxHeap(arr2));
    printArray(heapDelete(arr2, 2));

    // int[] arr3 = {0, 10, 9, 6, 8, 4, 7, 2, 3, 1, 5};
    // printArray(heapSort(arr3));
  }

  // Very similar to extractHeapMaximum()
  private static int[] heapDelete(int[] arr, int elem) {
    arr[elem] = arr[heapSize];
    heapSize--;
    maxHeapify(arr, elem);
    return produceSubarray(arr);
  }

  private static int heapMaximum(int[] arr) {
    return arr[1];
  }

  private static int extractHeapMaximum(int[] arr) {
    int max = arr[1];
    arr[1] = arr[heapSize];
    heapSize--;
    maxHeapify(arr, 1);
    return max;
  }

  // Why isn't this just done with maxHeapify()?
  private static void heapIncreaseKey(int[] arr, int elem, int newValue) {
    arr[elem] = newValue;
    while(elem > 1 && arr[elem/2] < arr[elem]) {
      int temp = arr[elem/2];
      arr[elem/2] = arr[elem];
      arr[elem] = temp;
      elem = elem/2;
    }
  }

  private static void heapIncreaseKeyInsertionSortInspired(int[] arr, int elem, int newValue) {
    while(elem > 1 && newValue > arr[elem/2]) {
      arr[elem] = arr[elem/2];
      elem = elem/2;
    }
    arr[elem] = newValue;
  }  

  private static void heapInsertKey(int[] arr, int key) {
    heapSize++;
    arr[heapSize] = Integer.MIN_VALUE;
    heapIncreaseKey(arr, heapSize, key);
  }

  private static int[] heapSort(int[] arr) {
    buildMaxHeap(arr);
    for(int i = arr.length - 1; i > 0; i--) {
      int temp = arr[i];
      arr[i] = arr[1];
      arr[1] = temp;
      heapSize--;
      maxHeapify(arr, 1);
    }
    return arr;
  }

  private static int[] buildMaxHeap(int[] arr) {
    heapSize = arr.length - 1;
    for(int i = (heapSize) / 2; i > 0; i--) {
      maxHeapify(arr, i);
    }
    return arr;
  }

  private static int[] maxHeapify(int[] arr, int i) {
    int leftIndex = 2 * i;
    int rightIndex = (2 * i) + 1;
    int largestIndex = 0;
    if(leftIndex <= heapSize && arr[leftIndex] > arr[i]) {
      largestIndex = leftIndex;
    }
    else {
      largestIndex = i;
    }
    if(rightIndex <= heapSize && arr[rightIndex] > arr[largestIndex]) {
      largestIndex = rightIndex;
    }
    if(largestIndex != i) {
      int temp = arr[i];
      arr[i] = arr[largestIndex];
      arr[largestIndex] = temp;
      maxHeapify(arr, largestIndex);
    }
    return arr;
  }

  private static void printArray(int[] arr) {
    for(int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  private static int[] produceSubarray(int[] arr) {
    int[] subarray = new int[arr.length - 1];
    for(int i = 0; i < subarray.length; i++) {
      subarray[i] = arr[i];
    }
    return subarray;
  }
}
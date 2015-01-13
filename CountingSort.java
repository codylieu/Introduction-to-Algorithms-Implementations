import java.lang.Integer;

// Running time is Theta(k + n), which is Theta(n) when k = O(n)
public class CountingSort {

  public static void main(String[] args) {
    int[] arr = {2, 5, 3, 0, 2, 3, 0, 3};
    printArray(countingSort(arr));

    int[] arr2 = {6, 0, 2, 0, 1, 3, 4, 6, 1, 3, 2};
    printArray(countingSort(arr2));

    int[] arr3 = {6, 0, 2, 0, 1, 3, 4, 6, 1, 3, 2, 2, 5, 3, 6, 4, 2, 4, 5, 3, 0, 2, 2, 4};
    printArray(countingSort(arr3));
  }

  private static int[] countingSort(int[] arr) {
    int maxElem = findMaxElement(arr);
    int[] c = new int[maxElem + 1];
    for(int i = 0; i < arr.length; i++) {
      c[arr[i]]++;
    }
    for(int j = 1; j < c.length; j++) {
      c[j] += c[j - 1];
    }
    int[] b = new int[arr.length];
    for(int k = arr.length - 1; k >= 0; k--) {
      b[c[arr[k]] - 1] = arr[k];
      c[arr[k]]--;
    }
    return b;
  }

  private static int findMaxElement(int[] arr) {
    int max = Integer.MIN_VALUE;
    for(int i : arr) {
      if(i > max) {
        max = i;
      }
    }
    return max;
  }

  private static void printArray(int[] arr) {
    for(int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

}
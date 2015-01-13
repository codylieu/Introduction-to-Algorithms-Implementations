import java.lang.Integer;
import java.util.Random;

public class QuickSort {
    
  public static void main(String[] args) {
    int[] arr = {7, 5, 3, 1, 6, 2, 8, 4};
    printArray(quickSort(arr, 0, arr.length - 1));
  }

  private static int[] quickSort(int[] arr, int p, int r) {
    if(p < r) {
      int q = randomizedPartition(arr, p, r);
      quickSort(arr, p, q - 1);
      quickSort(arr, q + 1, r);
    }
    return arr;
  }

  // Randomize pivot to increase probability of average case analysis
  private static int randomizedPartition(int[] arr, int p, int r) {
    Random rand = new Random();
    int i = rand.nextInt((r - p) + 1) + p;
    int temp = arr[r];
    arr[r] = arr[i];
    arr[i] = temp;
    return partition(arr, p, r);
  }

  private static int partition(int[] arr, int p, int r) {
    int pivot = arr[r];
    int i = p - 1;
    for(int j = p; j < r; j++) {
      if(arr[j] <= pivot) {
        i++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
    int temp = arr[i + 1];
    arr[i + 1] = pivot;
    arr[r] = temp;
    return i + 1;
  }

  private static void printArray(int[] arr) {
    for(int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

}
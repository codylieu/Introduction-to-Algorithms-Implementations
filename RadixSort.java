import java.lang.String;
import java.lang.Integer;

// To work properly, digit sorts must be stable, so we use an adapted Counting Sort algorithm as a subroutine
// Intuitively, this makes sense, stable sort on digits means that within each higher digit, the lower digits are sorted correctly
// This eventually means that when the higher order digit is sorted, the rest of the digits will be sorted correctly, since it was done already and it's stable
// Theta(d(n + k)) run time. Radix Sort has benefits over Counting Sort for large numbers where Counting Sort will have a high space complexity
// This is basically Counting Sort with hashing
public class RadixSort {

  public static void main(String[] args) {
    int[] arr = {329, 457, 657, 839, 436, 720, 355};
    printArray(radixSort(arr, 3));
  }

  private static int[] radixSort(int[] arr, int d) {
    for(int i = d - 1; i >= 0; i--) {
      arr = adaptedCountingSort(arr, produceArrayAtDigitD(arr, i));
    }
    return arr;
  }

  private static int[] adaptedCountingSort(int[] arr, int[] arrAtDigitD) {
    int[] c = new int[10];
    int n = arr.length;
    for(int i = 0; i < n; i++) {
      c[arrAtDigitD[i]]++;
    }
    for(int j = 1; j < c.length; j++) {
      c[j] += c[j - 1];
    }
    int[] b = new int[n];
    for(int k = n - 1; k >= 0; k--) {
      b[c[arrAtDigitD[k]] - 1] = arr[k];
      c[arrAtDigitD[k]]--;
    }
    return b;
  }

  private static int[] produceArrayAtDigitD(int[] arr, int d) {
    int n = arr.length;
    int[] arrAtDigitD = new int[n];
    for(int i = 0; i < n; i++) {
      arrAtDigitD[i] = Integer.parseInt((arr[i] + "").substring(d, d + 1));
    }
    return arrAtDigitD;
  }

  private static void printArray(int[] arr) {
    for(int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }
}
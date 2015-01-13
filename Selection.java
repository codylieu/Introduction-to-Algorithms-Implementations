import java.util.Random;

public class Selection {

  public static void main(String[] args) {
    int[] arr = {7, 5, 3, 1, 6, 2, 8, 4};
    System.out.println(selection(arr, 0, arr.length - 1, 5));
  }

  // This is expected linear time. Implement worst-case linear time selection later
  private static int selection(int[] arr, int p, int r, int i) {
    if(p == r) {
      return arr[p];
    }
    int q = randomizedPartition(arr, p, r);
    int k = q - p + 1;
    if(i == k) {
      return arr[q];
    }
    else if(i < k) {
      return selection(arr, p, q - 1, i);
    }
    else {
      return selection(arr, q + 1, r, i - k);
    }
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

}
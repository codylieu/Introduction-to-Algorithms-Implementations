public class InsertionSort {

  public static void main(String[] args) {
    int[] arr = {7, 5, 3, 1, 6, 2, 4, 8};
    printArray(insertionSort(arr));
  }

  private static int[] insertionSort(int[] arr) {
    for(int i = 1; i < arr.length; i++) {
      int temp = arr[i];
      int j = i - 1;
      while(j >= 0 && arr[j] > temp) {
        arr[j + 1] = arr[j];
        j--;
      }
      arr[j + 1] = temp;
    }
    return arr;
  }

  private static void printArray(int[] arr) {
    for(int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

}
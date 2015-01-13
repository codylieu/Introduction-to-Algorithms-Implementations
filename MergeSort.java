import java.lang.Integer;

// Only works for arrays whose sizes are powers of 2
public class MergeSort {
    
  public static void main(String[] args) {
    int[] arr = {7, 5, 3, 1, 6, 2, 4, 8};
    printArray(mergeSort(arr));
  }

  private static int[] mergeSort(int[] arr) {
    if(arr.length <= 1) {
      return arr;
    }
    int mid = arr.length / 2;
    int[] l = new int[mid];
    int[] r = new int[mid];
    for(int i = 0; i < mid; i++) {
      l[i] = arr[i];
    }
    for(int j = 0; j < mid; j++) {
      r[j] = arr[j + mid];
    }
    return merge(mergeSort(l), mergeSort(r));
  }

  private static int[] merge(int[] l, int[] r) {
    int length = l.length + r.length;
    int[] sortedList = new int[length];
    int lCounter = 0;
    int rCounter = 0;
    for(int i = 0; i < length; i++) {
      int lVal = 0;
      int rVal = 0;
      if(lCounter >= l.length) {
        lVal = Integer.MAX_VALUE;
      }
      else {
        lVal = l[lCounter];
      }
      if(rCounter >= r.length) {
        rVal = Integer.MAX_VALUE;
      }
      else {
        rVal = r[rCounter];
      }
      if(lVal < rVal) {
        sortedList[i] = lVal;
        lCounter++;
      }
      else {
        sortedList[i] = rVal;
        rCounter++;
      }
    }
    return sortedList;
  }

  private static void printArray(int[] arr) {
    for(int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

}
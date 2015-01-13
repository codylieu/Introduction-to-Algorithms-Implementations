// Jank implementation without linkedlists and hardcoded values
// Pain in the ass to implement
public class BucketSort {

  public static void main(String[] args) {
    double[] arr = {.78, .17, .39, .26, .72, .94, .21, .12, .23, .68};
    bucketSort(arr);
  }

  private static void bucketSort(double[] arr) {
    int n = arr.length;
    double[][] b = new double[n][n];
    for(int i = 0; i < n; i++) {
      b = insert(arr[i], b, n);
    }
    for(int j = 0; j < n; j++) {
      for(int k = 0; k < n; k++) {
        if(b[j][k] != 0) {
          System.out.print(b[j][k] + " ");
        }
      }
    }
    System.out.println();
  }

  private static double[][] insert(double val, double[][] b, int n) {
    int index = (int) (((double) n) * val);
    if(b[index][0] == 0) {
      b[index][0] = val;
    }
    else {
      for(int i = n - 1; i >= 0; i--) {
        if(b[index][i] != 0) {
          i++;
          int j = i - 1;
          while(j >= 0 && b[index][j] > val) {
            b[index][j + 1] = b[index][j];
            j--;
          }
          b[index][j + 1] = val;
          break;
        }
      }
    }
    return b;
  }

  private static void printMatrix(double[][] matrix) {
    for(int i = 0; i < matrix.length; i++) {
      for(int j = 0; j < matrix[0].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

}
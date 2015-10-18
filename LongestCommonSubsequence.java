public class LongestCommonSubsequence {
  public static void main(String[] args) {
    char[] X = new char[]{'A', 'B', 'C', 'B', 'D', 'A', 'B'};
    char[] Y = new char[]{'B', 'D', 'C', 'A', 'B', 'A'};
    int[][] c = lengthLCS(X, Y);
    System.out.println(c[X.length][Y.length]);
  }

  private static int[][] lengthLCS (char[] X, char[] Y) {
    int m = X.length;
    int n = Y.length;
    int[][] b = new int[m + 1][n + 1];
    int[][] c = new int[m + 1][n + 1];

    for(int i = 1; i < m + 1; i++) {
      c[i][0] = 0;
    }
    for(int j = 1; j < n + 1; j++) {
      c[0][j] = 0;
    }
    for(int i = 1; i < m + 1; i++) {
      for(int j = 1; j < n + 1; j++) {
        if (X[i - 1] == Y[j - 1]) {
          c[i][j] = c[i - 1][j - 1] + 1;
        }
        else if (c[i - 1][j] >= c[i][j - 1]) {
          c[i][j] = c[i - 1][j];
        }
        else {
          c[i][j] = c[i][j - 1];
        }
      }
    }
    return c;
  }
}
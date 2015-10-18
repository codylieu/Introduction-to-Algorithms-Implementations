import java.lang.Integer;

public class MatrixChainOrder {
  public static void main(String[] args) {
    int[] p = new int[]{30, 35, 15, 5, 10, 20, 25};
    int[][] m = matrixChainOrderBottomUp(p);
    System.out.println(m[1][p.length - 1]);
    System.out.println(matrixChainOrderTopDown(p));
  }

  private static int[][] matrixChainOrderBottomUp (int[] p) {
    int n = p.length - 1;
    int[][] m = new int[n + 1][n + 1];
    for(int i = 0; i < n + 1; i++) {
      m[i][i] = 0;
    }
    for(int l = 2; l < n + 1; l++) { // l is the chain length
      for(int i = 1; i < n - l + 2; i++) {
        int j = i + l - 1;
        m[i][j] = Integer.MAX_VALUE;
        for(int k = i; k < j; k++) {
          int q = m[i][k] + m[k + 1][j] + p[i - 1]*p[k]*p[j];
          if (q < m[i][j]) {
            m[i][j] = q;
          }
        }
      }
    }
    return m;
  }

  private static int matrixChainOrderTopDown (int[] p) {
    int n = p.length - 1;
    int[][] m = new int[n + 1][n + 1];
    for(int i = 1; i < n + 1; i++) {
      for(int j = 1; j < n + 1; j++) {
        m[i][j] = Integer.MAX_VALUE;
      }
    }
    return lookupChain(m, p, 1, n);
  }

  private static int lookupChain (int[][] m, int[] p, int i, int j) {
    if (m[i][j] < Integer.MAX_VALUE) {
      return m[i][j];
    }
    if (i == j) {
      m[i][j] = 0;
    }
    else {
      for(int k = i; k < j; k++) {
        int q = lookupChain(m, p, i, k) + lookupChain(m, p, k + 1, j) + p[i - 1]*p[k]*p[j];
        if (q < m[i][j]) {
          m[i][j] = q;
        }
      }
    }
    return m[i][j];
  }
}
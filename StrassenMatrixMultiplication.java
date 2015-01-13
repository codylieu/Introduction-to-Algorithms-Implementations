public class StrassenMatrixMultiplication {

  public static void main(String[] args) {
    int[][] a = {{1, 9, 0, 7},
                  {5, 5, 7, 9},
                  {0, 5, 7, 5},
                  {1, 8, 7, 9}};
    int[][] b = {{1, 5, 9, 0},
                  {5, 2, 1, 9},
                  {6, 8, 4, 2},
                  {8, 3, 7, 3}};
    // Expected Solution
    int[][] C = {{102, 44, 67, 102},
                  {144, 118, 141, 86},
                  {107, 81, 68, 74},
                  {155, 104, 108, 113}};
    printMatrix(strassenMatrixMultiplication(a, b));
  }

  private static int[][] strassenMatrixMultiplication(int[][] a, int[][] b) {
    int n = a.length;
    int[][] c = new int[n][n];
    if(n == 1) {
      c[0][0] = a[0][0] * b[0][0];
    }
    else {
      int[][] a11 = partitionMatrix(a, 0, 0);
      int[][] a12 = partitionMatrix(a, 0, n/2);
      int[][] a21 = partitionMatrix(a, n/2, 0);
      int[][] a22 = partitionMatrix(a, n/2, n/2);
      int[][] b11 = partitionMatrix(b, 0, 0);
      int[][] b12 = partitionMatrix(b, 0, n/2);
      int[][] b21 = partitionMatrix(b, n/2, 0);
      int[][] b22 = partitionMatrix(b, n/2, n/2);

      int[][] s1 = subtractMatrix(b12, b22);
      int[][] s2 = addMatrix(a11, a12);
      int[][] s3 = addMatrix(a21, a22);
      int[][] s4 = subtractMatrix(b21, b11);
      int[][] s5 = addMatrix(a11, a22);
      int[][] s6 = addMatrix(b11, b22);
      int[][] s7 = subtractMatrix(a12, a22);
      int[][] s8 = addMatrix(b21, b22);
      int[][] s9 = subtractMatrix(a11, a21);
      int[][] s10 = addMatrix(b11, b12);

      int[][] p1 = strassenMatrixMultiplication(a11, s1);
      int[][] p2 = strassenMatrixMultiplication(s2, b22);
      int[][] p3 = strassenMatrixMultiplication(s3, b11);
      int[][] p4 = strassenMatrixMultiplication(a22, s4);
      int[][] p5 = strassenMatrixMultiplication(s5, s6);
      int[][] p6 = strassenMatrixMultiplication(s7, s8);
      int[][] p7 = strassenMatrixMultiplication(s9, s10);

      int[][] c11 = addMatrix(subtractMatrix(addMatrix(p5, p4), p2), p6);
      int[][] c12 = addMatrix(p1, p2);
      int[][] c21 = addMatrix(p3, p4);
      int[][] c22 = subtractMatrix(subtractMatrix(addMatrix(p5, p1), p3), p7);

      mergeMatrices(c, c11, c12, c21, c22);
    }
    return c;
  }

  private static int[][] partitionMatrix(int[][] matrix, int rLow, int cLow) {
    int n = matrix.length/2;
    int[][] partitionedMatrix = new int[n][n];
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        partitionedMatrix[i][j] = matrix[rLow + i][cLow + j];
      }
    }
    return partitionedMatrix;
  }

  private static int[][] mergeMatrices(int[][] c, int[][] c11, int[][] c12, int[][] c21, int[][] c22) {
    int n = c11.length * 2;

    for(int i = 0; i < n/2; i++) {
      for(int j = 0; j < n/2; j++) {
        c[i][j] = c11[i][j];
      }
    }

    for(int x = 0; x < n/2; x++) {
      for(int y = n/2; y < n; y++) {
        c[x][y] = c12[x][y - n/2];
      }
    }

    for(int a = n/2; a < n; a++) {
      for(int b = 0; b < n/2; b++) {
        c[a][b] = c21[a - n/2][b];
      }
    }

    for(int u = n/2; u < n; u++) {
      for(int v = n/2; v < n; v++) {
        c[u][v] = c22[u - n/2][v - n/2];
      }
    }

    return c;
  }

  private static int[][] addMatrix(int[][] m1, int[][] m2) {
    int n = m1.length;
    int[][] resultMatrix = new int[n][n];
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        resultMatrix[i][j] = m1[i][j] + m2[i][j];
      }
    }
    return resultMatrix;
  }

  private static int[][] subtractMatrix(int[][] m1, int[][] m2) {
    int n = m1.length;
    int[][] resultMatrix = new int[n][n];
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        resultMatrix[i][j] = m1[i][j] - m2[i][j];
      }
    }
    return resultMatrix;
  }

  private static void printMatrix(int[][] matrix) {
    for(int i = 0; i < matrix.length; i++) {
      for(int j = 0; j < matrix[0].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  // Naive n^3 implementation to compare to
  private static int[][] matrixMultiplication(int[][] a, int[][] b) {
    int n = a.length;
    int[][] c = new int[n][n];
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        int cTemp = 0;
        for(int k = 0; k < n; k++) {
          cTemp += a[i][k] * b[k][j];
        }
        c[i][j] = cTemp;
      }
    }
    return c;
  }
  
}
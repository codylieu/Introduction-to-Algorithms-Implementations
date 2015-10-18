import java.lang.Integer;

public class CutRod {
  public static void main(String[] args) {
    int[] p = new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
    System.out.println(cutRodBottomUp(p, p.length));
  }

  // Memoized
  private static int cutRodTopDown (int[] p, int n) {
    int[] r = new int[n + 1];
    for(int i = 0; i < n + 1; i++) {
      r[i] = Integer.MIN_VALUE;
    }
    return memoizedCutRodAux(p, n, r);
  }

  private static int memoizedCutRodAux(int[] p, int n, int[] r) {
    if (r[n] >= 0) {
      return r[n];
    }
    int q = 0;
    if (n != 0) {
      q = Integer.MIN_VALUE;
      for(int i = 1; i < n + 1; i++) {
        q = Math.max(q, p[i - 1] + memoizedCutRodAux(p, n - i, r));
      }
    }
    r[n] = q;
    return q;
  }

  private static int cutRodBottomUp (int[] p, int n) {
    int[] r = new int[n + 1];
    r[0] = 0;
    for(int i = 1; i < n + 1; i++) {
      int q = Integer.MIN_VALUE;
      for(int j = 1; j < i + 1; j++) {
        q = Math.max(q, p[j - 1] + r[i - j]);
      }
      r[i] = q;
    }
    return r[n];
  }
}
public class EuclidGreatestCommonDivisor {

  public static void main(String[] args) {
    System.out.println(greatestCommonDivisor(30, 21));
  }

  private static int greatestCommonDivisor(int a, int b) {
    if(b == 0) {
      return a;
    }
    return greatestCommonDivisor(b, a % b);
  }
  
}
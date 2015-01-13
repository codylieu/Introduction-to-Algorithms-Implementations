import java.lang.Integer;
import java.lang.Math;

public class MaximumSubarray {

  public static void main(String[] args) {
    int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
    System.out.println(kadaneMaximumSubarray(arr));
    System.out.println(maximumSubarray(arr, 0, arr.length));
  }

  // Google's question. It's an adaptation of Kadane's Algorithm AND I FUCKED IT UP.
  private static int maxWeightLost(int[] arr) {
    int maxSoFar = 0;
    int maxDifference = 0;
    for(int i = 0; i < arr.length; i++) {
      maxSoFar = Math.max(maxSoFar, arr[i]);
      maxDifference = Math.max(0, maxSoFar - arr[i]);
    }
    return maxDifference;
  }

  // Kadane's Algorithm: O(n) runtime
  private static int kadaneMaximumSubarray(int[] arr) {
    int maxEndingHere = 0;
    int maxSoFar = 0;
    for(int i = 0; i < arr.length; i++) {
      maxEndingHere = Math.max(0, maxEndingHere + arr[i]);
      maxSoFar = Math.max(maxSoFar, maxEndingHere);
    }
    return maxSoFar;
  }

  // O(nlogn) runtime
  private static int maximumSubarray(int[] arr, int low, int high) {
    if(low + 1 == high) {
      return arr[low];
    }
    else {
      int mid = (low + high) / 2;
      int leftMax = maximumSubarray(arr, low, mid);
      int rightMax = maximumSubarray(arr, mid, high);
      // This represents the combine step
      int crossingMax = maximumCrossingSubarray(arr, low, mid, high);

      if(leftMax > rightMax && leftMax > crossingMax) {
        return leftMax;
      }
      else if(rightMax > leftMax && rightMax > crossingMax) {
        return rightMax;
      }
      else {
        return crossingMax;
      }
    }
  }

  private static int maximumCrossingSubarray(int[] arr, int low, int mid, int high) {
    int maxLeftSum = Integer.MIN_VALUE;
    int leftSum = 0;
    for(int i = mid - 1; i >= low; i--) {
      leftSum += arr[i];
      if(leftSum > maxLeftSum) {
        maxLeftSum = leftSum;
      }
    }
    int maxRightSum = Integer.MIN_VALUE;
    int rightSum = 0;
    for(int j = mid; j < high; j++) {
      rightSum += arr[j];
      if(rightSum > maxRightSum) {
        maxRightSum = rightSum;
      }
    }
    return maxLeftSum + maxRightSum;
  }

}
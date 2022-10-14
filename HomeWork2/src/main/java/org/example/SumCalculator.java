package org.example;

public class SumCalculator {
   public int sumCalculator(int number) {
      int result = 0;
      if (number <= 0) {
         throw new IllegalArgumentException("The number must be greater than 0");
      }

      for (int i = 1; i <= number; i++) {
         result += i;
      }
         return result;
      }
}


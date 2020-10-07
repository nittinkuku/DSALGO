package org.swanix;

public class Swati_Codelity {
    public static void main(String[] args) {
        //System.out.println(new Test().solution(734));
        //System.out.println(new Test().solution(3, 2, new int[]{2, 1, 1, 0, 1}));
        //System.out.println(new Swati_Codelity().solution(2, 3, new int[]{ 0, 0, 1, 1, 2}));
        //System.out.println(new Zalando_Codelity().solution(2, 3, new int[]{0, 0, 1, 1, 2}));

        System.out.println(new Swati_Codelity().solution(new int[]{}, new int[]{}));
    }

    public String solution(int U, int L, int[] C) {
        StringBuilder firstString = new StringBuilder(C.length);
        StringBuilder secondString = new StringBuilder(C.length);

        for (int i = 0; i < C.length; i++) {
            if (C[i] == 0) {
                firstString.append("0");
                secondString.append("0");
            } else if (C[i] == 2) {
                firstString.append("1");
                secondString.append("1");
                U--;
                L--;
            } else {
                if (U >= L) {
                    firstString.append("1");
                    secondString.append("0");
                    U--;
                } else {
                    secondString.append("1");
                    firstString.append("0");
                    L--;
                }

            }
            if (U < 0 || L < 0) {
                return "IMPOSSIBLE";
            }
        }

        if (U > 0 || L > 0) {
            return "IMPOSSIBLE";
        }

        return firstString.toString() + "," + secondString.toString();
    }


    public int solution(int[] A, int[] B) {
        int totalLength = A.length;
        int fairIndexesCount = 0;

        long sumA = 0;
        long sumB = 0;

        for (int i = 0; i < totalLength; i++) {
            sumA = sumA + A[i];
            sumB = sumB + B[i];
        }

        int tempSumA = 0;
        int tempSumB = 0;

        for (int i = 1; i < totalLength; i++) {
            tempSumA = tempSumA + A[i - 1];
            tempSumB = tempSumB + B[i - 1];
            if ((sumA == 2 * tempSumA) && (sumB == 2 * tempSumB) && (tempSumA == tempSumB)) {
                fairIndexesCount++;
            }

        }

        return fairIndexesCount;
    }


    public int solution(int N) {
        int givenDigitSum = getDigitSum(N);
        N++;
        while (getDigitSum(N) != givenDigitSum) {
            N++;
        }
        return N;
    }


    public int getDigitSum(int n) {
        int sum = 0;
        String str = String.valueOf(n);
        for (int i = 0; i < str.length(); i++) {
            sum = sum + Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return sum;
    }
}

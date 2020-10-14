package org.swanix.interviewquestion.zalando;

import java.util.stream.IntStream;

public class Zalando_Codelity {
    public static void main(String[] args) {
        //System.out.println(new Test().solution(734));
        //System.out.println(new Test().solution(3, 2, new int[]{2, 1, 1, 0, 1}));
        System.out.println(new Zalando_Codelity().solution(5, 5, new int[]{2, 1, 2, 0, 1, 0, 1, 2, 0, 1}));
        //System.out.println(new Zalando_Codelity().solution(2, 3, new int[]{0, 0, 1, 1, 2}));

    }

    public String solution(int U, int L, int[] C) {
        if (C == null || C.length == 0) {
            return "IMPOSSIBLE";
        }

        int sum = IntStream.of(C).sum();

        if (U + L != sum) {
            return "IMPOSSIBLE";
        }

        StringBuilder a = new StringBuilder("");
        StringBuilder b = new StringBuilder("");

        for (int i = 0; i < C.length; i++) {
            if (C[i] == 0) {
                a.append("0");
                b.append("0");
            } else if (C[i] == 2) {
                a.append("1");
                b.append("1");
                U--;
                L--;
            } else {
                if (U >= L) {
                    a.append("1");
                    b.append("0");
                    U--;
                } else {
                    b.append("1");
                    a.append("0");
                    L--;
                }

            }
            if (U < 0 || L < 0) {
                return "IMPOSSIBLE";
            }
        }


        return a.toString() + "," + b.toString();
    }


    public int solution(int[] A, int[] B) {
        if ((A == null) || (B == null) || (A.length < 2) || (B.length < 2) || (A.length != B.length)) {
            return 0;
        }

        int arrayLength = A.length;
        int noOfK = 0;

        long totalSumOfA = 0;
        long totalSumOfB = 0;
        int currSumOfA = 0;
        int currSumOfB = 0;

        for (int i = 0; i < arrayLength; i++) {
            totalSumOfA = totalSumOfA + A[i];
            totalSumOfB = totalSumOfB + B[i];
        }

        for (int i = 1; i < arrayLength; i++) {
            currSumOfA = currSumOfA + A[i - 1];
            currSumOfB = currSumOfB + B[i - 1];
            if ((totalSumOfA == 2 * currSumOfA) && (totalSumOfB == 2 * currSumOfB) && (currSumOfA == currSumOfB)) {
                noOfK++;
            }

        }

        return noOfK;
    }


    public int solution(int N) {
        int  givenDigitSum = getDigitSum(N);
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

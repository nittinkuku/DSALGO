package org.swanix.interviewquestion.zalando;

import java.util.PriorityQueue;

public class Swati_Codelity_2 {

    public static void main(String[] args) {

    }

    public int solution1(String S) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < S.length() - 1; i++) {
            int curr_num = Integer.parseInt(S.substring(i, i + 2));
            max = Math.max(max, curr_num);
        }

        return max;
    }


    public static int solution(String S) {
        int countDel = 0;
        int[] freq = new int[26];
        for (char c : S.toCharArray()) {
            int index = c - 'a';
            freq[index] = freq[index] + 1;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (int i : freq) {
            if (i != 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            int top = pq.poll();

            if (pq.isEmpty()) {
                return countDel;
            }

            if (top == pq.peek()) {
                if (top > 1) {
                    pq.add(top - 1);
                }
                countDel++;
            }
        }
        return countDel;

    }


    public static int solution(int[] A) {
        int result = Integer.MAX_VALUE;
        int min_so_far = A[1];

        for (int i = 3; i < A.length; i++) {
            int sum = A[i] + min_so_far;
            result = Math.min(result, sum);

            min_so_far = Math.min(min_so_far, A[i - 1]);
        }

        return result;
    }
}

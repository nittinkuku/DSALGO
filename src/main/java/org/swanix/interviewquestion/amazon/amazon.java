package org.swanix.interviewquestion.amazon;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class amazon {


    public int minComplexity(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        int[][] dp = new int[d][n];

        dp[0][0] = jobDifficulty[0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.max(jobDifficulty[i], dp[0][i - 1]);
        }

        for (int i = 1; i < d; i++) {
            for (int j = i; j < n; j++) {
                int localmax = jobDifficulty[j];
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = j; k >= i; k--) {
                    localmax = Math.max(localmax, jobDifficulty[k]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k - 1] + localmax);
                }
            }
        }

        return dp[d - 1][n - 1];
    }

    public int fiveStarReviews(List<List<Integer>> productRatings, int ratingsThreshold) {
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((a, b) -> {
            double currRatingA = 100.0 * a.get(0) / a.get(1);
            double newRatingA = 100.0 * (a.get(0) + 1) / (a.get(1) + 1);
            double percIncreaseA = newRatingA - currRatingA;

            double currRatingB = 100.0 * b.get(0) / b.get(1);
            double newRatingB = 100.0 * (b.get(0) + 1) / (b.get(1) + 1);
            double percIncreaseB = newRatingB - currRatingB;

            return (int) (percIncreaseB - percIncreaseA);
        });

        double currentRating = 0;
        for (List<Integer> list : productRatings) {
            pq.offer(list);
            currentRating += 100 * list.get(0) / list.get(1);
        }

        int count = 0;
        while (currentRating < ratingsThreshold * productRatings.size()) {
            count++;
            List<Integer> currProd = pq.poll();
            List<Integer> newRating = Arrays.asList(currProd.get(0) + 1, currProd.get(1) + 1);
            //subtract previous percentage and add new calculated percentage
            currentRating = currentRating - 100 * currProd.get(0) / currProd.get(1) + 100 * newRating.get(0) / newRating.get(1);
            pq.offer(newRating);
        }

        return count;

    }

}

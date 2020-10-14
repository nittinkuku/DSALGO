package org.swanix.interviewquestion.gs;

import java.util.HashSet;

public class SwatiGS {

    public static void main(String[] args){
        System.out.println(nonRepeatingDigitProductCount(2,10,15));
    }

    static int nonRepeatingDigitProductCount(int x, int y, int z) {
        int count = 0;
        for (int i = y; i <= z; i++) {
            int mul = x * i;
            if (isValid(mul, i)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isValid(int res, int n) {
        String resStr = String.valueOf(res);
        String nStr = String.valueOf(n);

        HashSet<Character> mulHash = new HashSet<>();

        for (int i = 0; i < resStr.length(); i++) {
            mulHash.add(resStr.charAt(i));
        }

        for (int i = 0; i < nStr.length(); i++) {
            if (mulHash.contains(nStr.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}

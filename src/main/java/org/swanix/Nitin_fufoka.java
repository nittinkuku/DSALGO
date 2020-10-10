package org.swanix;

import java.util.ArrayList;
import java.util.List;

public class Nitin_fufoka {

}

class Solution {
    public String[] generateParenthesis(int n) {
        List<String> output = new ArrayList<>();
        fn(output, n, n, "");
        return output.toArray(new String[0]);
    }

    void fn(List<String> arr, int open, int close, String ans) {
        if (open == 0 && close == 0) {
            arr.add(ans);
            return;
        }
        if (open > 0) {
            fn(arr, open - 1, close, ans + '(');
        }
        if (close > open) {
            fn(arr, open, close - 1, ans + ')');
        }
    }
}

   /* class Solution {
        public int solution(int[] n) {
            if (n.length == 1) {
                return n[0];
            }

            int noToInclude = 0;
            int currSum = n[0];

            for (int i = 1; i < n.length; i++) {
                int sum = n[i] + noToInclude;
                noToInclude = currSum;
                currSum = Math.max(sum, currSum);
            }

            return currSum;
        }
    }*/


   /* public String solution(int t, String[] logs) {
        List<String> runningProcess = new ArrayList<>();
        int value = 0;

        for (int i = 0; i < logs.length; i++) {
            String[] info = logs[i].split(" ");

            if (Integer.parseInt(info[0]) >= t) {
                break;
            }

            if (info[2].equalsIgnoreCase("running")) {
                runningProcess.add(info[1]);
                value++;
            } else {
                if (runningProcess.contains(info[1])) {
                    runningProcess.remove(info[1]);
                    value--;
                }
            }
        }
        if (value == 1) {
            return runningProcess.iterator().next();
        } else {
            return "-1";
        }
    }*/




/*
    class Solution {
        int length;
        Entry valArray[];
        ArrayList<String> res;
        Deque<String> deque;

        public String[] solution(String[] n) {
            length = n.length;
            valArray = new Entry[length];
            res = new ArrayList<>();
            deque = new LinkedList<>();

            outer:
            for (int i = 0; i < n.length; i++) {
                String[] info = n[i].split(" ");
                switch (info[0]) {
                    case "add":
                        add(info[1], info[2]);
                        break;
                    case "get":
                        get(info[1]);
                        break;
                    case "evict":
                        evict();
                        break;
                    case "remove":
                        remove(info[1],true);
                        break;
                    case "exit":
                        break outer;
                }
            }
            return res.toArray(new String[0]);
        }

        public void add(String key, String val) {
            int bucket = Math.abs(key.hashCode()) % length;
            Entry head = valArray[bucket];
            if (head == null) {
                valArray[bucket] = new Entry(key, val);
            } else {
                Entry prev = null;
                while (head != null) {
                    if (head.key.equals(key)) {
                        head.val = val;
                        return;
                    }
                    prev = head;
                    head = head.next;
                }
                if (head == null) {
                    prev.next = new Entry(key, val);
                }
            }
            deque.remove(key);
            deque.addFirst(key);
        }

        public void get(String key) {
            int bucket = Math.abs(key.hashCode()) % length;
            Entry head = valArray[bucket];
            while (head != null) {
                if (head.key.equals(key)) {
                    res.add(head.val);
                    deque.remove(key);
                    deque.addFirst(key);
                    return;
                } else {
                    head = head.next;
                }
            }

            if (head == null) {
                res.add("-1");
            }
        }

        public void remove(String key, boolean addToRes) {
            int bucket = Math.abs(key.hashCode()) % length;
            Entry head = valArray[bucket];
            Entry prev = null;

            while (head != null) {
                if (head.key.equals(key)) {
                    if(addToRes) {
                        res.add(head.val);
                    }
                    if(prev!=null){
                        prev.next = head.next;
                    }
                    else{
                        valArray[bucket] = head.next;
                    }
                    return;
                }
                prev = head;
                head = head.next;
            }

            if (head == null) {
                if(addToRes) {
                    res.add("-1");
                }
            }
        }

        public void evict() {
            String key = deque.removeLast();
            remove(key,false);
        }

        class Entry {
            String key;
            String val;
            Entry next;

            Entry(String key, String val) {
                this.key = key;
                this.val = val;
            }
        }
    }
*/







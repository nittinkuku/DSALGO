package org.swanix.interviewquestion.oracle;

public class OracleTest {
    public static void main(String[] args) {
        PrinterTask p = new PrinterTask(10);

        Thread t1 = new Thread(() -> p.printOdd());
        Thread t2 = new Thread(() -> p.printEven());
        t1.start();
        t2.start();
    }
}


class PrinterTask {
    public volatile boolean isOdd = true;
    int number = 1;
    int max;

    public PrinterTask(int max) {
        this.max = max;
    }

    public synchronized void printOdd() {
        while (number < max) {
            while (!isOdd) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Print Number :" + number);
            number++;
            isOdd = false;
            notify();
        }
    }

    public synchronized void printEven() {
        while (number < max) {
            while (isOdd) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Print Number :" + number);
            number++;
            isOdd = true;
            notify();
        }
    }
}
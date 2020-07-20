import static java.lang.Thread.*;

public class ShopUpOddEven {

    int TOTAL_COUNT = 100;

    boolean isOdd;
    int count = 1;

    public static void main(String[] args) {
        ShopUpOddEven oddEven = new ShopUpOddEven();
        oddEven.isOdd = true;
        Thread t1 = new Thread(() -> oddEven.printEven());
        Thread t2 = new Thread(() -> oddEven.printOddNumbers());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    public void printEven() {
        waitForTime();
        synchronized (this) {
            while (TOTAL_COUNT > count) {
                while (isOdd) try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Even thread count: " + count);
                isOdd = true;
                count++;
                notify();

            }
        }
    }

    private void waitForTime() {
        try {
            sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void printOddNumbers() {
        synchronized (this) {
            while (TOTAL_COUNT > count) {
                while (!isOdd) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Odd Thread count: " + count);
                count++;
                isOdd = false;
                notify();
            }
        }
    }
}

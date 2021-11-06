package exercise03.java02;

public class Consumer extends Thread {
    private final CircleBuffer cubbyhole;
    public Consumer(CircleBuffer c) {
        cubbyhole = c;
    }
    @Override
    public void run() {
        while (true){
            int value=cubbyhole.get();
        }
    }
}

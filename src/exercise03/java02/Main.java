package exercise03.java02;

//数据取走后，数据不会消失
public class Main {

    public static void main(String[] args) {
        CircleBuffer c = new CircleBuffer();
        Producer p1 = new Producer(c, 1000,-1);
        Producer p2 = new Producer(c, 0,1);
        Consumer c1 = new Consumer(c);
        p1.start();
        p2.start();
        c1.start();
    }

}

package exercise03.java02;

public class Producer extends Thread {
    private CircleBuffer cubbyhole;
    private int isFlag;
    private int i=0;
    public Producer(CircleBuffer c,int i, int isFlag) {
        cubbyhole = c;
        this.i=i;
        this.isFlag = isFlag;
    }

    @Override
    public void run() {
        while (true){
            if(isFlag==-1&&i<=0){
                i=i+1000;
            }
            if(i>1000){
                i-=1000;
            }
            cubbyhole.put(i);
            i+=isFlag;

        }
    }
}
package exercise03.java02;

import java.util.Random;

public class CircleBuffer {
    private int writePoint=0;
    private int readPoint=0;
    private static final Random RANDOM =new Random();
    private static final int LENGTH=5;
    private int[] contents=new int[LENGTH];
    private boolean available = false;
    public String readInts(){
        String s = "";
        for(int i=0;i<LENGTH;i++){
            s+= contents[i] +" ";
        }
        return s;
    }
    public synchronized int get() {
        if(readPoint>=5){
            readPoint%=5;
        }
        while (!available) {
            try {
                wait(RANDOM.nextInt(500));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        readPoint++;
        available = false;
        notifyAll();
        int value=contents[readPoint-1];
        System.out.println("get "+value+"  & {["+readInts()+"] "+LENGTH+" "+readPoint+" "+writePoint+" }");
        return contents[readPoint-1];
    }
    public synchronized void put(int value) {
        if(writePoint>=5){
            writePoint%=5;
        }
        while (available) {
            try {
                wait(RANDOM.nextInt(5));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        contents[writePoint] = value;
        writePoint++;
        available = true;
        notifyAll();
        System.out.println("put "+value+"&{["+readInts()+"]"+LENGTH+" "+readPoint+" "+writePoint+"}");
    }
}

package exercise03.java03;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static exercise03.java03.MedianFilter.medianFilter;

public class Worker extends Thread{
    public static int[] newPix;

    public static int height;
    public static int width;
    public static String fileIn;
    public static String fileOut;
    private static BufferedImage image;
    private int index;
    private int[] pix;

    public Worker(int index) {
        loadImage();
        this.index = index;
    }
    private static void loadImage(){
        if (image==null) {
            try {
                image= ImageIO.read(new File(fileIn));
                width=image.getWidth();
                height=image.getHeight();
                newPix=new int[width*height];
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void run() {
        pix=medianFilter(0,(index)*height/4,width,height/4,image);
        System.arraycopy(pix, 0, newPix, ((index) * height * width / 4), pix.length);
        System.out.println(currentThread()+"线程结束~~~");
    }


}

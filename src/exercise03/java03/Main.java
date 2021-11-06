package exercise03.java03;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Main {
    private static final int LENGTH=4;
    public static void main(String[] args) {
        ExecutorService executorService = newFixedThreadPool(LENGTH);
        String fileName="src\\exercise03\\java03\\ship.png";
        String fileOut="src\\exercise03\\java03\\ship3.png";
        Worker.fileIn=fileName;
        Worker.fileOut=fileOut;
        for (int i=0;i<LENGTH;i++){
            executorService.execute(new Worker(i));
        }
        executorService.shutdown();
        //这一步需要检查是否有线程未完成
        while (true){
            if(executorService.isTerminated()) {
                System.out.println("线程池所有线程结束了！");
                break;
            }
        }
        BufferedImage outBufferedImage =new BufferedImage(Worker.width, Worker.height, BufferedImage.TYPE_INT_RGB);
        outBufferedImage.setRGB(0, 0, Worker.width, Worker.height, Worker.newPix, 0, Worker.width);
        try {

            ImageIO.write(outBufferedImage, "png", new File(fileOut));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("程序结束");
    }
}

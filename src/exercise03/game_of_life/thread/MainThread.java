package exercise03.game_of_life.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static exercise03.game_of_life.ui.GameOfLifeFrame.*;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class MainThread extends Thread{
    private final int LENGTH=2;
    private final int THREADNUMBER=LENGTH+1;//
    private final int gapHeight=cellMatrix.getHeight()/LENGTH;
    private Worker[] workers=new Worker[THREADNUMBER];
    private Future<Boolean>[] future=new Future[THREADNUMBER];
    @Override
    public void run(){
//        long startTime=System.nanoTime();
        ExecutorService service = newFixedThreadPool(THREADNUMBER);
        for (int i=0;i<LENGTH;i++){
            workers[i]=new Worker(i*gapHeight,(i+1)*gapHeight);
        }
        workers[THREADNUMBER-1]=new Worker((LENGTH)*gapHeight,cellMatrix.getHeight());
        while (!isEnd()){
            while (isStop()){
                System.out.println("wait......");
            }
            for (int i=0;i<THREADNUMBER;i++){
                future[i]=service.submit(workers[i]);
            }
            for (int i=0;i<THREADNUMBER;i++){
                try {
                    if (!future[i].get()){
                        break;
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
//            long endTime=System.nanoTime();
//            System.out.println((endTime-startTime)+"  "+cellMatrix.getAliveCell());
        }
        service.shutdown();


    }

}

package exercise03.game_of_life.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import static exercise03.game_of_life.ui.GameOfLifeFrame.cellMatrix;
import static exercise03.game_of_life.ui.GameOfLifeFrame.showMatrix;

public class Worker implements  Callable<Boolean> {
    private int startHeight;
    private int endHeight;//不包括
    private final int DEFAULT_DURATION=200;
    public Worker(int startHeight, int endHeight) {
        this.startHeight = startHeight;
        this.endHeight = endHeight;

    }
    @Override
    public Boolean call()  {
        int[][] matrix = cellMatrix.transform(startHeight, endHeight);
        showMatrix(startHeight,endHeight,matrix);
        try {
            Thread.sleep(DEFAULT_DURATION);
        } catch (InterruptedException ex) {
            return false;
        }
        for (int y = startHeight; y < endHeight; y++) {
            if (cellMatrix.getWidth() >= 0) {
                System.arraycopy(matrix[y - startHeight], 0, cellMatrix.matrix[y], 0, cellMatrix.getWidth());
            }
        }
        return true;
    }
}

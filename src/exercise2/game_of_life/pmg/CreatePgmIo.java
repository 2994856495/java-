package exercise2.game_of_life.pmg;

import org.junit.Test;

import java.io.File;
import java.util.Random;


/**
 * @author ASUS
 */
public class CreatePgmIo {
    private static void createCaseFile() {
        Random random = new Random();
        int rows = 1 + random.nextInt(10);
        int cols = 1 + random.nextInt(10);
        String filePath=cols+"_"+rows+"_"+System.nanoTime() + ".pgm";
        File file = new File(filePath);
        int[][] rgbArray = new int[rows][cols];
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    int i = random.nextInt(255);
                    rgbArray[y][x]=i;
                }
            }
            PgmIo.write(rgbArray,file);

    }
    @Test
    public void test() {
        createCaseFile();
    }
}

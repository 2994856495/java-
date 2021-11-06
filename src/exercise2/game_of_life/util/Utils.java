package exercise2.game_of_life.util;

import exercise2.game_of_life.model.CellMatrix;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;


public class Utils {
    private static int width;
    private static int height;
    private static int[][] matrix;
    public static CellMatrix initMatrixFromFile(String path) {
        FileInputStream fileInputStream=null;
        DataInputStream dataInputStream=null;
        try {
            fileInputStream = new FileInputStream(path);
            dataInputStream = new DataInputStream(fileInputStream);
            String s;
            dataInputStream.readUTF();
            s=dataInputStream.readUTF();
            width = Integer.parseInt(s);
            s=dataInputStream.readUTF();
            height = Integer.parseInt(s);
            s=dataInputStream.readUTF();
            int maximumPixelValue=Integer.parseInt(s);
            int judge;
            matrix = new int[height][width];
            //noinspection InfiniteLoopStatement
            while (true){
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        s=dataInputStream.readUTF();
                        judge=Integer.parseInt(s);
                        if(judge>maximumPixelValue/2){
                            matrix[i][j] =1;
                        }
                        else{
                            matrix[i][j]=0;
                        }
                    }
                }
            }
        }
        catch (EOFException e){
            return new CellMatrix(height, width, matrix);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataInputStream!=null) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }
}

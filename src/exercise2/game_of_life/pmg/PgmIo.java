package exercise2.game_of_life.pmg;

import java.io.*;

public final class PgmIo {
    private static final String MAGIC = "P5";
    private static final int MAXVAL = 255;
    public static void write(int[][] rgbArray, final File file)  {
        try {
            write(rgbArray, file, MAXVAL);
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

    public static void write(int[][] rgbArray, final File file, final int maxval) throws IOException {
        if (maxval > MAXVAL) {
            throw new IllegalArgumentException("The maximum gray value cannot exceed " + MAXVAL + ".");
        }
        try (DataOutputStream stream = new DataOutputStream(new FileOutputStream(file))) {
            stream.writeUTF(MAGIC);
            stream.writeUTF(Integer.toString(rgbArray[0].length));
            stream.writeUTF(Integer.toString(rgbArray.length));
            stream.writeUTF(Integer.toString(maxval));
            for (int[] ints : rgbArray) {
                for (int j = 0; j < rgbArray[0].length; j++) {
                    final int p = ints[j];
                    if (p < 0 || p > maxval) {
                        throw new IOException("Pixel value " + p + " outside of range [0, " + maxval + "].");
                    }
                    stream.writeUTF(Integer.toString(ints[j]));
                }
            }
            System.out.println("PGM create successful, file name :" + file.getName() + "\nwidth:" + rgbArray[0].length + ",height:" + rgbArray.length);
        }
    }

}
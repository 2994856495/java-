package exercise03.game_of_life.model;

import java.util.Arrays;

/**
 *
 * @author ASUS
 */
public class CellMatrix {
    /**
     * 矩阵高度
     */
    private final int height;
    /**
     * 矩阵宽度
     */
    private final int width;
    /**
     * 矩阵状态，1表示活，0表示死
     */
    public int[][] matrix;
    public String getAliveCell(){
        /*获取存活细胞个数*/
        int count = 0;
        if(matrix==null||matrix.length == 0){
            return "游戏未开始";
        }
        else{
            for (int y=0;y<height;y++){
                for (int x=0;x<width;x++){
                    if (matrix[y][x]==1){
                        count++;
                    }
                }
            }
            return "存活细胞个数为:"+count;
        }
    }
    public CellMatrix(int height, int width, int[][] matrix) {
        this.height = height;
        this.width = width;
        this.matrix = matrix;
    }

    /**
     * 上一个状态到下一个状态的转移
     * 根据规则可以总结得出两条规则:
     * 1. 对于周围活着的细胞为3的情况,下一个状态该细胞总是为活
     * 2. 对于周围活着的细胞为2的情况,下一个状态与上一状态相同
     */
    public int[][] transform(int startHeight, int endHeight){
        int[][] nextMatrix=new int[endHeight-startHeight][width];
        for (int y = startHeight; y < endHeight; y++) {
            for (int x = 0; x < width; x++) {
                nextMatrix[y-startHeight][x]=0;
                int nearNum= findLifedNum(y,x);
                //等于3，则下一状态总是活
                if(nearNum==3){
                    nextMatrix[y-startHeight][x]=1;
                }
                //等于2，则与上一状态一样
                else if(nearNum==2){
                    nextMatrix[y-startHeight][x]=matrix[y][x];
                }

            }
        }
        return nextMatrix;
    }

    public int findLifedNum(int y, int x){
        int num=0;
        //左边
        if(x!=0){
            num+=matrix[y][x-1];
        }
        //左上角
        if(x!=0&&y!=0){
            num+=matrix[y-1][x-1];
        }
        //上边
        if(y!=0){
            num+=matrix[y-1][x];
        }
        //右上
        if(x!=width-1&&y!=0){
            num+=matrix[y-1][x+1];
        }
        //右边
        if(x!=width-1){
            num+=matrix[y][x+1];
        }
        //右下
        if(x!=width-1&&y!=height-1){
            num+=matrix[y+1][x+1];
        }
        //下边
        if(y!=height-1){
            num+=matrix[y+1][x];
        }
        //左下
        if(x!=0&&y!=height-1){
            num+=matrix[y+1][x-1];
        }
        return num;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int[] ints : matrix) {
            sb.append(Arrays.toString(ints)).append("\n");
        }
        return sb.toString();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}

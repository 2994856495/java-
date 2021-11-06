package exercise1.Point_1;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
    }

    @Override
    public String toString() {
        return "该点的坐标为("+this.x+","+this.y+")";
    }

    /**将一个点x,y向X和Y轴移动dx和dy*/
    public void move(int dx, int dy){
        x+=dx;
        y+=dy;
    }
    /**int distance(Position p): 计算当前对象this和对象p的距离。*/
    public int distance(Position p){
        return (int) Math.sqrt(Math.pow((p.x-this.x),2)+Math.pow((p.y-this.y),2));
    }
    /**Position scale(double f):此this对象的x,y值乘以一个系数f。*/
    public Position scale(double f){
        this.x*=f;
        this.y*=f;
        return this;
    }


}

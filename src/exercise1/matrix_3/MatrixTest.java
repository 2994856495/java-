package exercise1.matrix_3;

public class MatrixTest {
    public static void main(String[] args) {
        Matrix m1 = new Matrix("1,2,3,4,5,6,7,8,9",3);
        Matrix m2=new Matrix("9,8,7,6,5,4,3,2,1",3);

        System.out.println(m1.add("1,2,3,4,5,6,7,8,9",3));
        System.out.println(m1.subtraction("1,2,3,4,5,6,7,8,9",3));
        System.out.println(m1.multiply("1,2,3,4,5,6,7,8,9",3));
        System.out.println(m1.rotation());

        System.out.println("*********************************");

        System.out.println(m2.add("1,2,3,4,5,6,7,8,9",3));
        System.out.println(m2.subtraction("1,2,3,4,5,6,7,8,9",3));
        System.out.println(m2.multiply("1,2,3,4,5,6,7,8,9",3));
        System.out.println(m2.rotation());




    }
}

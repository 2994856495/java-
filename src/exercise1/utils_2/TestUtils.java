package exercise1.utils_2;

public class TestUtils {

    public static void assertEquals(String a, String b, String c){
        if(a.equals(b)){
            System.out.println("The two are equal");
        }
        else{
            System.out.println(c+":"+"expecting "+a+"("+a.getClass()+")" +
                    "but got "+b+"("+b.getClass()+")");
        }
    }

    public static void assertEquals(int a,int b, String s){
        Integer value1 = Integer.valueOf(a);
        Integer value2 = Integer.valueOf(b);
        if(value1.equals(value2)){
            System.out.println("The two are equal");
        }
        else{
            System.out.println(s+":"+"expecting "+value1+"("+value1.getClass()+")" +
                    "but got "+value2+"("+value2.getClass()+")");
        }
    }
}

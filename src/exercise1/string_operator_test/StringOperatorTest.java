package exercise1.string_operator_test;

import exercise1.string_operator_4.StringOperator;
import org.junit.Test;

import static exercise1.utils_2.TestUtils.assertEquals;

public class StringOperatorTest {
    @Test
    public void test0(){

        String strAppend = StringOperator.strAppend("ab", 2);
        assertEquals(strAppend,"abab","Err!");
        System.out.println("****************");

        String strCompress = StringOperator.strCompress("aaaaabcdadddd");
        assertEquals(strCompress,"a5bcdad4","Err!");
        System.out.println("****************");

        String strEncryt = StringOperator.strEncryt("abcdefgh", 3);
        assertEquals(strEncryt,"defghijk","Err!");
        System.out.println("*****************");

        boolean b = StringOperator.stringIntersect("abcdefg", "abcdghj", 3);
        System.out.println(b);
        System.out.println("*****************");

    }

}

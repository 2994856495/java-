package exercise1.string_operator_4;
/** 改用StringBuffer或者StringBuilder实现复制粘贴string功能。比较两种方法有和不同。
 * //        String strAppend = StringOperator.strAppend("ab", 2000);
 * //        可以执行成功，无异常，
 * //        string不可变，内存分配字符串在堆空间中，每次增加一个字符串，便重新在堆空间生成新的字符串序列
 * //        当进行垃圾回收时会变慢
 * 
 * //        StringOperator.strBufferAppend(new StringBuffer("dc"),2000);
 * //        Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * //        可变的字符串，
 * //        容量不超过Integer.MAX_VALUE的前提下，
 * //        初始化时大小为capacity+16，
 * //        扩容：capacity*2+2，直至满足条件，此时类似于string的内存分配
 *
 * */
public class StringOperator {
    //将str做n次复制，粘贴在原str后面，例如str为“java”，复制粘贴2次后结果为“javajavajava”。
    public static String strAppend(String str,int n){
        if(n<0){
            return "error!";
        }
        String temp=str;
        for(int i=0;i<n;i++){
            str+=temp;
        }
        return str;
    }

    //改用StringBuffer或者StringBuilder实现复制粘贴string功能。比较两种方法有和不同。
    public static void strBufferAppend(StringBuffer a,int n){
        if(n<0){
            System.out.println("error!");
        }
        StringBuffer temp=a;
        for(int i=0;i<n;i++){
            a=a.append(temp);
        }
        System.out.println(a);


    }

    //将给定的字符串，连续重复出现的字母用数字表示重复率，输出压缩后的字符串。例如”aaaa”压缩为”a4”
    // 注：仅是单个字符连续才压缩，如babababa则不能压缩 输入：srcStr = "aaacccddef" 返回："a3c3d2ef"。
    public static String strCompress(String str){
        StringBuffer temp = new StringBuffer();
        int count = 1;
        str+=" ";
        for(int i=0;i<str.length()-1;i++){
            if(str.charAt(i)==str.charAt(i+1)){
                count+=1;
            }
            else{
                temp.append(str.charAt(i));
                if(count!=1){
                    temp.append(count);
                }
                count=1;
            }
        }
        return temp.toString();
    }

    //将str进行字符串加密，返回加密后结果。加密算法是将每个字母依次向后偏移n位，例如字母A偏移3位后为D。
    public static String strEncryt(String str,int n){
        StringBuffer temp=new StringBuffer();
        for(int i=0;i<str.length();i++){
            char ch = (char) (str.charAt(i) + n);
            temp.append(ch);
        }
        return temp.toString();
    }

    //查找两个字符串是否包括相同长度为n的子字符串
    public static boolean stringIntersect(String a,String b,int len){
        String temp;
        if(a.length()<len||b.length()<len){
            return false;
        }
        for(int i=0;i<a.length()-len;i++){
            temp=a.substring(i,i+len);
            if(b.indexOf(temp)!=-1){
                return true;
            }
        }
        return false;
    }
}

package exercise2.java03;

import org.junit.Test;

import java.util.*;

public class Constructor {
    //测试add(),multiply()
    @Test
    public void test1(){
        Odd[] odds=new Odd[50];
        Even[] evens=new Even[50];

        Random random = new Random();
        for(int i=0;i<odds.length;i++){
            odds[i]=new Odd(random.nextInt(200));
            evens[i]=new Even(random.nextInt(200));
        }
        System.out.println("测试加法：");
        for (int i=0;i<5;i++){
            System.out.println(odds[i].getNumber()+"+"+odds[i+1].getNumber()+"="+odds[i].add(odds[i+1]));
            System.out.println(evens[i*2].getNumber()+"+"+odds[i*2+1].getNumber()+"="+evens[i*2].add(odds[i*2+1]));
            System.out.println(odds[i*2].getNumber()+"+"+evens[i*2+1].getNumber()+"="+odds[i*2].add(evens[i*2+1]));
            System.out.println(evens[i*3].getNumber()+"+"+evens[i*3+1].getNumber()+"="+evens[i*3].add(evens[i*3+1]));
        }

        System.out.println("测试乘法：");
        for (int i=0;i<5;i++){
            System.out.println(odds[i*4].getNumber()+"*"+odds[i*4+1].getNumber()+"="+odds[i*4].mutiply(odds[i*4+1]));
            System.out.println(evens[i*5].getNumber()+"*"+odds[i*5+1].getNumber()+"="+evens[i*5].mutiply(odds[i*5+1]));
            System.out.println(odds[i*6].getNumber()+"*"+evens[i*6+1].getNumber()+"="+odds[i*6].mutiply(evens[i*6+1]));
            System.out.println(evens[i*7].getNumber()+"*"+evens[i*7+1].getNumber()+"="+evens[i*7].mutiply(evens[i*7+1]));
        }


    }
    //去除set中重复元素
    @Test
    public void test2(){
        HashSet hashSet = new HashSet();
       // HashMap hashMap = new HashMap();
        Odd[] odds=new Odd[50];
        Even[] evens=new Even[50];

        Random random = new Random();
        for(int i=0;i<odds.length;i++){
            odds[i]=new Odd(random.nextInt(200));
            hashSet.add(odds[i]);
            evens[i]=new Even(random.nextInt(200));
            hashSet.add(evens[i]);
        }

        System.out.println("去重结果：");
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            if(next instanceof Odd){
                System.out.println(" value="+((Odd) next).getNumber());
            }
            else if(next instanceof Even){
                System.out.println(" value="+((Even) next).getNumber());
            }
            else{
                System.out.println("error!");
            }

        }



    }




}

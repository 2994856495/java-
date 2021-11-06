package exercise1.magic_squares_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MagicSquares {
    public static boolean testDiagonal(String pathName) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(pathName));
        boolean isMagic = true;
        // For each line in the file ...
        String line;
        ArrayList<ArrayList> arrayList = new ArrayList<ArrayList>();
        //将所有元素以行的方式存入arraylist中，而arraylist中每个元素也为一个arraylist，
        //arraylist-->list1-->string
        while ((line = reader.readLine()) != null) {
            // ... sum each row of numbers
            //split("\t")时分割不完全
            String[] parts = line.split(" ");
            List<String> list = Arrays.asList(parts);
            ArrayList list1 = new ArrayList(list);
            Iterator iterator = list1.iterator();
            while(iterator.hasNext()){
                if(iterator.next()==""){
                    iterator.remove();
                }
            }
            arrayList.add(list1);
        }
        int[]sum=new int[2];
        int size = arrayList.size();
        for(int i=0;i<size;i++){
           //int j=i;
            ArrayList list = arrayList.get(i);
            String s = (String) list.get(i);
            String s1 = (String) list.get(size-1 - i);
            sum[0]+=Integer.parseInt(s);
            sum[1]+=Integer.parseInt(s1);

        }
        if(sum[0]!=sum[1]){
            reader.close();
            isMagic=false;
            return isMagic;
        }
        reader.close();
        return isMagic;
    }

    public static boolean testColumn(String pathName) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(pathName));
        boolean isMagic = true;
        int lastSum = -1;
        // For each line in the file ...
        String line;
        ArrayList<ArrayList> arrayList = new ArrayList<ArrayList>();
        //将所有元素以行的方式存入arraylist中，而arraylist中每个元素也为一个arraylist，
        //arraylist-->list1-->string
        while ((line = reader.readLine()) != null) {
            // ... sum each row of numbers
            //split("\t")时分割不完全
            String[] parts = line.split(" ");
            List<String> list = Arrays.asList(parts);
            ArrayList list1 = new ArrayList(list);
            Iterator iterator = list1.iterator();
            while(iterator.hasNext()){
                if(iterator.next()==""){
                    iterator.remove();
                }
            }
            arrayList.add(list1);
        }
        int[]sum=new int[arrayList.size()];
        Iterator iterator = arrayList.iterator();
        while(iterator.hasNext()){
            ArrayList s= (ArrayList) iterator.next();
            Iterator iterator1 = s.iterator();
            int i=0;
            while (iterator1.hasNext()) {
                sum[i]+=Integer.parseInt(String.valueOf(iterator1.next()));
                i++;
            }
        }
        if (lastSum == -1) {
            // If this is the first row, remember the sum
            lastSum = sum[0];
        }
        for(int s:sum){
            if(s!=lastSum){
                reader.close();
                isMagic=false;
                return isMagic;
            }
        }
        reader.close();
        return isMagic;
    }

    public static boolean testMagic(String pathName) throws IOException {
        // Open the file
        BufferedReader reader = new BufferedReader(new FileReader(pathName));

        boolean isMagic = true;
        int lastSum = -1;
        
        // For each line in the file ...
        String line;
        while ((line = reader.readLine()) != null) {

            // ... sum each row of numbers
            //split("\t")时分割不完全
            String[] parts = line.split(" ");
            List<String> list = Arrays.asList(parts);
            ArrayList list1 = new ArrayList(list);
            Iterator iterator = list1.iterator();
            while(iterator.hasNext()){
                if(iterator.next()==""){
                    iterator.remove();
                }
            }
            int sum = 0;
            Iterator iterator1 = list1.iterator();
            while(iterator1.hasNext()){
                sum+=Integer.parseInt((String) iterator1.next());

            }
            if (lastSum == -1) {
                // If this is the first row, remember the sum
                lastSum = sum;
            } else if (lastSum != sum) {
                // if the sums don't match, it isn't magic, so stop reading
                isMagic = false;
                break;
            }
        }
        reader.close();
        return isMagic;
    }

    public static void main(String[] args) throws IOException {
        String[] fileNames = { "Mercury.txt", "Luna.txt" };
        for (String fileName : fileNames) {
            System.out.println(fileName + " is magic to the line? " + testMagic(fileName));
            System.out.println(fileName + " is magic to the column? " + testColumn(fileName));
            System.out.println(fileName + " is magic to the diagonal? " + testDiagonal(fileName));        }

    }
}

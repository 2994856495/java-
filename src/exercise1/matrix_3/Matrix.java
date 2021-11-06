package exercise1.matrix_3;

@SuppressWarnings("unused")
public class Matrix {
    private String inArr;
    private int n;

    public Matrix() {

    }

    public Matrix(String inArr, int n) {
        if(inArr.length()==(2*n*n-1)){
            this.inArr = inArr;

        }
        else if(inArr.length()>=(2*n*n-1)){
            this.inArr=inArr.substring(0,2*n);
        }
        else{

            int i=n+(inArr.length()+1)/2;
            while(i!=0){
                this.inArr= new StringBuilder().append(inArr).append(",0").toString();
                i--;
            }
        }

        this.n = n;

    }

    public void showString() {
        String temp=this.inArr;
        int[][] tempArray=new int[this.n][this.n];
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                tempArray[i][j]=Integer.parseInt(temp.substring(2*count,2*count+1));
                count++;
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(tempArray[i][j]+" ");
            }
            System.out.println();
        }

    }

    //实现两个N*N矩阵的相加，返回相加后的字符矩阵。
    public String add(String inArr,int n){

        if(this.n!=n){
            return "error!";
        }
        else{
            String temp=this.inArr;
            StringBuilder builder = new StringBuilder();
            for(int i=0;i<2*n*n;i++){
                if(i%2==0){
                    int b= Integer.parseInt(String.valueOf(temp.charAt(i)))+
                            Integer.parseInt(String.valueOf(inArr.charAt(i)));
                    builder.append(String.valueOf(b));

                }
                else{
                    builder.append(",");
                }
            }
            String newString = builder.substring(0,builder.length()-1);
            return newString;
        }

    }

    //实现两个N*N矩阵的相减，返回相加后的字符矩阵。
    public String subtraction(String inArr,int n){

        if(this.n!=n){
            return "error!";
        }
        else{
            String temp=this.inArr;
            StringBuilder builder = new StringBuilder();
            for(int i=0;i<2*n*n;i++){
                if(i%2==0){
                    int b= Integer.parseInt(String.valueOf(temp.charAt(i)))-
                            Integer.parseInt(String.valueOf(inArr.charAt(i)));
                    builder.append(String.valueOf(b));

                }
                else{
                    builder.append(",");
                }
            }
            String newString = builder.substring(0,builder.length()-1);
            return newString;
        }

    }

    // 实现两个N*N矩阵的相乘，返回相乘后的字符矩阵。
    public String multiply(String inArr,int n){

        if(this.n!=n){

            return "error!";
        }
        else{
            String temp=","+this.inArr;
            inArr=","+inArr;
            StringBuilder builder = new StringBuilder();
            int[][] tempArray=new int[this.n][this.n];
            int[][] inArrArray=new int[this.n][this.n];
            int[][] a=new int[this.n][this.n];
            int b=0;
            int count=1;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    tempArray[i][j]=Integer.parseInt(String.valueOf(temp.charAt(2*count-1)));
                    inArrArray[i][j]=Integer.parseInt(String.valueOf(inArr.charAt(2*count-1)));
                    count+=1;
                }
            }

            for(int i=0;i<n;i++){
                for (int j=0;j<n;j++){
                    for(int k=0;k<n;k++){
                        a[i][j]+=tempArray[i][k]*inArrArray[k][j];
                    }
                }
            }

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    builder.append(",").append(String.valueOf(a[i][j]));
                }
            }

            String newString = builder.substring(1,builder.length());
            return newString;
        }

    }

    //将N*N矩阵的行列互换，返回转值后的字符矩阵，
    // 例如，输入InArr ="1,2,3,4,5,6,7,8,9"，n=3 返回："1,4,7,2,5,8,3,6,9"。
    public String rotation(){

        String temp=","+this.inArr;
        int[][] tempArray=new int[this.n][this.n];
        int[][] array=new int[this.n][this.n];
        int count=1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                tempArray[i][j]=Integer.parseInt(String.valueOf(temp.charAt(2*count-1)));
                array[j][i]=tempArray[i][j];
                count+=1;
            }
        }

        StringBuilder builder=new StringBuilder();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                builder.append(",").append(String.valueOf(array[i][j]));
            }
        }
        String newString = builder.substring(1);
        return newString;
    }

}

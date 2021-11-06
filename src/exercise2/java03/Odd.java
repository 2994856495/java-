package exercise2.java03;


public class Odd{
    private int oddNumber;

    public Odd(int oddNumber) {
        if(oddNumber%2!=0){
            this.oddNumber = oddNumber;
        }
        else{
            this.oddNumber=1+oddNumber;
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("你的参数为偶数,故新的参数为："+this.oddNumber);
            }
            //
        }

    }

    public int getNumber() {
        return oddNumber;
    }

    public void setOddNumber(int oddNumber) {
        this.oddNumber = oddNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if(o instanceof Odd){
            Odd odd = (Odd) o;
            if(odd.oddNumber==this.oddNumber){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }

    }
    @Override
    public int hashCode() {
        if (this==null){
            return 0;
        }
        int result=17;
        Integer integer = Integer.valueOf(this.getNumber());
        result = 31 * result + (integer == null ? 0 : integer.hashCode());
        return result;
    }
/*if (a == null)
            return 0;

        int result = 1;

        for (Object element : a)
            result = 31 * result + (element == null ? 0 : element.hashCode());

        return result;*/
    @Override
    public String toString() {
        return String.valueOf(this.getNumber());
    }

    public Object add(Object o1) {
        if(this.judge(o1)){
            Odd o= (Odd)o1;
            return new Even(this.getNumber()+o.getNumber());
        }
        else{
            Even o=(Even) o1;
            return new Odd(this.getNumber()+o.getNumber());
        }
    }

    public boolean judge(Object o1){//判断是否为相同类型
        if(o1.getClass()==this.getClass()){
            return true;
        }
        return false;
    }



    public Object mutiply(Object o1) {
        if(this.judge(o1)){
            Odd o= (Odd)o1;
            return new Odd(this.getNumber()*o.getNumber());
        }
        else{
            Even o=(Even) o1;
            return new Even(this.getNumber()*o.getNumber());
        }

    }


}

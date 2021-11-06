package exercise2.java03;

public class Even {
    private int evenNumber ;

    public Even (int evenNumber) {
        if(evenNumber%2==0){
            this.evenNumber = evenNumber;
        }
        else{
            this.evenNumber = evenNumber+1;
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("你的参数为奇数，故新的参数为"+this.evenNumber);
            }
            //
        }

    }

    public int getNumber() {
        return evenNumber;
    }

    public void setEvenNumber(int evenNumber) {
        this.evenNumber = evenNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if(o instanceof Even){
            Even e = (Even) o;
            if(this.evenNumber==e.evenNumber){
                return true;
            }
            return false;

        }
        else{
            return false;
        }

    }

    @Override
    public int hashCode() {
        int result=17;
        Integer integer = Integer.valueOf(this.getNumber());
        result=31*result+(integer==null?0:integer.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getNumber());
    }

    public Object add(Object o1) {
        if(this.judge(o1)){
            Even o= (Even) o1;
            return new Even(this.getNumber()+o.getNumber());
        }
        else {
            Odd o = (Odd) o1;
            return new Odd(this.getNumber() + o.getNumber());
        }

    }

    public boolean judge(Object o1){//判断是否为相同类型
        return o1.getClass()==this.getClass();
    }

    public Object mutiply(Object o1) {
        if(this.judge(o1)){
            Even o= (Even) o1;
            return new Even(this.getNumber()*o.getNumber());
        }
        else{
            Odd o=(Odd) o1;
            return new Even(this.getNumber()*o.getNumber());
        }
    }
}

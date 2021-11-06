package exercise1.Point_1;

public class Foo {
    private int a;

    public Foo(int a){ this.a  =  a; }
    public Foo(Foo that){ this.a = that.a;}

    @Override
    public String toString() {
        return "Foo{" +
                "a=" + a +
                '}';
    }
}

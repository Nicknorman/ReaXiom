package ReaXiom.ObservableWrapper

/**
 * Created by Nick on 07-05-2017.
 */
class RIntTester {
    public static void main(String[] args) {
        def a = new Rax(5);
        println a

        def b = new Rax(a);
        println b

        a.setValue(7);

        println a
        println b
    }
}

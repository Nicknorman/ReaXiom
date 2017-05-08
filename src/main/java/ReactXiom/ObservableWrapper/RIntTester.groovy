package ReactXiom.ObservableWrapper

/**
 * Created by Nick on 07-05-2017.
 */
class RIntTester {
    public static void main(String[] args) {
        def a = new RInt(5);
        def b = new RInt(2);
        def c = new RInt();
        c << a + b;

        println b

        a.set(7);

        println b
    }
}

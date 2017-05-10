import ReaXiom.ObservableWrapper.Rax;

/**
 * Created by Nick on 10-05-2017.
 */
class RaxTester extends GroovyTestCase {

    void testRaxSubscribeToRax() {
        Rax a = new Rax(5);
        Rax b = new Rax(a);

        assert a.getValue() == b.getValue()

        Rax c = new Rax();
        c.subscribeTo(a);

        assert a.getValue() == c.getValue();

        Rax d = new Rax();
        d << a;

        assert a.getValue() == d.getValue();
    }

    void testRaxSubscribeToChaining() {
        Rax a = new Rax(5);
        Rax b = new Rax();
        Rax c = new Rax();
        c << b << a;

        assert a.getValue() == c.getValue();

        a.setValue(10);

        assert a.getValue() == c.getValue();
    }

    void testRaxUpdateDifferentValueTypesException() {
        Rax a = new Rax(true);
        Rax b = new Rax(5);

        shouldFail (RuntimeException) {
            b << a;
        }
    }
}

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

    void testRaxSubscribeChaining() {
        Rax a = new Rax(5);
        Rax b = new Rax();
        Rax c = new Rax();
        c << b << a;

        assert a.getValue() == c.getValue();

        a.setValue(10);

        assert a.getValue() == c.getValue();
    }

    void testRaxSubscribeToBinding() {
        Rax a = new Rax(7);
        Rax b = new Rax(2);
        Rax c = new Rax();

        c << a + b;

        assert c() == 9

        a.setValue(17)

        assert c() == 19
    }

    void testRaxSetValueWhileSubscribedException() {
        Rax a = new Rax(5);
        Rax b = new Rax(a);

        shouldFail (RuntimeException) {
            b.setValue(1);
        }
    }

    void testRaxUpdateDifferentValueTypesException() {
        Rax a = new Rax(true);
        Rax b = new Rax(5);

        shouldFail (RuntimeException) {
            b << a;
        }
    }

    void testRaxSubscribeToItselfException() {
        Rax a = new Rax();

        shouldFail (RuntimeException) {
            a << a;
        }
    }
}

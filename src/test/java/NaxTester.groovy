import ReaXiom.ObservableCore.Bax
import ReaXiom.ObservableCore.Nax
import ReaXiom.ObservableCore.Rax;

/**
 * Created by Nick on 10-05-2017.
 */
class NaxTester extends GroovyTestCase {

    void testRaxSubscribeToRax() {
        Rax a = new Nax(5);
        Rax b = new Nax(a);

        assert a.getValue() == b.getValue()

        Rax c = new Nax();
        c.subscribeTo(a);

        assert a.getValue() == c.getValue();

        Rax d = new Nax();
        d << a;

        assert a.getValue() == d.getValue();
    }

    void testRaxSubscribeChaining() {
        Rax a = new Nax(5);
        Rax b = new Nax();
        Rax c = new Nax();
        c << b << a;

        assert a.getValue() == c.getValue();

        a.setValue(10);

        assert a.getValue() == c.getValue();
    }

    void testRaxSubscribeToBinding() {
        Nax a = new Nax(7);
        Nax b = new Nax(2);
        Nax c = new Nax();

        c << a + b;

        assert c() == 9

        a.setValue(17)

        assert c() == 19
    }

    void testRaxSetValueWhileSubscribedException() {
        Nax a = new Nax(5);
        Nax b = new Nax(a);

        shouldFail (RuntimeException) {
            b.setValue(1);
        }
    }

    void testRaxUpdateDifferentValueTypesException() {
        Rax a = new Bax(true);
        Rax b = new Nax(5);

        shouldFail (RuntimeException) {
            b << a;
        }
    }

    void testRaxSubscribeToItselfException() {
        Rax a = new Nax();

        shouldFail (RuntimeException) {
            a << a;
        }
    }
}

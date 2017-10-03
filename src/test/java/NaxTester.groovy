import ReaXiom.core.Bax
import ReaXiom.core.Nax
import ReaXiom.core.Rax;

/**
 * Created by Nick on 10-05-2017.
 */
class NaxTester extends GroovyTestCase {

    void testRaxSubscribeToRax() {
        Rax a = new Nax(5);
        Rax b = new Nax(a);

        assert a.get() == b.get()

        Rax c = new Nax();
        c.subscribeTo(a);

        assert a.get() == c.get();

        Rax d = new Nax();
        d << a;

        assert a.get() == d.get();
    }

    void testRaxSubscribeChaining() {
        Rax a = new Nax(5);
        Rax b = new Nax();
        Rax c = new Nax();
        c << b << a;

        assert a.get() == c.get();

        a.set(10);

        assert a.get() == c.get();
    }

    void testRaxSubscribeToBinding() {
        Nax a = new Nax(7);
        Nax b = new Nax(2);
        Nax c = new Nax();

        c << a + b;

        assert c() == 9

        a.set(17)

        assert c() == 19
    }

    void testRaxsetWhileSubscribedException() {
        Nax a = new Nax(5);
        Nax b = new Nax(a);

        shouldFail (RuntimeException) {
            b.set(1);
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

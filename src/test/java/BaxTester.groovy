import ReaXiom.core.Bax

/**
 * Created by Nick on 27-05-2017.
 */
class BaxTester extends GroovyTestCase {

    public void testBaxBasicSubscribe() {
        Bax a = new Bax();
        Bax b = new Bax();
        b << a
        a << true

        assert b()

        a << false

        assert !b()
    }

    public void testBaxChangeListener() {
        Bax a = new Bax();

        a.addListener({obs, o, n ->
            assert o != n
        })

        a << false
        a << true
    }
}

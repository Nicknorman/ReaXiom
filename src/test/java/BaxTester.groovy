import ReaXiom.ObservableCore.Bax

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
}

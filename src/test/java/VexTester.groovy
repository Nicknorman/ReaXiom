import ReaXiom.core.ChangeListener
import ReaXiom.core.Nax
import ReaXiom.core.Vex

/**
 * Created by Nick on 30-05-2017.
 */
class VexTester extends GroovyTestCase {

    public void testVexValueReadOnly() {
        Integer[] arr = [1,3,5,7,9]
        Vex<Integer> a = new Vex(arr)

        a()[0] = 4
        a[1] = 4

        assert a[0] == 1
        assert a[1] == 4
    }

    public void testVexSubscribe() {
        Integer[] arr = [1,3,5,7,9]
        Vex<Integer> a = new Vex(arr)
        Vex<Integer> b = new Vex(a);

        assert b[0] == 1
        assert b[1] == 3

        a[0] = 4

        assert b[0] == 4
        assert b[1] == 3
    }

    public void testVexUpdateWhenAxervableChange() {
        Nax[] axArr = [new Nax(1), new Nax(3), new Nax(5)]
        Vex<Nax> a = new Vex(axArr)
        Vex<Nax> b = new Vex(a);

        assert b[0]() == 1
        assert b[1]() == 3
        assert b[2]() == 5

        a[0].set(2)

        assert b[0]() == 2
        assert b[1]() == 3
        assert b[2]() == 5

        a[1].set(6)
        a[2].set(10)

        assert b[0]() == 2
        assert b[1]() == 6
        assert b[2]() == 10

        Nax[] axArr2 = [new Nax(21)]
        a.set(axArr2)

        assert b[0]() == 21
    }

    public void testListenerSuccessfullyAddedAndRemovedFromOldAxervableElement() {
        Nax[] axArr = [new Nax(1), new Nax(3), new Nax(5)]
        Vex<Nax> a = new Vex(axArr)
        Nax n1 = a[0]

        assert n1.getChangeListeners().size() == 1

        Nax[] axArr2 = [new Nax(21)]
        Nax n2 = axArr2[0]
        a.set(axArr2)

        assert n1.getChangeListeners().size() == 0
        assert n2.getChangeListeners().size() == 1

        Nax n3 = new Nax(17)
        a[0] = n3

        assert n2.getChangeListeners().size() == 0
        assert n3.getChangeListeners().size() == 1
    }
}

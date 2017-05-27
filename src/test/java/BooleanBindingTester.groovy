import ReaXiom.ObservableWrapper.Bax
import ReaXiom.ObservableWrapper.Nax
import ReaXiom.ObservableWrapper.Rax

/**
 * Created by Nick on 17-05-2017.
 */
class BooleanBindingTester extends GroovyTestCase {

    void testBooleanBindingsBooleanValues() {
        Rax a = new Bax(false);
        Rax b = new Bax(false);
        Rax and = new Bax(a & b);
        Rax or = new Bax(a | b);
        Rax xor = new Bax(a ^ b);

        assert !and()
        assert !or()
        assert !xor()

        a.setValue(true)

        assert !and()
        assert or()
        assert xor()

        b.setValue(true)

        assert and()
        assert or()
        assert !xor()
    }

    void testBooleanBindingsBooleanValuesThreeRax() {
        Bax a = new Bax(false)
        Bax b = new Bax(false)
        Bax c = new Bax(false)
        Bax and = new Bax(a & b & c)
        Bax or = new Bax(a | b | c)
        Bax xor = new Bax(a ^ b ^ c)
        Bax andOr = new Bax(a & b | c)
        Bax xorAnd = new Bax((a ^ b) & c)

        assert !and()
        assert !or()
        assert !xor()
        assert !andOr()
        assert !xorAnd()

        a.setValue(true)

        assert !and()
        assert or()
        assert xor()
        assert !andOr()
        assert !xorAnd()

        b.setValue(true)

        assert !and()
        assert or()
        assert !xor()
        assert andOr()
        assert !xorAnd()

        c.setValue(true)

        assert and()
        assert or()
        assert xor()
        assert andOr()
        assert !xorAnd()
    }

    void testBooleanBindingsNumberValues() {
        Nax a = new Nax(10)
        Nax b = new Nax(10)

        Bax equal = new Bax(a.equalTo(b))
        Bax notEqual = new Bax(a.notEqualTo(b))
        Bax greater = new Bax(a.greaterThan(b))
        Bax less = new Bax(a.lessThan(b))
        Bax greaterOrEqual = new Bax(a.equalOrGreaterThan(b))
        Bax lessOrEqual = new Bax(a.equalOrLessThan(b))

        assert equal()
        assert !notEqual()
        assert !greater()
        assert !less()
        assert greaterOrEqual()
        assert lessOrEqual()

        a.setValue(11)

        assert !equal()
        assert notEqual()
        assert greater()
        assert !less()
        assert greaterOrEqual()
        assert !lessOrEqual()

        b.setValue(12)

        assert !equal()
        assert notEqual()
        assert !greater()
        assert less()
        assert !greaterOrEqual()
        assert lessOrEqual()
    }
}

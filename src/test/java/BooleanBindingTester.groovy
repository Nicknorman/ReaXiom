import ReaXiom.ObservableCore.Bax
import ReaXiom.ObservableCore.BooleanBinding
import ReaXiom.ObservableCore.Nax
import ReaXiom.ObservableCore.Rax

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

    void testNumberComparisons() {
        Nax a = new Nax(15)
        Nax b = new Nax(7.23f)

        BooleanBinding greater = a.greaterThan(b)
        BooleanBinding equalOrGreater = a.equalOrGreaterThan(b)
        BooleanBinding lesser = a.lessThan(b)
        BooleanBinding equalOrLess = a.equalOrLessThan(b)
        BooleanBinding equal = a.equalTo(b)
        BooleanBinding notEqual = a.notEqualTo(b)

        assert greater()
        assert equalOrGreater()
        assert !lesser()
        assert !equalOrLess()
        assert !equal()
        assert notEqual()

        a.setValue(-3)

        assert !greater()
        assert !equalOrGreater()
        assert lesser()
        assert equalOrLess()
        assert !equal()
        assert notEqual()

        b.setValue(-3.0f)

        assert !greater()
        assert equalOrGreater()
        assert !lesser()
        assert equalOrLess()
        assert equal()
        assert !notEqual()
    }

    void testNumberComparisonsThreeNumbers() {
        Nax a = new Nax(15)
        Nax b = new Nax(7)
        Nax c = new Nax(-3)

        BooleanBinding greater = a.greaterThan(b) & b.greaterThan(c)
        BooleanBinding equalOrGreater = a.equalOrGreaterThan(b) & b.equalOrGreaterThan(c)
        BooleanBinding lesser = a.lessThan(b) & b.lessThan(c)
        BooleanBinding equalOrLess = a.equalOrLessThan(b) & b.equalOrLessThan(c)
        BooleanBinding equal = a.equalTo(b) & b.equalTo(c)
        BooleanBinding notEqual = a.notEqualTo(b) & b.notEqualTo(c)

        assert greater()
        assert equalOrGreater()
        assert !lesser()
        assert !equalOrLess()
        assert !equal()
        assert notEqual()

        c.setValue(20)

        assert !greater()
        assert !equalOrGreater()
        assert !lesser()
        assert !equalOrLess()
        assert !equal()
        assert notEqual()

        b.setValue(17)

        assert !greater()
        assert !equalOrGreater()
        assert lesser()
        assert equalOrLess()
        assert !equal()
        assert notEqual()

        b.setValue(15)

        assert !greater()
        assert !equalOrGreater()
        assert !lesser()
        assert equalOrLess()
        assert !equal()
        assert !notEqual()

        c.setValue(15)

        assert !greater()
        assert equalOrGreater()
        assert !lesser()
        assert equalOrLess()
        assert equal()
        assert !notEqual()
    }

    void testNumberComparisonsWithConstants() {
        Nax a = new Nax(15)
        int C = 10

        BooleanBinding greater = a.greaterThan(C)
        BooleanBinding equalOrGreater = a.equalOrGreaterThan(C)
        BooleanBinding lesser = a.lessThan(C)
        BooleanBinding equalOrLess = a.equalOrLessThan(C)
        BooleanBinding equal = a.equalTo(C)
        BooleanBinding notEqual = a.notEqualTo(C)

        assert greater()
        assert equalOrGreater()
        assert !lesser()
        assert !equalOrLess()
        assert !equal()
        assert notEqual()

        a.setValue(5)

        assert !greater()
        assert !equalOrGreater()
        assert lesser()
        assert equalOrLess()
        assert !equal()
        assert notEqual()

        a.setValue(10)

        assert !greater()
        assert equalOrGreater()
        assert !lesser()
        assert equalOrLess()
        assert equal()
        assert !notEqual()
    }

    void testNumberComparisonSameNumber() {
        Nax a = new Nax(5);

        BooleanBinding greater = a.greaterThan(a)
        BooleanBinding equalOrGreater = a.equalOrGreaterThan(a)
        BooleanBinding lesser = a.lessThan(a)
        BooleanBinding equalOrLess = a.equalOrLessThan(a)
        BooleanBinding equal = a.equalTo(a)
        BooleanBinding notEqual = a.notEqualTo(a)

        assert !greater()
        assert equalOrGreater()
        assert !lesser()
        assert equalOrLess()
        assert equal()
        assert !notEqual()

        a.setValue(7)

        assert !greater()
        assert equalOrGreater()
        assert !lesser()
        assert equalOrLess()
        assert equal()
        assert !notEqual()
    }

    void testBaxNot() {
        Bax a = new Bax(true)
        Bax b = new Bax(a.not())
        BooleanBinding c = a & b

        assert !b()
        assert !c()

        a.setValue(false)

        assert b()
        assert !c()
    }
}

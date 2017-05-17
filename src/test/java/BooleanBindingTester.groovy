import ReaXiom.ObservableWrapper.Rax

/**
 * Created by Nick on 17-05-2017.
 */
class BooleanBindingTester extends GroovyTestCase {

    void testBooleanBindingsBooleanValues() {
        Rax a = new Rax(false);
        Rax b = new Rax(false);
        Rax and = new Rax(a & b);
        Rax or = new Rax(a | b);
        Rax xor = new Rax(a ^ b);

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
        Rax a = new Rax(false)
        Rax b = new Rax(false)
        Rax c = new Rax(false)
        Rax and = new Rax(a & b & c)
        Rax or = new Rax(a | b | c)
        Rax xor = new Rax(a ^ b ^ c)
        Rax andOr = new Rax(a & b | c)
        Rax xorAnd = new Rax((a ^ b) & c)

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
        Rax a = new Rax(10)
        Rax b = new Rax(10)

        Rax equal = new Rax(a.equalTo(b))
        Rax notEqual = new Rax(a.notEqualTo(b))
        Rax greater = new Rax(a.greaterThan(b))
        Rax less = new Rax(a.lessThan(b))
        Rax greaterOrEqual = new Rax(a.equalOrGreaterThan(b))
        Rax lessOrEqual = new Rax(a.equalOrLessThan(b))

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

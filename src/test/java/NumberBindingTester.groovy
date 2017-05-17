import ReaXiom.ObservableWrapper.BindingType
import ReaXiom.ObservableWrapper.Binding
import ReaXiom.ObservableWrapper.NumberBinding
import ReaXiom.ObservableWrapper.Rax

/**
 * Created by Nick on 17-05-2017.
 */
class NumberBindingTester extends GroovyTestCase {
    void testNumberBindingInteger() {
        Rax a = new Rax(5);
        Rax b = new Rax(2);
        Binding binding = new NumberBinding(a, b, BindingType.ADD);

        assert binding.getValue().class == Integer.class
        assert binding.getValue() == 7

        a.setValue(10);

        assert binding.getValue() == 12

        Rax c = new Rax(binding);

        assert c() == 12
    }

    void testNumberBindingDouble() {
        Rax a = new Rax(1.25d);
        Rax b = new Rax(3);
        Binding binding = new NumberBinding(a, b, BindingType.ADD);

        assert binding.getValue() == 4.25
    }

    void testNumberBindingOperatorOverloading() {
        Rax a = new Rax(12);
        Rax b = new Rax(5);
        Binding plus = a + b;
        Binding sub = a - b;
        Binding mult = a * b;
        Binding div = a / b;
        Binding mod = a % b;
        Binding pow = a ** b;

        assert plus.getValue() == 17
        assert sub.getValue() == 7
        assert mult.getValue() == 60
        assert div.getValue() == 2
        assert mod.getValue() == 2
        assert pow.getValue() == 248832
    }

    void testNumberBindingThreeRaxOperatorOverloading() {
        Rax a = new Rax(12);
        Rax b = new Rax(5);
        Rax c = new Rax(2);
        Binding b1 = a + b - c;
        Binding b2 = a - b + c;
        Binding b3 = a * b * c;
        Binding b4 = (a - b) * c;
        Binding b5 = a % b * c;
        Binding b6 = b ** c / a;

        assert b1() == 15
        assert b2() == 9
        assert b3() == 120
        assert b4() == 14
        assert b5() == 4
        assert b6() == 2
    }
}

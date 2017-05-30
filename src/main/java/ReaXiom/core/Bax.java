package ReaXiom.core;

import java.util.Observable;

/**
 * Created by Nick on 27-05-2017.
 */
public class Bax extends Rax<Boolean> {

    public Bax(Axervable<Boolean> obs) {
        super(obs);
    }

    public Bax(Boolean value) {
        super(value);
    }

    public Bax() {
        super();
    }

    /**
     * Returns a BooleanBinding that will always be the opposite of this boolean.
     * @return
     */
    public BooleanBinding not() {
        return this.xor(true);
    }

    /**
     * '&' overloading for Groovy.
     * @param observable
     * @return
     */
    public BooleanBinding and(Observable observable) { return new BooleanBinding(this, observable, BindingType.AND); }

    public BooleanBinding and(Boolean value) { return new BooleanBinding(this, value, BindingType.AND); }

    public BooleanBinding and(Number value) { return new BooleanBinding(this, value, BindingType.AND); }

    /**
     * '|' overloading for Groovy.
     * @param observable
     * @return
     */
    public BooleanBinding or(Observable observable) { return new BooleanBinding(this, observable, BindingType.OR); }

    public BooleanBinding or(Boolean value) { return new BooleanBinding(this, value, BindingType.OR); }

    public BooleanBinding or(Number value) { return new BooleanBinding(this, value, BindingType.OR); }

    /**
     * '^' overloading for Groovy.
     * @param observable
     * @return
     */
    public BooleanBinding xor(Observable observable) { return new BooleanBinding(this, observable, BindingType.XOR); }

    public BooleanBinding xor(Boolean value) { return new BooleanBinding(this, value, BindingType.XOR); }

    public BooleanBinding xor(Number value) { return new BooleanBinding(this, value, BindingType.XOR); }
}

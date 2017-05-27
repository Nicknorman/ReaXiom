package ReaXiom.ObservableWrapper;

import java.util.Observable;

/**
 * Created by Nick on 26-05-2017.
 */
public class Nax extends Rax<Number> {
    public Nax(Number value) {
        super(value);
    }

    public Nax(Observable obs) {
        super(obs);
    }

    public Nax() {
        super();
    }


    /**
     * '+' overloading for Groovy
     * @param observable
     * @return
     */
    public NumberBinding plus(Observable observable) {
        return new NumberBinding(this, observable, BindingType.ADD);
    }

    public NumberBinding plus(Number value) {
        return new NumberBinding(this, value, BindingType.ADD);
    }

    /**
     * '-' overloading for Groovy.
     * @param observable
     * @return
     */
    public NumberBinding minus(Observable observable) {
        return new NumberBinding(this, observable, BindingType.SUBTRACT);
    }

    public NumberBinding minus(Number value) {
        return new NumberBinding(this, value, BindingType.SUBTRACT);
    }

    /**
     * '*' overloading for Groovy.
     * @param observable
     * @return
     */
    public NumberBinding multiply(Observable observable) {
        return new NumberBinding(this, observable, BindingType.MULTIPLY);
    }

    public NumberBinding multiply(Number value) {
        return new NumberBinding(this, value, BindingType.MULTIPLY);
    }

    /**
     * '/' overloading for Groovy.
     * @param observable
     * @return
     */
    public NumberBinding div(Observable observable) {
        return new NumberBinding(this, observable, BindingType.DIVIDE);
    }

    public NumberBinding div(Number value) {
        return new NumberBinding(this, value, BindingType.DIVIDE);
    }

    /**
     * '%' overloading for Groovy.
     * @param observable
     * @return
     */
    public NumberBinding mod(Observable observable) {
        return new NumberBinding(this, observable, BindingType.MOD);
    }

    public NumberBinding mod(Number value) {
        return new NumberBinding(this, value, BindingType.MOD);
    }

    /**
     * '**' overloading for Groovy.
     * @param observable
     * @return
     */
    public NumberBinding power(Observable observable) {
        return new NumberBinding(this, observable, BindingType.POWER);
    }

    public NumberBinding power(Number value) {
        return new NumberBinding(this, value, BindingType.POWER);
    }

    /**
     *
     * @param observable
     * @return
     */
    public BooleanBinding equalTo(Observable observable) {return new BooleanBinding(this, observable, BindingType.EQUAL_TO); }

    public BooleanBinding equalTo(Number value) {return new BooleanBinding(this, value, BindingType.EQUAL_TO); }

    /**
     *
     * @param observable
     * @return
     */
    public BooleanBinding notEqualTo(Observable observable) {return new BooleanBinding(this, observable, BindingType.NOT_EQUAL_TO); }

    public BooleanBinding notEqualTo(Number value) {return new BooleanBinding(this, value, BindingType.NOT_EQUAL_TO); }

    /**
     *
     * @param observable
     * @return
     */
    public BooleanBinding equalOrGreaterThan(Observable observable) {return new BooleanBinding(this, observable, BindingType.EQUAL_OR_GREATER_THAN); }

    public BooleanBinding equalOrGreaterThan(Number value) {return new BooleanBinding(this, value, BindingType.EQUAL_OR_GREATER_THAN); }

    /**
     *
     * @param observable
     * @return
     */
    public BooleanBinding equalOrLessThan(Observable observable) {return new BooleanBinding(this, observable, BindingType.EQUAL_OR_LESS_THAN); }

    public BooleanBinding equalOrLessThan(Number value) {return new BooleanBinding(this, value, BindingType.EQUAL_OR_LESS_THAN); }

    /**
     *
     * @param observable
     * @return
     */
    public BooleanBinding lessThan(Observable observable) {return new BooleanBinding(this, observable, BindingType.LESS_THAN); }

    public BooleanBinding lessThan(Number value) {return new BooleanBinding(this, value, BindingType.LESS_THAN); }

    /**
     *
     * @param observable
     * @return
     */
    public BooleanBinding greaterThan(Observable observable) {return new BooleanBinding(this, observable, BindingType.GREATER_THAN); }

    public BooleanBinding greaterThan(Number value) {return new BooleanBinding(this, value, BindingType.GREATER_THAN); }
}

package ReaXiom.ObservableWrapper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Nick on 17-05-2017.
 */
public abstract class ObservableAxiom extends Observable implements Observer {

    /**
     * Should be called by Observable that this Observer is subscribed to.
     * @param observable
     * @param o
     */
    public abstract void update(Observable observable, Object o);

    /**
     * Add observer.
     * Children should override this if f.x. instant update of observer is needed.
     * @param observer
     */
    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
    }

    /**
     * '+' overloading for Groovy
     * @param observable
     * @return
     */
    public Observable plus(Observable observable) {
        return new NumberBinding(this, observable, BindingType.ADD);
    }

    /**
     * '-' overloading for Groovy.
     * @param observable
     * @return
     */
    public Observable minus(Observable observable) {
        return new NumberBinding(this, observable, BindingType.SUBTRACT);
    }

    /**
     * '*' overloading for Groovy.
     * @param observable
     * @return
     */
    public Observable multiply(Observable observable) {
        return new NumberBinding(this, observable, BindingType.MULTIPLY);
    }

    /**
     * '/' overloading for Groovy.
     * @param observable
     * @return
     */
    public Observable div(Observable observable) {
        return new NumberBinding(this, observable, BindingType.DIVIDE);
    }

    /**
     * '%' overloading for Groovy.
     * @param observable
     * @return
     */
    public Observable mod(Observable observable) {
        return new NumberBinding(this, observable, BindingType.MOD);
    }

    /**
     * '**' overloading for Groovy.
     * @param observable
     * @return
     */
    public Observable power(Observable observable) {
        return new NumberBinding(this, observable, BindingType.POWER);
    }

    /**
     *
     * @param observable
     * @return
     */
    public Observable equalTo(Observable observable) {return new BooleanBinding(this, observable, BindingType.EQUAL_TO); }

    /**
     *
     * @param observable
     * @return
     */
    public Observable notEqualTo(Observable observable) {return new BooleanBinding(this, observable, BindingType.NOT_EQUAL_TO); }

    /**
     *
     * @param observable
     * @return
     */
    public Observable equalOrGreaterThan(Observable observable) {return new BooleanBinding(this, observable, BindingType.EQUAL_OR_GREATER_THAN); }

    /**
     *
     * @param observable
     * @return
     */
    public Observable equalOrLessThan(Observable observable) {return new BooleanBinding(this, observable, BindingType.EQUAL_OR_LESS_THAN); }

    /**
     *
     * @param observable
     * @return
     */
    public Observable lessThan(Observable observable) {return new BooleanBinding(this, observable, BindingType.LESS_THAN); }

    /**
     *
     * @param observable
     * @return
     */
    public Observable greaterThan(Observable observable) {return new BooleanBinding(this, observable, BindingType.GREATER_THAN); }

    /**
     * '&' overloading for Groovy.
     * @param observable
     * @return
     */
    public Observable and(Observable observable) { return new BooleanBinding(this, observable, BindingType.AND); }

    /**
     * '|' overloading for Groovy.
     * @param observable
     * @return
     */
    public Observable or(Observable observable) { return new BooleanBinding(this, observable, BindingType.OR); }

    /**
     * '^' overloading for Groovy.
     * @param observable
     * @return
     */
    public Observable xor(Observable observable) { return new BooleanBinding(this, observable, BindingType.XOR); }
}

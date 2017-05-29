package ReaXiom.core;

import java.util.Observable;

/**
 * Created by Nick on 06-05-2017.
 */
public abstract class Rax<T> extends Axervable<T> {
    private Observable _subscribedTo;

    protected Rax(T value) {
        this();
        super._setValue(value);
    }

    protected Rax(Observable observable) {
        this();
        this.subscribeTo(observable);
    }

    public Rax() {
        super();
    }

    /**
     * Should only be called by the Observable that this Rax is subscribed to.
     *
     * Updates this Rax's value and notifies all of its observers if {o} corresponds to its _subscribedTo Observable,
     * and {arg}'s type is the same type as this Rax's internal _value's type.
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        T value = super.getValue();

        if (o == _subscribedTo) {
            if (value == null || value.getClass().isInstance(arg)) {
                this._setValueAndNotify((T)arg);
            } else {
                throw new RuntimeException("Subscribed value type " + arg.getClass().toString() +
                        " differs from " + value.getClass().toString());
            }
        }
    }

    /**
     * Sets the internal value of this Rax and updates its observers, as long as this Rax is not subscribed to any
     * Observable.
     * @param newValue
     */
    public void setValue(T newValue) {
        if (_subscribedTo != null) {
            throw new RuntimeException("Bound object cannot be set");
        }
        this._setValueAndNotify(newValue);
    }

    /**
     * Binds this Rax to an Observable.
     * @param observable
     * @return itself as Observable to allow for chaining Observables.
     * @throws RuntimeException if subscribing to itself
     */
    public Observable subscribeTo(Observable observable) {
        if (observable == this) {
            throw new RuntimeException("Rax cannot subscribe to itself");
        }

        if (observable != null) {
            // Clear old subscription
            this.clearSubscription();

            // Set new subscription
            _subscribedTo = observable;
            observable.addObserver(this);
        }
        return this;
    }

    /**
     * Clears this Rax's subscription. Preservers the last value gained from its former Observable.
     */
    public void clearSubscription() {
        if (_subscribedTo != null) {
            _subscribedTo.deleteObserver(this);
            _subscribedTo = null;
        }
    }

    /**
     * '<<' overloading for Groovy.
     * Equivalent to subscribeTo(Observable observable)
     * @param observable
     * @return
     */
    public Observable leftShift(Observable observable) {
        return this.subscribeTo(observable);
    }
}

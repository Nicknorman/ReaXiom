package ReaXiom.ObservableWrapper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Nick on 06-05-2017.
 */
public class Rax<T> extends Observable implements Observer {
    private T _value;
    private Observable _subscribedTo;

    public Rax(T value) {
        this();
        this._value = value;
    }

    public Rax(Observable observable) {
        this();
        this.subscribeTo(observable);
    }

    public Rax() {
        super();
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

    public T getValue() {
        return _value;
    }

    /**
     * Binds this Rax to an Observable.
     * @param observable
     * @return itself as Observable to allow for chaining Observables.
     */
    public Observable subscribeTo(Observable observable) {
        if (observable != null) {
            // Clear old subscription
            this.clearSubscription();

            // Set new observable
            _subscribedTo = observable;
            observable.addObserver(this);
        }
        return this;
    }

    /**
     * Clears this Rax's subscription. Its value will be equal to the value received from its subscription's
     * last update.
     */
    public synchronized void clearSubscription() {
        if (_subscribedTo != null) {
            _subscribedTo.deleteObserver(this);
            _subscribedTo = null;
        }
    }

    @Override
    public synchronized String toString() {
        return this.getValue().toString();
    }

    /**
     * Updates this Rax's value and notifies all of its observers if {o} corrensponds to its _subscribedTo Observable,
     * and {arg}'s type is the same type as this Rax's internal _value's type.
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o == _subscribedTo) {
            if (_value == null || _value.getClass().isInstance(arg)) {
                this._setValueAndNotify((T)arg);
            } else {
                throw new RuntimeException("Subscribed value type " + arg.getClass().toString() +
                        " differs from " + _value.getClass().getName());
            }
        }
    }

    /**
     * Add observer and update it instantly.
     * @param observer
     */
    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        observer.update(this, _value);
    }

    /**
     * Sets this Rax's value and notifies all of its observers.
     * @param value
     */
    private void _setValueAndNotify(T value) {
        _value = value;
        super.setChanged();
        super.notifyObservers(_value);
        super.clearChanged();
    }

    /**
     * '<<' overloading for Groovy.
     * Is equivalent to subscribeTo(Observable observable)
     * @param observable
     * @return
     */
    public Observable leftShift(Observable observable) {
        return this.subscribeTo(observable);
    }

}

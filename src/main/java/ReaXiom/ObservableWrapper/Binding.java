package ReaXiom.ObservableWrapper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Nick on 10-05-2017.
 * Binds 2 Observables
 */
public abstract class Binding<T> extends ObservableAxiom {
    private Observable _obs1;
    private Observable _obs2;
    private Object _arg1;
    private Object _arg2;
    private T _value;
    protected BindingType _bindingType;

    /**
     *
     * @param obs1
     * @param obs2
     * @param bindingType The type of Binding that should describe the relationship between the two Observables' values.
     */
    public Binding(Observable obs1, Observable obs2, BindingType bindingType) {
        super();

        this._obs1 = obs1;
        this._obs2 = obs2;
        this._bindingType = bindingType;

        // Subscribe to both observables
        _obs1.addObserver(this);
        _obs2.addObserver(this);
    }

    /**
     * Unsafe if returned value is changed.
     * @return
     */
    public T getValue() {
        return _value;
    }

    /**
     * Should only be called by one of the two Observables that this Binding is subscribed to.
     * Updates the value associated with one its two Observables.
     * @param observable
     * @param value
     */
    public void update(Observable observable, Object value) {
        if (value == null)
            return;

        if (observable == _obs1) {
            if (_arg1 == null || _arg1.getClass().isInstance(value)) {
                _arg1 = value;
                _calcValueAndNotify();
            } else {
                throw new RuntimeException("Subscribed value type " + value.getClass().toString() +
                        " differs from " + _arg1.getClass().toString());
            }
        }
        else if (observable == _obs2) {
            if (_arg2 == null || _arg2.getClass().isInstance(value)) {
                _arg2 = value;
                _calcValueAndNotify();
            } else {
                throw new RuntimeException("Subscribed value type " + value.getClass().toString() +
                        " differs from " + _arg2.getClass().toString());
            }
        }
    }

    /**
     * Add observer and update the Observer instantly.
     * @param observer
     */
    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        observer.update(this, _value);
    }

    /**
     * Calls _calcValue() if both internal arguments are not null.
     * Notifies all its observers of the newly calculated value.
     */
    private void _calcValueAndNotify() {
            if (_arg1 != null && _arg2 != null) {
                // Update _value calculated from args
                _value = _calcValue(_arg1, _arg2);
                // Notify observers with internal calculated value
                super.setChanged();
                super.notifyObservers(_value);
                super.clearChanged();
            }
    }

    /**
     * Calculates and returns a value based on the arguments and this Binding's _bindingType.
     * @param arg1
     * @param arg2
     * @return
     */
    protected abstract T _calcValue(Object arg1, Object arg2);

    /**
     * 'a()' overloading for Groovy.
     * Equivalent to getValue()
     * @return
     */
    public T call() {
        return this.getValue();
    }
}

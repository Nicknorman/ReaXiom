package ReaXiom.core;

import java.util.Observable;

/**
 * Created by Nick on 10-05-2017.
 * Binds 2 Observables
 */
public abstract class Binding<T> extends Axervable<T> {
    private Observable _obs1;
    private Observable _obs2;
    private Object _arg1;
    private Object _arg2;
    protected BindingType _bindingType;

    /**
     * Binds two observables together using the specified binding type.
     * If both Observables are the same, it will only subscribe to the Observable once. This makes sure that no
     * "double notifying" is done to this Binding's Observers.
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
        // Only subscribe to _obs2 if both observables are different
        if (_obs1 != _obs2) {
            _obs2.addObserver(this);
        }
    }

    /**
     * Binds an observable and a constant value together using the specified binding type.
     * @param obs1
     * @param arg2
     * @param bindingType
     */
    public Binding(Observable obs1, Object arg2, BindingType bindingType) {
        super();

        this._obs1 = obs1;
        this._bindingType = bindingType;

        // Set constant argument directly
        _arg2 = arg2;
        // Subscribe to observer
        _obs1.addObserver(this);
    }

    /**
     * Should only be called by one of the two Observables that this Binding is subscribed to.
     * Updates the value associated with one its two Observables.
     * It's possible for this Binding's internal Observables to be the same, in which case both its internal values
     * are set. Therefore, only one call to this Binding's update() is necessary.
     * @param observable
     * @param value
     */
    public void update(Observable observable, Object value) {
        if (value == null)
            return;

        if (observable == _obs1) {
            if (_arg1 == null || _arg1.getClass().isInstance(value)) {
                _arg1 = value;

                // Also set _arg2 if both observables are the same, so that
                if (_obs1 == _obs2)
                    _arg2 = value;
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
     * Calls _calcValue() if both internal arguments are not null.
     * Notifies all its observers of the newly calculated value.
     */
    private void _calcValueAndNotify() {
        if (_arg1 != null && _arg2 != null) {
            // Notify observers with internal calculated value
            super._setValueAndNotify(_calcValue(_arg1, _arg2));
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
        return this.get();
    }
}

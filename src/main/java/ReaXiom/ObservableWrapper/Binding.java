package ReaXiom.ObservableWrapper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Nick on 10-05-2017.
 * Binds 2 Observables
 */
public abstract class Binding<T, A1, A2> extends Observable implements Observer {
    private Observable _obs1;
    private Observable _obs2;
    private A1 _arg1;
    private A2 _arg2;
    private T _value;
    protected BindingType _bindingType;

    /**
     *
     * @param obs1
     * @param obs2
     * @param bindingType The type of Binding that should describe the relationship between the two Observables' values.
     */
    public Binding(Observable obs1, Observable obs2, BindingType bindingType) {
        this._obs1 = obs1;
        this._obs2 = obs2;
        this._bindingType = bindingType;
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
     * @param observable
     * @param arg
     */
    @Override
    public void update(Observable observable, Object arg) {
        if (observable == _obs1) {
            _calcValueAndNotify(_arg1, arg);
        }
        else if (observable == _obs2) {
            _calcValueAndNotify(_arg2, arg);
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
     * Sets the _value of one of the two internal arguments, calls _calcValue(), and notifies all its observers.
     * @param _arg the internal argument to set.
     * @param arg the new _value that the internal _value should contain.
     */
    private void _calcValueAndNotify(Object _arg, Object arg) {
        if (_arg == null || _arg.getClass().isInstance(arg)) {
            _arg = arg;
            // Update _value calculated from args
            _value = _calcValue(_arg1, _arg2);
            // Notify observers with internal calculated value
            super.setChanged();
            super.notifyObservers(_value);
            super.clearChanged();
        } else {
            throw new RuntimeException("Subscribed _value _bindingType " + arg.getClass().toString() +
                " differs from " + _arg.getClass().toString());
        }
    }

    /**
     * Should calculate and update this Binding's _value from its internal arguments.
     */
    protected abstract T _calcValue(A1 arg1, A2 arg2);
}

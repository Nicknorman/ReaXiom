package ReaXiom.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Nick on 17-05-2017.
 */
public abstract class Axervable<T> extends Observable implements Observer {
    private T _value;
    private ArrayList<ChangeListener<T>> changeListeners;

    public Axervable() {
        super();
        changeListeners = new ArrayList<ChangeListener<T>>();
    }

    /**
     * Should be called by Observable that this Observer is subscribed to.
     * Subclasses should make sure that the implementation of this method results in a call to _setValueAndNotify()
     * if the right conditions are met.
     * @param observable
     * @param o
     */
    public abstract void update(Observable observable, Object o);

    /**
     * Adds an observer and updates it immediately.
     * @param observer
     */
    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        observer.update(this, _value);
    }

    public void addListener(ChangeListener<T> listener) {
        changeListeners.add(listener);
    }

    public void removeListener(ChangeListener<T> listener) {
        changeListeners.remove(listener);
    }

    public Collection<ChangeListener<T>> getChangeListeners() {
        return changeListeners;
    }

    /**
     * @return value held by this Axervable.
     */
    public T getValue() {
        if (_value != null)
            return _cloneValue(_value);
        return null;
    }

    /**
     * Sets this Axervable's value only if the new value is different to the old value. Notifies all of its observers
     * and invokes all ChangeListeners registered.
     * @param newValue
     */
    protected void _setValueAndNotify(T newValue) {
        // Checking that new value is different makes sure to end cyclic updating when Axervables are bound bidirectional
        if (_value != newValue) {
            T oldValue = _value;
            _setValueWithoutNotifying(newValue);

            T clonedValue = _cloneValue(newValue);

            // Invoke all ChangeListeners
            for (ChangeListener<T> cl : changeListeners) {
                cl.observableChanged(this, oldValue, clonedValue);
            }

            // Notify all Observers
            super.setChanged();
            super.notifyObservers(_cloneValue(_value));
            super.clearChanged();
        }
    }

    /**
     * Used for notifying Observers with a read-only value.
     * Subclasses should override this if the internal value is clonable.
     * @param value
     * @return
     */
    protected T _cloneValue(T value) {
        return value;
    }

    /**
     * Sets internal value without notifying Observers.
     * @param value
     */
    protected void _setValueWithoutNotifying(T value) {
        _value = value;
    }

    /**
     * 'a()' overloading for Groovy. Equivalent to getValue()
     * @return
     */
    public T call() {
        return this.getValue();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " (" + this.getValue().toString() + ")";
    }
}

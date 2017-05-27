package ReaXiom.ObservableCore;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Nick on 17-05-2017.
 */
public abstract class Axervable<T> extends Observable implements Observer {
    protected T _value;

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
     * Unsafe if returned value is changed.
     * @return value held by this Axervable.
     */
    public T getValue() {
        return _value;
    }

    /**
     * Sets this Axervable's value and notifies all of its observers only if the new value is different to the
     * old value.
     * @param newValue
     */
    protected void _setValueAndNotify(T newValue) {
        if (_value != newValue) {
            _value = newValue;
            super.setChanged();
            super.notifyObservers(_value);
            super.clearChanged();
        }
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
        return this.getValue().toString();
    }
}

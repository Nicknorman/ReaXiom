package ReaXiom.core;

import java.util.Arrays;

/**
 * Created by Nick on 30-05-2017.
 * Holds an array.
 */
public class Vex<T> extends Rax<T[]> {
    private boolean holdsAxervables = false;
    private ChangeListener<T> axervableListener;

    /**
     * Creates a new Vex containing the specified array.
     * If elements contained in array are Axervables, this Vex will notify its observers whenever an element changes
     * its value.
     * @param values
     */
    public Vex(T[] values) {
        super(values);

        // If array contains Axervables, add change listeners to each element
        if (values[0] instanceof Axervable) {
            this.holdsAxervables = true;

            // Instantiate change listener that should be added to all Axervables contained in array
            // TODO: as of now, this won't matter since value is not read-only
            axervableListener = new ChangeListener<T>() {
                @Override
                public void observableChanged(Axervable<T> obs, T oldValue, T newValue) {
                    // If Axervable is still contained in array, then notify observers, else remove this listener
                    if (Arrays.stream(values).anyMatch(v -> v == obs)) {
                        Vex.super._setValueAndNotify(values.clone());
                    } else {
                        obs.removeListener(this);
                    }
                }
            };

            this._addListenersToAxervables(values);
        }
    }

    public Vex(Axervable<T[]> obs) {
        super(obs);
    }

    public Vex() {
        super();
    }

    /**
     * {@inheritDoc}
     * @param newArray
     */
    @Override
    public void setValue(T[] newArray) {
        // A new array is required as Axervable only notifies observers if internal value reference has changed
        T[] clonedArray = newArray.clone();

        // If relevant, remove change listener from old array and add to new array
        if (this.holdsAxervables) {
            this._removeListenersFromValues(super.getValue());
            this._addListenersToAxervables(clonedArray);
        }

        super.setValue(clonedArray);
    }

    /**
     * Clones an array
     * @param value
     * @return cloned array
     */
    @Override
    protected T[] _cloneValue(T[] value) {
        return Arrays.copyOf(value, value.length);
    }

    /**
     *
     * @return length of array held by this Vex
     */
    public int length() {
        return super.getValue().length;
    }

    /**
     * 'a[i]' operator overloading for Groovy.
     * Use 'putAt()' / 'a[i] = b' or 'setValue()' for setting a new value
     * @param i
     * @return read-only value at index
     */
    public T getAt(int i) {
        return this.getValue()[i];
    }

    /**
     * 'a[i] = b' operator overloading for Groovy. Sets new value specified at position and notifies all observers.
     * @param i
     * @param newValue
     */
    public void putAt(int i, T newValue) {
        // If relevant, remove axervable from old element and add it to new
        if (this.holdsAxervables) {
            ((Axervable)this.getAt(i)).removeListener(axervableListener);
            ((Axervable)newValue).addListener(axervableListener);
        }

        // A new array is required as Axervable only notifies observers if internal value reference has changed
        T[] newArray = super.getValue().clone();
        newArray[i] = newValue;
        super.setValue(newArray);
    }

    private void _addListenersToAxervables(T[] values) {
        for (T value : values) {
            ((Axervable)value).addListener(axervableListener);
        }
    }

    private void _removeListenersFromValues(T[] values) {
        for (T value : super.getValue()) {
            ((Axervable)value).removeListener(axervableListener);
        }
    }

}

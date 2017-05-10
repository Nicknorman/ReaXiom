package ReaXiom.ObservableWrapper
/**
 * Created by Nick on 06-05-2017.
 */
class Rax<T> extends Observable implements Observer {
    private T _value;
    private Observable _subscribedTo;

    public Rax(T value) {
        super();
        this._value = value;
    }

    public Rax(Observable observable) {
        super();
        observable.addObserver(this);
        this._subscribedTo = observable;
    }

    public Rax() {
        this(new T());
    }

    public void setValue(T newValue) {
        if (_subscribedTo) {
            throw new RuntimeException("Bound object cannot be set");
        }
        this._setValueAndNotify(newValue);
    }

    public void subscribeTo(Observable observable) {
        if (observable == null) {
            return;
        }

        // Clear old subscription
        this.clearSubscription();

        // Set new observable
        observable.addObserver(this);
        _subscribedTo = observable;

        // TODO: make this more efficient when custom-observable interface is implemented
        _subscribedTo.notifyObservers();
    }

    public void clearSubscription() {
        if (_subscribedTo != null) {
            _subscribedTo.deleteObserver(this);
            _subscribedTo = null;
        }
    }

    public T getValue() {
        return _value;
    }

    @Override
    public String toString() {
        return _value.toString();
    }

    /**
     *
     * {@inherit-doc}
     * @param o
     * @param arg
     */
    @Override
    void update(Observable o, Object arg) {
        if (o == _subscribedTo) {
            if (arg instanceof T) {
                this._setValueAndNotify((T)arg);
            } else {
                throw new RuntimeException("Subscribed value type " + arg.class.toString() +
                        " differs from " + T.class.toString());
            }
        }
    }

    @Override
    public void addObserver(Observer observer) {

    }

    private void _setValueAndNotify(T value) {
        _value = value;
        super.setChanged();
        super.notifyObservers(_value);
        super.clearChanged();
    }

}

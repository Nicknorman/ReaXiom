package ReaXiom.ObservableWrapper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Nick on 10-05-2017.
 * Binds 2 Observables
 */
public class Binding<T> extends Observable implements Observer {
    private Observable _obs1;
    private Observable _obs2;
    private T _arg1;
    private T _arg2;
    private BindingType type;

    public Binding(Observable obs1, Observable obs2, BindingType type) {
        this._obs1 = obs1;
        this._obs2 = obs2;
        this.type = type;
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable == _obs1) {

        }
    }

    private void _calcValueAndNotify() {

    }
}

package ReaXiom.ObservableWrapper;

import java.util.Observable;

/**
 * Created by Nick on 16-05-2017.
 */
public class NumberBinding extends Binding<Number, Number, Number> {
    /**
     * @param obs1
     * @param obs2
     * @param type The type of Binding that should describe the relationship between the two Observables' values.
     */
    public NumberBinding(Observable obs1, Observable obs2, BindingType type) {
        super(obs1, obs2, type);
    }

    /**
     * Should calculate and update this Binding's _value from its internal arguments.
     *
     * @param arg1
     * @param arg2
     */
    @Override
    protected Number _calcValue(Number arg1, Number arg2) {

    }
}

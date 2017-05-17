package ReaXiom.ObservableWrapper;

import java.util.Observable;

/**
 * Created by Nick on 17-05-2017.
 */
public class BooleanBinding extends Binding<Boolean> {
    /**
     * @param obs1
     * @param obs2
     * @param bindingType The type of Binding that should describe the relationship between the two Observables' values.
     */
    public BooleanBinding(Observable obs1, Observable obs2, BindingType bindingType) {
        super(obs1, obs2, bindingType);
    }

    /**
     * Both arguments must either both be Booleans or Numbers.
     * @param arg1
     * @param arg2
     * @return resulting Boolean.
     */
    protected Boolean _calcValue(Object arg1, Object arg2) {
        if (arg1 instanceof Boolean && arg2 instanceof Boolean)
            return calcBooleans((Boolean)arg1, (Boolean)arg2);
        if (arg1 instanceof Number && arg2 instanceof Number)
            return calcNumbers((Number)arg1, (Number)arg2);
        throw new RuntimeException("Internal arguments must either be both a Boolean or both a Number");
    }

    private Boolean calcBooleans(Boolean arg1, Boolean arg2) {
        if (_bindingType == BindingType.AND)
            return arg1 & arg2;
        if (_bindingType == BindingType.OR)
            return arg1 | arg2;
        if (_bindingType == BindingType.XOR)
            return arg1 ^ arg2;
        throw new RuntimeException("Boolean binding type is not an usable value when comparing booleans: " + _bindingType);
    }

    private Boolean calcNumbers(Number arg1, Number arg2) {
        // All comparisons are done with the number's doubleValue()
        if (_bindingType == BindingType.EQUAL_TO)
            return arg1.doubleValue() == arg2.doubleValue();
        if (_bindingType == BindingType.NOT_EQUAL_TO)
            return arg1.doubleValue() != arg2.doubleValue();
        if (_bindingType == BindingType.EQUAL_OR_LESS_THAN)
            return arg1.doubleValue() <= arg2.doubleValue();
        if (_bindingType == BindingType.EQUAL_OR_GREATER_THAN)
            return arg1.doubleValue() >= arg2.doubleValue();
        if (_bindingType == BindingType.LESS_THAN)
            return arg1.doubleValue() < arg2.doubleValue();
        if (_bindingType == BindingType.GREATER_THAN)
            return arg1.doubleValue() > arg2.doubleValue();
        throw new RuntimeException("Boolean binding type is not an usable value when comparing numbers: " + _bindingType);
    }
}

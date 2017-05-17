package ReaXiom.ObservableWrapper;

import java.util.Observable;

/**
 * Created by Nick on 16-05-2017.
 */
public class NumberBinding extends Binding<Number, Number, Number> {
    private Class<? extends Number> _valueType;

    /**
     * @param obs1
     * @param obs2
     * @param type The type of Binding that should describe the relationship between the two Observables' values.
     */
    public NumberBinding(Observable obs1, Observable obs2, NumberBindingType type) {
        super(obs1, obs2, type);
        this._valueType = null;
    }

    public NumberBinding(Observable obs1, Observable obs2, NumberBindingType type, Class<? extends Number> valueType) {
        super(obs1, obs2, type);
        this._valueType = valueType;
    }

    /**
     * Should calculate and update this Binding's _value from its internal arguments.
     * @param arg1
     * @param arg2
     */
    @Override
    protected Number _calcValue(Number arg1, Number arg2) {
        // Initalize _valueType if it is not set.
        if (_valueType == null) {
            initValueType(arg1, arg2);
        }

        if (_bindingType == NumberBindingType.ADD)
            return addNumbers(arg1, arg2, _valueType);
        if (_bindingType == NumberBindingType.SUBTRACT)
            return subtractNumbers(arg1, arg2, _valueType);
        if (_bindingType == NumberBindingType.DIVIDE)
            return divideNumbers(arg1, arg2, _valueType);
        if (_bindingType == NumberBindingType.MULTIPLY)
            return multiplyNumbers(arg1, arg2, _valueType);
        throw new RuntimeException("Number binding type is not a known value: " + _bindingType.toString());
    }

    /**
     * Sets this NumberBindings's _valueType based on arguments.
     * This is important as NumberBinding calculation methods require a specified return type.
     * @param arg1
     * @param arg2
     */
    private void initValueType(Number arg1, Number arg2) {
        if (arg1 instanceof Double || arg2 instanceof Double)
            _valueType = Double.class;
        else if (arg1 instanceof Float || arg2 instanceof Float)
            _valueType = Float.class;
        else if (arg1 instanceof Long || arg2 instanceof Long)
            _valueType = Long.class;
        else
            _valueType = Integer.class;
    }

    private static Number addNumbers(Number a, Number b, Class<? extends Number> returnType) {
        if (returnType == Double.class)
            return a.doubleValue() + b.doubleValue();
        if (returnType == Float.class)
            return a.floatValue() + b.floatValue();
        if (returnType == Long.class)
            return a.longValue() + b.longValue();
        if (returnType == Integer.class)
            return a.intValue() + b.intValue();
        throw new RuntimeException("Return type not specified as one of the allowed values: " + returnType);
    }

    private static Number subtractNumbers(Number a, Number b, Class<? extends Number> returnType) {
        if (returnType == Double.class)
            return a.doubleValue() - b.doubleValue();
        if (returnType == Float.class)
            return a.floatValue() - b.floatValue();
        if (returnType == Long.class)
            return a.longValue() - b.longValue();
        if (returnType == Integer.class)
            return a.intValue() - b.intValue();
        throw new RuntimeException("Return type not specified as one of the allowed values: " + returnType);
    }

    private static Number multiplyNumbers(Number a, Number b, Class<? extends Number> returnType) {
        if (returnType == Double.class)
            return a.doubleValue() * b.doubleValue();
        if (returnType == Float.class)
            return a.floatValue() * b.floatValue();
        if (returnType == Long.class)
            return a.longValue() * b.longValue();
        if (returnType == Integer.class)
            return a.intValue() * b.intValue();
        throw new RuntimeException("Return type not specified as one of the allowed values: " + returnType);
    }

    private static Number divideNumbers(Number a, Number b, Class<? extends Number> returnType) {
        if (returnType == Double.class)
            return a.doubleValue() / b.doubleValue();
        if (returnType == Float.class)
            return a.floatValue() / b.floatValue();
        if (returnType == Long.class)
            return a.longValue() / b.longValue();
        if (returnType == Integer.class)
            return a.intValue() / b.intValue();
        throw new RuntimeException("Return type not specified as one of the allowed values: " + returnType);
    }
}

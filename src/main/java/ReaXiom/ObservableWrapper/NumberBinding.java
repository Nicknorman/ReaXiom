package ReaXiom.ObservableWrapper;

import com.sun.istack.internal.NotNull;

import java.util.Observable;

/**
 * Created by Nick on 16-05-2017.
 */
public class NumberBinding extends Binding<Number> {
    private Class<? extends Number> _valueType;

    /**
     * @param obs1
     * @param obs2
     * @param type The type of Binding that should describe the relationship between the two Observables' values.
     */
    public NumberBinding(Observable obs1, Observable obs2, BindingType type) {
        super(obs1, obs2, type);
        this._valueType = null;
    }

    public NumberBinding(Observable obs1, Number arg2, BindingType type) {
        super(obs1, arg2, type);
        this._valueType = null;
    }

    public NumberBinding(Observable obs1, Observable obs2, BindingType type, Class<? extends Number> valueType) {
        super(obs1, obs2, type);
        this._valueType = valueType;
    }

    /**
     * Performs a calculation of the two args.
     * @param arg1 Number
     * @param arg2 Number
     * @return Result of calculation.
     */
    @Override
    protected Number _calcValue(Object arg1, Object arg2) {
        Number nArg1 = (Number)arg1;
        Number nArg2 = (Number)arg2;

        // Initalize _valueType if it is not set.
        if (_valueType == null) {
            initValueType(nArg1, nArg2);
        }

        if (_bindingType == BindingType.ADD)
            return addNumbers(nArg1, nArg2, _valueType);
        if (_bindingType == BindingType.SUBTRACT)
            return subtractNumbers(nArg1, nArg2, _valueType);
        if (_bindingType == BindingType.DIVIDE)
            return divideNumbers(nArg1, nArg2, _valueType);
        if (_bindingType == BindingType.MULTIPLY)
            return multiplyNumbers(nArg1, nArg2, _valueType);
        if (_bindingType == BindingType.MOD)
            return modNumbers(nArg1, nArg2, _valueType);
        if (_bindingType == BindingType.POWER)
            return powNumbers(nArg1, nArg2, _valueType);
        throw new RuntimeException("Number binding type is not an usable value: " + _bindingType.toString());
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

    @NotNull
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

    @NotNull
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

    @NotNull
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

    @NotNull
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

    @NotNull
    private static Number modNumbers(Number a, Number b, Class<? extends Number> returnType) {
        if (returnType == Double.class)
            return a.doubleValue() % b.doubleValue();
        if (returnType == Float.class)
            return a.floatValue() % b.floatValue();
        if (returnType == Long.class)
            return a.longValue() % b.longValue();
        if (returnType == Integer.class)
            return a.intValue() % b.intValue();
        throw new RuntimeException("Return type not specified as one of the allowed values: " + returnType);
    }

    @NotNull
    private static Number powNumbers(Number a, Number b, Class<? extends Number> returnType) {
        if (returnType == Double.class)
            return Math.pow(a.doubleValue(), b.doubleValue());
        if (returnType == Float.class)
            return (float)Math.pow(a.doubleValue(), b.doubleValue());
        if (returnType == Long.class)
            return (long)Math.pow(a.doubleValue(), b.doubleValue());
        if (returnType == Integer.class)
            return (int)Math.pow(a.doubleValue(), b.doubleValue());
        throw new RuntimeException("Return type not specified as one of the allowed values: " + returnType);
    }
}

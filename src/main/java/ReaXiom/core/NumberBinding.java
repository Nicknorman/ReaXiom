package ReaXiom.core;

import java.util.Observable;

/**
 * Created by Nick on 16-05-2017.
 */
public class NumberBinding extends Binding<Number> {
    /**
     * The specific Number subclass that will be returned by this NumberBinding.
     */
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
     * If BindingType is DIVIDE, the value type will always be set to Double.
     * @param arg1
     * @param arg2
     */
    private void initValueType(Number arg1, Number arg2) {
        if (_bindingType == BindingType.DIVIDE)
            _valueType = Double.class;
        else if (arg1 instanceof Double || arg2 instanceof Double)
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

    /**
     * '+' overloading for Groovy
     * @param observable
     * @return
     */
    public NumberBinding plus(Observable observable) {
        return new NumberBinding(this, observable, BindingType.ADD);
    }

    public NumberBinding plus(Number value) {
        return new NumberBinding(this, value, BindingType.ADD);
    }

    /**
     * '-' overloading for Groovy.
     * @param observable
     * @return
     */
    public NumberBinding minus(Observable observable) {
        return new NumberBinding(this, observable, BindingType.SUBTRACT);
    }

    public NumberBinding minus(Number value) {
        return new NumberBinding(this, value, BindingType.SUBTRACT);
    }

    /**
     * '*' overloading for Groovy.
     * @param observable
     * @return
     */
    public NumberBinding multiply(Observable observable) {
        return new NumberBinding(this, observable, BindingType.MULTIPLY);
    }

    public NumberBinding multiply(Number value) {
        return new NumberBinding(this, value, BindingType.MULTIPLY);
    }

    /**
     * '/' overloading for Groovy.
     * @param observable
     * @return
     */
    public NumberBinding div(Observable observable) {
        return new NumberBinding(this, observable, BindingType.DIVIDE);
    }

    public NumberBinding div(Number value) {
        return new NumberBinding(this, value, BindingType.DIVIDE);
    }

    /**
     * '%' overloading for Groovy.
     * @param observable
     * @return
     */
    public NumberBinding mod(Observable observable) {
        return new NumberBinding(this, observable, BindingType.MOD);
    }

    public NumberBinding mod(Number value) {
        return new NumberBinding(this, value, BindingType.MOD);
    }

    /**
     * '**' overloading for Groovy.
     * @param observable
     * @return
     */
    public NumberBinding power(Observable observable) {
        return new NumberBinding(this, observable, BindingType.POWER);
    }

    public NumberBinding power(Number value) {
        return new NumberBinding(this, value, BindingType.POWER);
    }

    /**
     *
     * @param observable
     * @return
     */
    public BooleanBinding equalTo(Observable observable) {return new BooleanBinding(this, observable, BindingType.EQUAL_TO); }

    public BooleanBinding equalTo(Number value) {return new BooleanBinding(this, value, BindingType.EQUAL_TO); }

    /**
     *
     * @param observable
     * @return
     */
    public BooleanBinding notEqualTo(Observable observable) {return new BooleanBinding(this, observable, BindingType.NOT_EQUAL_TO); }

    public BooleanBinding notEqualTo(Number value) {return new BooleanBinding(this, value, BindingType.NOT_EQUAL_TO); }

    /**
     *
     * @param observable
     * @return
     */
    public BooleanBinding equalOrGreaterThan(Observable observable) {return new BooleanBinding(this, observable, BindingType.EQUAL_OR_GREATER_THAN); }

    public BooleanBinding equalOrGreaterThan(Number value) {return new BooleanBinding(this, value, BindingType.EQUAL_OR_GREATER_THAN); }

    /**
     *
     * @param observable
     * @return
     */
    public BooleanBinding equalOrLessThan(Observable observable) {return new BooleanBinding(this, observable, BindingType.EQUAL_OR_LESS_THAN); }

    public BooleanBinding equalOrLessThan(Number value) {return new BooleanBinding(this, value, BindingType.EQUAL_OR_LESS_THAN); }

    /**
     *
     * @param observable
     * @return
     */
    public BooleanBinding lessThan(Observable observable) {return new BooleanBinding(this, observable, BindingType.LESS_THAN); }

    public BooleanBinding lessThan(Number value) {return new BooleanBinding(this, value, BindingType.LESS_THAN); }

    /**
     *
     * @param observable
     * @return
     */
    public BooleanBinding greaterThan(Observable observable) {return new BooleanBinding(this, observable, BindingType.GREATER_THAN); }

    public BooleanBinding greaterThan(Number value) {return new BooleanBinding(this, value, BindingType.GREATER_THAN); }
}

package ReactXiom.ObservableWrapper

import javafx.beans.binding.FloatBinding
import javafx.beans.binding.IntegerBinding
import javafx.beans.binding.NumberBinding
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.value.ObservableValue

/**
 * Created by Nick on 06-05-2017.
 */
class RInt extends SimpleIntegerProperty {
    public RInt(int value) {
        super(value);
    }

    public RInt() {
        super();
    }

    public void set(int value) {
        super.set(value);
    }

    public void leftShift(RInt other) {
        super.bind(other);
    }

    public NumberBinding plus(RInt other) {
        return super.add(other);
    }

    public IntegerBinding plus(int value) {
        return super.add(value);
    }

    public FloatBinding plus(float value) {
        return super.add(value);
    }

    public NumberBinding minus(RInt other) {
        return super.subtract(other);
    }

    public IntegerBinding minus(int value) {
        return super.subtract(value);
    }

    public String toString() {
        return String.valueOf(super.get());
    }
}

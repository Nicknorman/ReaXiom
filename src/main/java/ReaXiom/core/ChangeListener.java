package ReaXiom.core;

import java.util.Observable;

/**
 * Created by Nick on 27-05-2017.
 */
public interface ChangeListener<T> {
    public void observableChanged(Axervable<T> obs, T oldValue, T newValue);
}

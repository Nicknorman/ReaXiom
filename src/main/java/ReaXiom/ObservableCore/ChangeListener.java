package ReaXiom.ObservableCore;

import java.util.Observable;

/**
 * Created by Nick on 27-05-2017.
 */
public interface ChangeListener<T> {
    public void observableChanged(Observable obs, T oldValue, T newValue);
}

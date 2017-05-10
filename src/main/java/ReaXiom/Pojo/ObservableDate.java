package ReaXiom.Pojo;

import javafx.beans.property.*;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nick on 06-05-2017.
 */
public class ObservableDate {
    private ReadOnlyLongWrapper epochMillis;
    private ReadOnlyIntegerWrapper seconds;
    private ReadOnlyIntegerWrapper minutes;
    private ReadOnlyIntegerWrapper hours;
    private Calendar calendar;

    public ObservableDate(long epochMillis) {
        this.epochMillis = new ReadOnlyLongWrapper();
        this.calendar = Calendar.getInstance();
        calendar.setTimeInMillis(epochMillis);

        seconds = new ReadOnlyIntegerWrapper(calendar.get(Calendar.SECOND));
        minutes = new ReadOnlyIntegerWrapper(calendar.get(Calendar.MINUTE));
        hours = new ReadOnlyIntegerWrapper(calendar.get(Calendar.HOUR_OF_DAY));
    }

    public ObservableDate() {
        this(0);
    }

    public Date getTime() {
        return (Date)calendar.getTime().clone();
    }

    public ReadOnlyLongProperty getEpochMillis() {
        return epochMillis.getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty getSecondProperty() {
        return seconds.getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty getMinuteProperty() {
        return minutes.getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty getHourProperty() {
        return hours.getReadOnlyProperty();
    }

    public void addSeconds(int seconds) {
        addToCalendar(Calendar.SECOND, seconds);
    }

    public void addMinutes(int minutes) {
        addToCalendar(Calendar.MINUTE, minutes);
    }

    public void addHours(int hours) {
        addToCalendar(Calendar.HOUR_OF_DAY, hours);
    }

    private void addToCalendar(int field, int value) {
        Calendar newCalendar = (Calendar)calendar.clone();
        newCalendar.add(field, value);
        updateCalendar(newCalendar);
    }

    private void updateCalendar(Calendar newCalendar) {
        if (calendar.getTimeInMillis() != newCalendar.getTimeInMillis()) {
            epochMillis.setValue(newCalendar.getTimeInMillis());
        }
        if (calendar.get(Calendar.SECOND) != newCalendar.get(Calendar.SECOND)) {
            seconds.setValue(newCalendar.get(Calendar.SECOND));
        }
        if (calendar.get(Calendar.MINUTE) != newCalendar.get(Calendar.MINUTE)) {
            minutes.setValue(newCalendar.get(Calendar.MINUTE));
        }
        if (calendar.get(Calendar.HOUR_OF_DAY) != newCalendar.get(Calendar.HOUR_OF_DAY)) {
            hours.setValue(newCalendar.get(Calendar.HOUR_OF_DAY));
        }

        calendar = newCalendar;
    }
}

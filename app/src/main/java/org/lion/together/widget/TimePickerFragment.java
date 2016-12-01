package org.lion.together.widget;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by lion on 12/1/16.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private OnTimeSetListener mOnTimeSetListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),this,hour,minute,true);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (mOnTimeSetListener != null) {
            mOnTimeSetListener.OnTimeSet(hourOfDay,minute);
        }
    }


    public void setOnTimeSetListener(OnTimeSetListener listener) {
        mOnTimeSetListener = listener;
    }

    public interface OnTimeSetListener {
        void OnTimeSet(int hourOfDay, int minute);
    }
}

package org.lion.together.widget;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    public void onDateSet(DatePicker view, int year, int month, int day) {
        if (mOnDataSetListener != null) {
            mOnDataSetListener.onDataSet(year, month, day);
        }
    }
    private onDataSetListener mOnDataSetListener;

    public void setOnDataSetListener(onDataSetListener onDataSetListener){
        mOnDataSetListener = onDataSetListener;
    }
    public interface onDataSetListener {
        void onDataSet(int year, int month, int day);
    }

}

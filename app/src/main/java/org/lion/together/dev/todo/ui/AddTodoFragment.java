package org.lion.together.dev.todo.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import org.lion.together.App;
import org.lion.together.R;
import org.lion.together.base.BaseFragment;
import org.lion.together.dao.Todo;
import org.lion.together.widget.DatePickerFragment;
import org.lion.together.widget.TimePickerFragment;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lion on 11/30/16.
 */

public class AddTodoFragment extends BaseFragment implements DatePickerFragment.onDataSetListener, TimePickerFragment.OnTimeSetListener {


    Calendar mCalendar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.abl)
    AppBarLayout mAbl;
    @BindView(R.id.et_todo_title)
    EditText mEtTodoTitle;
    @BindView(R.id.et_todo_descripiton)
    EditText mEtTodoDescripiton;
    @BindView(R.id.tv_todo_date)
    TextView mTvTodoDate;
    @BindView(R.id.tv_todo_time)
    TextView mTvTodoTime;
    @BindView(R.id.bt_add)
    Button mBtAdd;

    @Override
    protected void refreshData() {

    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_add_todo;
    }

    @Override
    public void setContentView() {
    }

    @Override
    protected void initArguments(Bundle arguments) {
        mCalendar = Calendar.getInstance();

    }


    private void showTimePicker() {
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setOnTimeSetListener(this);
        fragment.show(getActivity().getSupportFragmentManager(),"timePicker");
    }

    private void showDataPicker() {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setOnDataSetListener(this);
        fragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }


    @OnClick({R.id.tv_todo_date, R.id.tv_todo_time, R.id.bt_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_todo_date:
                showDataPicker();
                break;
            case R.id.tv_todo_time:
                showTimePicker();
                break;
            case R.id.bt_add:
                saveTodo();
                break;
        }
    }

    private void saveTodo() {
        ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
                "Loading. Please wait...", true);
        Todo todo =new Todo();
        todo.description = mEtTodoDescripiton.getText().toString();
        todo.title=mEtTodoTitle.getText().toString();
        long currentTimeMillis = System.currentTimeMillis();
        todo.createTime= currentTimeMillis;
        todo.updateTime= currentTimeMillis;
        todo.planDoTime=  mCalendar.getTimeInMillis();
        App.getAppComponent().getTodoDaoSession().getTodoDao().insertOrReplace(todo);

        mEtTodoDescripiton.setText("");
        mEtTodoTitle.setText("");
        dialog.dismiss();
    }

    @Override
    public void onDataSet(int year, int month, int day) {
        mCalendar.set(year, month, day);
        mTvTodoDate.setText(String.format("%4d-%2d-%2d",year,month,day));
        Logger.i("onDataSet called with" + "year = [" + year + "], month = [" + month + "], day = [" + day + "]");
    }

    @Override
    public void OnTimeSet(int hourOfDay, int minute) {
        Logger.i("OnTimeSet called with" + "hourOfDay = [" + hourOfDay + "], minute = [" + minute + "]");
        mCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
        mCalendar.set(Calendar.MINUTE,minute);
        mTvTodoTime.setText(String.format("%2d:%2d",hourOfDay,minute));
    }
}

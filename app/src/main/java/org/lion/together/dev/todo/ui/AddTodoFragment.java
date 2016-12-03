package org.lion.together.dev.todo.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.lion.together.R;
import org.lion.together.base.BaseFragment;
import org.lion.together.dao.Todo;
import org.lion.together.dev.todo.presenter.AddTodoPresenter;
import org.lion.together.di.componets.AppComponent;
import org.lion.together.di.componets.DaggerTodoComponent;
import org.lion.together.di.componets.TodoComponent;
import org.lion.together.di.modules.TodoModule;
import org.lion.together.utils.ToastUtil;
import org.lion.together.widget.DatePickerFragment;
import org.lion.together.widget.TimePickerFragment;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lion on 11/30/16.
 */

public class AddTodoFragment extends BaseFragment implements DatePickerFragment.onDataSetListener, TimePickerFragment.OnTimeSetListener, AddTodoView<TodoComponent> {

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

    @BindView(R.id.til_title)
    TextInputLayout mTilTitle;

    @BindView(R.id.til_description)
    TextInputLayout mTilDescription;

    @BindView(R.id.sb_importance)
    SeekBar mSbImportance;

    @Inject
    AddTodoPresenter mAddTodoPresenter;
    private TodoComponent mTodoComponent;
    private ProgressDialog mDialog;

    @Override
    protected void refreshData() {

    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_add_todo;
    }

    @Override
    public void setContentView() {
        mTvTodoDate.setText(String.format("%4d-%02d-%02d",
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)));
        mTvTodoTime.setText(String.format("%02d:%02d",
                mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE)));

    }

    @Override
    protected void initArguments(Bundle arguments) {
        mCalendar = Calendar.getInstance();
    }


    private void showTimePicker() {
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setOnTimeSetListener(this);
        fragment.show(getActivity().getSupportFragmentManager(), "timePicker");
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
                if (validate()) {
                    saveTodo();
                }
                break;
        }
    }

    private boolean validate() {
        int length = mEtTodoTitle.getText().length();
        if (length < 3 && length > 10) {
            ToastUtil.showToast(getActivity(), "title must between 3 and 10 characters");
            return false;
        }
        return true;
    }

    private void saveTodo() {
        if (null == mDialog) {
            mDialog = ProgressDialog.show(getActivity(), "",
                    "Saving...", true);
        }
        mDialog.show();
        Todo todo = getComponent().getTodo();
        todo.description = mEtTodoDescripiton.getText().toString();
        todo.title = mEtTodoTitle.getText().toString();
        long currentTimeMillis = System.currentTimeMillis();
        todo.createTime = currentTimeMillis;
        todo.updateTime = currentTimeMillis;
        todo.planDoTime = mCalendar.getTimeInMillis();
        todo.importance = mSbImportance.getProgress();

        mAddTodoPresenter.saveTodo(todo);
    }

    @Override
    public void onDataSet(int year, int month, int day) {
        mCalendar.set(year, month, day);
        mTvTodoDate.setText(String.format("%4d-%02d-%02d", year, month, day));
    }

    @Override
    public void OnTimeSet(int hourOfDay, int minute) {
        mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mCalendar.set(Calendar.MINUTE, minute);
        mTvTodoTime.setText(String.format("%02d:%02d", hourOfDay, minute));
    }

    @Override
    protected void inject(AppComponent appComponent) {
        if (null == mTodoComponent) {
            mTodoComponent = DaggerTodoComponent.builder()
                    .todoModule(new TodoModule(this))
                    .appComponent(appComponent)
                    .build();
        }
        mTodoComponent.inject(this);
        super.inject(appComponent);
    }


    @Override
    public TodoComponent getComponent() {
        return mTodoComponent;
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }

    @Override
    public void onSaveFailed() {
        ToastUtil.showToast(getActivity(), "Insert failed");
        mDialog.dismiss();
    }

    @Override
    public void onSaveSuccess(long insertId) {
        ToastUtil.showToast(getViewContext(), "Insert success");
        mEtTodoTitle.setText("");
        mEtTodoDescripiton.setText("");
        mEtTodoTitle.requestFocus();
        mDialog.dismiss();
    }
}

package org.lion.together.dev.todo.presenter;

import org.lion.together.base.BaseView;
import org.lion.together.dao.DaoSession;
import org.lion.together.dao.Todo;
import org.lion.together.dev.todo.ui.AddTodoView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lion on 12/3/16.
 */

public class AddTodoPresenterImpl implements AddTodoPresenter {
    private AddTodoView mAddTodoView;
    private DaoSession mDaoSession;

    public AddTodoPresenterImpl(AddTodoView addTodoView , DaoSession daoSession) {
        mAddTodoView = addTodoView;
        mDaoSession = daoSession;
    }

    @Override
    public void saveTodo(Todo todo) {
        Observable
                .<Long>create(subscriber -> {
                    long insertId = mDaoSession.getTodoDao().insertOrReplace(todo);
                    subscriber.onNext(insertId);
                    subscriber.onCompleted();
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(insertId -> {
                    if (0 < insertId) {
                        mAddTodoView.onSaveSuccess(insertId);
                    } else {
                        mAddTodoView.onSaveFailed();
                    }

                })
                .subscribe();
    }

    @Override
    public void attachView(BaseView view) {

    }
}

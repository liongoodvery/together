package org.lion.together.dev.todo.presenter;

import org.lion.together.base.BaseView;
import org.lion.together.dao.DaoSession;
import org.lion.together.dao.Todo;
import org.lion.together.dao.TodoDao;
import org.lion.together.dev.todo.ui.TodoView;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lion on 11/30/16.
 */
public class TodoPresenterImpl implements TodoPresenter {
    private TodoView mTodoView;
    private DaoSession mDaoSession;

    public TodoPresenterImpl(TodoView todoView,DaoSession daoSession) {
        mTodoView = todoView;
        mDaoSession = daoSession;
    }

    @Override
    public void readFromDatabase() {
        Observable
                .<List<Todo>>create(subscriber -> {
                    List<Todo> list = mDaoSession.getTodoDao().queryBuilder()
                                             .orderAsc(TodoDao.Properties.PlanDoTime)
                                             .build().list();
                    subscriber.onNext(list);
                    subscriber.onCompleted();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(mTodoView::doOnResponse)
                .subscribe();
    }

    @Override
    public void searchTodo(String s) {
        Observable
                .<List<Todo>>create(subscriber -> {
                    List<Todo> list = mDaoSession.getTodoDao().queryBuilder()
                                              .orderAsc(TodoDao.Properties.PlanDoTime)
                                              .build().list();
                    subscriber.onNext(list);
                    subscriber.onCompleted();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(mTodoView::doOnResponse)
                .subscribe();
    }

    @Override
    public void attachView(BaseView view) {
        mTodoView= (TodoView) view;
    }
}

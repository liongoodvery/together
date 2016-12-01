package org.lion.together.dev.todo.presenter;

import org.lion.together.App;
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

    public TodoPresenterImpl(TodoView todoView) {
        mTodoView = todoView;
    }

    @Override
    public void readFromDatabase() {
        Observable
                .<List<Todo>>create(subscriber -> {
                    DaoSession session = App.getAppComponent().getTodoDaoSession();
                    List<Todo> list = session.getTodoDao().queryBuilder()
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
}

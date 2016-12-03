package org.lion.together.di.modules;

import android.content.Context;

import org.greenrobot.greendao.database.Database;
import org.lion.together.C;
import org.lion.together.dao.DaoMaster;
import org.lion.together.dao.DaoSession;
import org.lion.together.dev.todo.presenter.AddTodoPresenter;
import org.lion.together.dev.todo.presenter.AddTodoPresenterImpl;
import org.lion.together.dev.todo.presenter.TodoPresenter;
import org.lion.together.dev.todo.presenter.TodoPresenterImpl;
import org.lion.together.dev.todo.ui.AddTodoView;
import org.lion.together.dev.todo.ui.TodoView;
import org.lion.together.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lion on 2016-12-03
 */
@Module
public class TodoModule {
    private TodoView mTodoView;
    private AddTodoView mAddTodoView;

    public TodoModule(TodoView todoView) {
        mTodoView = todoView;
    }

    public TodoModule(AddTodoView addTodoView) {
        mAddTodoView = addTodoView;
    }

    @ActivityScope
    @Provides
    public DaoSession provideTodoDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, C.TODO_DB);
        Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

    @Provides
    public TodoView provideTodoView(){
        return mTodoView;
    }

    @Provides
    public AddTodoView provideAddTodoView(){
        return mAddTodoView;
    }


    @Provides
    public TodoPresenter provideTodoPresenter(TodoView todoView,DaoSession daoSession){
        return new TodoPresenterImpl(todoView,daoSession);
    }

    @Provides
    public AddTodoPresenter provideAddTodoPresenter(AddTodoView addTodoView,DaoSession daoSession){
        return new AddTodoPresenterImpl(addTodoView, daoSession);
    }
}

package org.lion.together.dev.todo.presenter;

import org.lion.together.base.BasePresenter;

/**
 * Created by lion on 11/30/16.
 */

public interface TodoPresenter  extends BasePresenter{
    void readFromDatabase();
    void searchTodo(String s);
}

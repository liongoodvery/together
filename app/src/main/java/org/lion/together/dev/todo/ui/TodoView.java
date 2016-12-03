package org.lion.together.dev.todo.ui;

import org.lion.together.base.BaseView;
import org.lion.together.dao.Todo;

import java.util.Collection;

/**
 * Created by lion on 11/30/16.
 */

public interface TodoView<T> extends BaseView<T>{
    void doOnResponse(Collection<Todo> todos);

    void addTodo();
}

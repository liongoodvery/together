package org.lion.together.dev.todo.ui;

import org.lion.together.dao.Todo;

import java.util.Collection;

/**
 * Created by lion on 11/30/16.
 */

public interface TodoView {
    void doOnResponse(Collection<Todo> todos);

    void addTodo();
}

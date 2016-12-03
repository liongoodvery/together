package org.lion.together.dev.todo.presenter;

import org.lion.together.base.BasePresenter;
import org.lion.together.dao.Todo;

/**
 * Created by lion on 12/3/16.
 */

public interface AddTodoPresenter extends BasePresenter {
   void saveTodo(Todo todo);
}

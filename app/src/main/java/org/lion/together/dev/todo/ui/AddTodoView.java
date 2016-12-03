package org.lion.together.dev.todo.ui;

import org.lion.together.base.BaseView;

/**
 * Created by lion on 12/3/16.
 */

public interface AddTodoView<T> extends BaseView<T> {
    void onSaveFailed();
    void onSaveSuccess(long insertId);
}

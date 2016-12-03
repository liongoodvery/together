package org.lion.together.di.componets;

import org.lion.together.dao.Todo;
import org.lion.together.dev.todo.ui.AddTodoFragment;
import org.lion.together.dev.todo.ui.TodoFragment;
import org.lion.together.di.ActivityScope;
import org.lion.together.di.modules.TodoModule;

import dagger.Component;

/**
 * Created by lion on 2016-12-03
 */
@ActivityScope
@Component(modules = TodoModule.class,dependencies = {AppComponent.class})
public interface TodoComponent {
    void inject(TodoFragment fragment);
    void inject(AddTodoFragment fragment);
    Todo getTodo();
}

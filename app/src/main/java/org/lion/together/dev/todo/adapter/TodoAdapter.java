package org.lion.together.dev.todo.adapter;

import android.view.ViewGroup;

import org.lion.together.base.BaseRecyclerAdapter;
import org.lion.together.dev.todo.model.Todo;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Created by lion on 2016-11-27
 */

public class TodoAdapter extends BaseRecyclerAdapter<TodoHolder, Todo> {
    public TodoAdapter(Collection<Todo> datas) {
        super(datas);
    }

    @Override
    public TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TodoHolder(parent);
    }

    @Override
    public void onBindViewHolder(TodoHolder holder, int position) {
        Todo todo = mDatas.get(position);
        holder.mTvTodoTitle.setText(todo.title);
        Date date = new Date(todo.planDoTime);
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        holder.mTvTodoTime.setText(format.format(date));
        holder.mTvTodoDescripiton.setText(todo.description);
        holder.mRbTodoRating.setRating(todo.importance);
    }
}

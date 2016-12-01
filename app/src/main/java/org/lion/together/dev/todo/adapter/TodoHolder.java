package org.lion.together.dev.todo.adapter;

import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import org.lion.together.R;
import org.lion.together.base.BaseRecyclerVH;

import butterknife.BindView;

/**
 * Created by lion on 2016-11-27
 */

public class TodoHolder extends BaseRecyclerVH {
    @BindView(R.id.tv_todo_title)
    TextView mTvTodoTitle;
    @BindView(R.id.tv_todo_time)
    TextView mTvTodoTime;
    @BindView(R.id.rb_todo_rating)
    RatingBar mRbTodoRating;
    @BindView(R.id.et_todo_descripiton)
    TextView mTvTodoDescripiton;

    public TodoHolder(ViewGroup parent) {
        super(parent, R.layout.item_todo);
    }
}

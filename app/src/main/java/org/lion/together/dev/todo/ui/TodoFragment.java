package org.lion.together.dev.todo.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.lion.together.R;
import org.lion.together.base.BaseFragment;
import org.lion.together.dev.todo.adapter.TodoAdapter;
import org.lion.together.dev.todo.model.Todo;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by lion on 2016-11-27
 */

public class TodoFragment extends BaseFragment {
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.abl)
    AppBarLayout mAbl;
    @BindView(R.id.rv_todo)
    RecyclerView mRvTodo;
    @BindView(R.id.srl_todo)
    SwipeRefreshLayout mSrlTodo;


    @Override
    protected void refreshData() {

    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_todo;
    }

    @Override
    public void setContentView() {

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRvTodo.setLayoutManager(manager);
        ArrayList<Todo> datas = new ArrayList<>();
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Todo todo = new Todo("title" + i, l + i * 1000000, l + i * 100000, l + i * 200000, i % 5, "description" + i);
            datas.add(todo);
        }
        mRvTodo.setAdapter(new TodoAdapter(datas));
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }
}

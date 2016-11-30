package org.lion.together.dev.todo.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.lion.together.R;
import org.lion.together.base.BaseFragment;
import org.lion.together.dao.Todo;
import org.lion.together.dev.todo.adapter.TodoAdapter;
import org.lion.together.dev.todo.presenter.TodoPresenter;
import org.lion.together.dev.todo.presenter.TodoPresenterImpl;

import java.util.Collection;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lion on 2016-11-27
 */

public class TodoFragment extends BaseFragment implements TodoView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.abl)
    AppBarLayout mAbl;
    @BindView(R.id.rv_todo)
    RecyclerView mRvTodo;
    @BindView(R.id.srl_todo)
    SwipeRefreshLayout mSrlTodo;
    @BindView(R.id.fab_todo)
    FloatingActionButton mFabTodo;
    private TodoAdapter mAdapter;

    TodoPresenter mTodoPresenter;

    @Override
    protected void refreshData() {
        mSrlTodo.post(() -> mSrlTodo.setRefreshing(true));
        mTodoPresenter.readFromDatabase();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_todo;
    }

    @Override
    public void setContentView() {
        mTodoPresenter = new TodoPresenterImpl(this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRvTodo.setLayoutManager(manager);
        mAdapter = new TodoAdapter(null);
        mRvTodo.setAdapter(mAdapter);
        mSrlTodo.setOnRefreshListener(this);
        mRootView.findViewById(R.id.toolbar_title).setOnClickListener(v -> addTodo());
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    public void doOnResponse(Collection<Todo> todos) {
        mAdapter.update(todos);
        mSrlTodo.setRefreshing(false);
    }

    @Override
    public void addTodo() {
        AddTodoFragment addTodoFragment = new AddTodoFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.containner,addTodoFragment)
                .commit();
//        FragmentUtils.addToBackStack(getActivity(),addTodoFragment);
    }

    @Override
    public void onRefresh() {
        refreshData();
    }

    @OnClick({R.id.fab_todo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_todo:
                addTodo();
                break;
        }
    }
}

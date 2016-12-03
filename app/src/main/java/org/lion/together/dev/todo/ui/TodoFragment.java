package org.lion.together.dev.todo.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;

import org.lion.together.R;
import org.lion.together.base.BaseFragment;
import org.lion.together.dao.Todo;
import org.lion.together.dev.todo.adapter.TodoAdapter;
import org.lion.together.dev.todo.presenter.TodoPresenter;
import org.lion.together.di.componets.AppComponent;
import org.lion.together.di.componets.DaggerTodoComponent;
import org.lion.together.di.componets.TodoComponent;
import org.lion.together.di.modules.TodoModule;
import org.lion.together.utils.FragmentUtils;
import org.lion.together.utils.ToastUtil;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lion on 2016-11-27
 */

public class TodoFragment extends BaseFragment implements TodoView<TodoComponent>, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.abl)
    AppBarLayout mAbl;
    @BindView(R.id.rv_todo)
    RecyclerView mRvTodo;
    @BindView(R.id.srl_todo)
    SwipeRefreshLayout mSrlTodo;
    @BindView(R.id.fab_todo)
    FloatingActionButton mFabTodo;
    private TodoAdapter mAdapter;

    @Inject
    TodoPresenter mTodoPresenter;
    private TodoComponent mTodoComponent;

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
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRvTodo.setLayoutManager(manager);
        mAdapter = new TodoAdapter(null);
        mRvTodo.setAdapter(mAdapter);
        mSrlTodo.setOnRefreshListener(this);
        mToolbar.setTitle("TODO");
        mToolbar.setTitleTextColor(Color.RED);
        initSearchView();
    }


    private void initSearchView() {
        final SearchView searchView = (SearchView) mToolbar.getMenu()
                                                           .findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("搜索Todo");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ToastUtil.showToast(getActivity(), "query=" + query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });
    }

    @Override
    protected int getMenuRes() {
        return R.menu.todo_menu;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                break;
        }
        return super.onMenuItemClick(item);
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    public void doOnResponse(Collection<Todo> todos) {
        mAdapter.update(todos);
        mSrlTodo.post(() -> mSrlTodo.setRefreshing(false));
    }

    @Override
    public void addTodo() {
        AddTodoFragment addTodoFragment = new AddTodoFragment();
        FragmentUtils.addToBackStack(getActivity(), addTodoFragment);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void inject(AppComponent appComponent) {
        if(null == mTodoComponent){
            mTodoComponent = DaggerTodoComponent.builder()
                    .todoModule(new TodoModule(this))
                    .appComponent(appComponent)
                    .build();
        }

        mTodoComponent.inject(this);
        super.inject(appComponent);
    }

    @Override
    public TodoComponent getComponent() {
        return mTodoComponent;
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }
}

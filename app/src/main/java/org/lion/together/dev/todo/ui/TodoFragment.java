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
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        Observable.range(1, 40)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(j -> {
                    Todo todo = new Todo("title" + j, l + j * 1000000, l + j * 100000, l + j * 200000, j % 5, "descriptjon" + j);
                    return todo;
                })
                .toList()
                .doOnNext(todos -> mRvTodo.setAdapter(new TodoAdapter(todos)))
                .subscribe();
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }
}

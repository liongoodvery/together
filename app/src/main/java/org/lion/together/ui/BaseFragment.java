package org.lion.together.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lion.together.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    protected View mRootView;  //该Fragment的根View
    protected boolean mShowed; //该Fragment是否显示过

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        //处理传给该Fargment的初始化数据
        initArguments(getArguments());
        initCustomData(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //如果RootView已经存在,则不再创建
        if (null == mRootView) {
            mRootView = inflater.inflate(getContentLayout(), container, false);
            ButterKnife.bind(this, mRootView);
            inject();
            if (mToolbar!=null){
                setToolBar();
                setHasOptionsMenu(true);
                mToolbar.inflateMenu(R.menu.main2);
            }
            setContentView();//setContentView中只能进行静态数据与事件的初始化 获取网络数据应在refreshData中进行
        }

        return mRootView;
    }

    protected void inject() {

    }

    @Override
    public void onStart() {
        super.onStart();
        //当该Fragment重新显示时是否重新获取数据
        if (!mShowed || shouldRefrsh()) {
            refreshData();
            mShowed = true;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            restoreState(savedInstanceState);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveSate(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * 控制Fragment重新显示时是否重新刷新数据 默认为true
     *
     * @return 返回true 则刷新数据
     */
    protected boolean shouldRefrsh() {
        return true;
    }

    /**
     * 刷新数据
     */
    protected abstract void refreshData();

    /**
     * 该Fragment的布局文件
     *
     * @return 布局Id
     */
    @LayoutRes
    public abstract int getContentLayout();

    /**
     * 执行View的初始化操作,比如寻找控件
     */
    public abstract void setContentView();

    /**
     * 处理传给此Fragment的初始化数据
     *
     * @param arguments 包含初始化数据的Bundle
     */
    protected abstract void initArguments(Bundle arguments);

    public void restoreState(Bundle savedInstanceState) {

    }

    public void saveSate(Bundle outState) {

    }

    public boolean isVisibleToolbar() {
        return false;
    }

    protected void initCustomData(Bundle savedInstanceState) {

    }

    protected void setToolBar() {
        ((Drawer) getActivity()).initDrawer(mToolbar);
    }
}
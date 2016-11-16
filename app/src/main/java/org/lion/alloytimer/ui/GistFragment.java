package org.lion.alloytimer.ui;

import android.os.Bundle;

import retrofit2.Retrofit;

/**
 * Created by lion on 2016-11-16
 */

public class GistFragment extends BaseFragment {
    @Override
    protected void setToolBar() {
        Retrofit build = new Retrofit.Builder().build();
    }

    @Override
    protected void refreshData() {

    }

    @Override
    public int getContentLayout() {
        return 0;
    }

    @Override
    public void setContentView() {

    }

    @Override
    protected void initArguments(Bundle arguments) {

    }
}

package org.lion.together.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Weizhengbin on 2016/3/28.
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleArguments();
        initCustomData(savedInstanceState);
        setContentView(getContentLayout());
        ButterKnife.bind(this);
        setupToolbar();
        setupView();
        fetchData();
    }

    protected abstract void fetchData();


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        newConfig.fontScale = 1.0f;
        super.onConfigurationChanged(newConfig);
    }


    protected abstract int getContentLayout();

    protected abstract void setupView();

    protected abstract void setupToolbar();

    protected abstract void handleArguments();

    protected void initCustomData(Bundle savedInstanceState) {
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

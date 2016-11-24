package org.lion.together.ui;

import android.content.Intent;

import org.lion.together.R;
import org.lion.together.config.C;
import org.lion.together.model.Gist;
import org.lion.together.model.Owner;

public class DetailGistActivity extends BaseActivity {

    private Gist mGist;
    private Owner mOwner;
    private String mInGistId;

    @Override
    protected void fetchData() {

    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_detail_gist;
    }

    @Override
    protected void setupView() {

    }

    @Override
    protected void setupToolbar() {

    }

    @Override
    protected void handleArguments() {
        Intent intent = getIntent();
        mInGistId = intent.getStringExtra(C.GIST_ID);
    }
}

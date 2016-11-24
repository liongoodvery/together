package org.lion.together.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.blankj.utilcode.utils.SPUtils;
import com.blankj.utilcode.utils.ToastUtils;

import org.lion.together.R;
import org.lion.together.adapter.GistAdapter;
import org.lion.together.adapter.RecyclerItemClickListener;
import org.lion.together.config.C;
import org.lion.together.di.componets.DaggerGistComponent;
import org.lion.together.di.modules.GistModule;
import org.lion.together.model.Gist;
import org.lion.together.presenter.GistPresenter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by lion on 2016-11-16
 */

public class GistFragment extends BaseFragment implements GistView, View.OnClickListener, RecyclerItemClickListener.OnItemClickListener {
    @BindView(R.id.rv_gist)
    RecyclerView mRvGist;
    @BindView(R.id.srl_gist)
    SwipeRefreshLayout mSrlGist;

    @Inject
    GistPresenter mGistPresenter;
    @Inject
    SPUtils mSPUtils;
    @Inject
    Dialog mDialog;

    @BindView(R.id.abl_gist)
    AppBarLayout mAblGist;
    private GistAdapter mGistAdapter;
    private EditText mEt_dialog_token;
    private String mToken;

    @Override
    protected int getMenuRes() {
        return R.menu.gist_menu;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_all_gist:
                refresh(null);
                break;
            case R.id.action_show_me:
                if (TextUtils.isEmpty(mToken)) {
                    showTokenDialog();
                } else {
                    refresh(mToken);
                }
                break;
        }
        return super.onMenuItemClick(item);
    }


    private void refresh(String token) {
        mSrlGist.post(() -> mSrlGist.setRefreshing(true));
        mGistPresenter.fetchGists(token);
    }


    @Override
    protected void refreshData() {
        refresh(mToken);
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_gist;
    }

    @Override
    public void setContentView() {
        String token = mSPUtils.getString(C.SP_GITHUB_TOKEN);
        boolean not_show_again = mSPUtils.getBoolean(C.NOT_SHOW_AGAIN, false);
        if (!not_show_again && (null == token)) {
            showTokenDialog();
        } else {
            mToken = token;
        }
        mSrlGist.setOnRefreshListener(this::refreshData);
        mRvGist.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mRvGist, this));
    }

    private void showTokenDialog() {
        mEt_dialog_token = (EditText) mDialog.findViewById(R.id.et_dialog_token);
        mDialog.findViewById(R.id.bt_gist_confirm).setOnClickListener(this);
        mDialog.findViewById(R.id.bt_gist_cancel).setOnClickListener(this);
        CheckBox checkBox = (CheckBox) mDialog.findViewById(R.id.cb_not_show_again);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mSPUtils.putBoolean(C.NOT_SHOW_AGAIN, isChecked);
        });
        mDialog.show();
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initCustomData(Bundle savedInstanceState) {
    }

    @Override
    protected void inject() {
        super.inject();
        DaggerGistComponent.builder()
                .gistModule(new GistModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onFetchSuccess(List<Gist> gists) {
        if (mSrlGist.isRefreshing()) {
            mSrlGist.setRefreshing(false);
        }
        if (null == mGistAdapter) {
            mGistAdapter = new GistAdapter(gists);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
            mRvGist.setLayoutManager(manager);
            mRvGist.setAdapter(mGistAdapter);
        } else {
            mGistAdapter.update(gists);
        }
    }

    @Override
    public void onFetchFailed() {
        if (mSrlGist.isRefreshing()) {
            mSrlGist.setRefreshing(false);
        }
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_gist_confirm:
                Editable text = mEt_dialog_token.getText();
                if (!TextUtils.isEmpty(text)) {
                    Pattern pattern = Pattern.compile(C.GIST_TOKEN_REGEX);
                    Matcher matcher = pattern.matcher(text);
                    if (matcher.matches()) {
                        mGistPresenter.verifyToken(text.toString());
                    } else {
                        ToastUtils.showShortToast(getActivity(), "Wrog Token");
                    }
                }
                break;
            case R.id.bt_gist_cancel:
                mDialog.dismiss();
                break;
        }
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onVerifyTokenResponce(boolean success, String token) {
        if (success) {
            mToken = token;
            mSPUtils.putString(C.SP_GITHUB_TOKEN, mToken);
            mDialog.dismiss();
            refreshData();
        } else {
            ToastUtils.showShortToast(getActivity(), "Your Token can not be verified");
            mSPUtils.putString(C.SP_GITHUB_TOKEN,null);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        if (mGistAdapter == null) {
            return;
        }
        Gist gist = mGistAdapter.getDataAt(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable(C.GIST_SINGLE_GIST, gist);
        GistDetailFragment fragment = new GistDetailFragment();
        fragment.setArguments(bundle);
        ((MainActivity) getActivity()).addToBackStack(fragment);
    }
}

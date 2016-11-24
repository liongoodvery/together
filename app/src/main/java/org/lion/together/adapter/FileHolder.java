package org.lion.together.adapter;

import android.content.Context;
import android.widget.TextView;

import com.pddstudio.highlightjs.HighlightJsView;

import org.lion.together.R;

import butterknife.BindView;

/**
 * Created by lion on 11/24/16.
 */

public class FileHolder extends BaseRecyclerVH {
    @BindView(R.id.tv_file_name)
    public TextView mTvFileName;
    @BindView(R.id.hj_code)
    public HighlightJsView mHjCode;

    public FileHolder(Context context) {
        super(context, R.layout.item_file);
    }
}

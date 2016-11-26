package org.lion.together.dev.gist.adapter;

import android.view.ViewGroup;

import com.pddstudio.highlightjs.models.Language;
import com.pddstudio.highlightjs.models.Theme;

import org.lion.together.base.BaseRecyclerAdapter;
import org.lion.together.dev.gist.model.GistFile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

/**
 * Created by lion on 11/24/16.
 */

public class FileAdapter extends BaseRecyclerAdapter<FileHolder, GistFile> {
    public FileAdapter(Collection<GistFile> datas) {
        super(datas);
    }

    @Override
    public FileHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FileHolder(parent.getContext());
    }

    @Override
    public void onBindViewHolder(FileHolder holder, int position) {
        holder.mTvFileName.setText(mDatas.get(position).filename);
        //find and instantiate the view

        //optional: register callbacks and style the view

        //register theme change listener
//        holder.mHjCode.setOnThemeChangedListener(this);
        //change theme and set language to auto detect
        holder.mHjCode.setTheme(Theme.ATELIER_CAVE_LIGHT);
        holder.mHjCode.setHighlightLanguage(Language.AUTO_DETECT);
        //load the source (can be loaded by String, File or URL)
        try {
            holder.mHjCode.setSource(new URL(mDatas.get(position).raw_url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

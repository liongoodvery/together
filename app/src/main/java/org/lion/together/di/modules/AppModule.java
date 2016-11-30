package org.lion.together.di.modules;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;

import org.greenrobot.greendao.database.Database;
import org.lion.together.C;
import org.lion.together.dao.DaoMaster;
import org.lion.together.dao.DaoSession;
import org.lion.together.dev.gist.GistC;
import org.lion.together.http.GistApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lion on 11/11/16.
 */
@Module
public class AppModule {
    Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Singleton
    @Provides
    public Handler provideHandler() {
        return new Handler(Looper.getMainLooper());
    }


    @Singleton
    @Provides
    public Vibrator provideVibrator() {
        return (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Singleton
    @Provides
    public GistApi provideGistApi() {
        return new Retrofit.Builder()
                .baseUrl(GistC.BASE_GIST_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(GistApi.class);
    }


    @Singleton
    @Provides
    public DaoSession provideTodoDaoSession() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, C.TODO_DB);
        Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }
}

package org.lion.together;

import org.junit.Test;
import org.lion.together.config.C;
import org.lion.together.http.GistApi;
import org.lion.together.model.Gist;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_getAllGists() throws Exception {
        new Retrofit.Builder()
                .baseUrl(C.BASE_GIST_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(GistApi.class)
                .getAllGists()
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<List<Gist>>() {
                    @Override
                    public void call(List<Gist> gists) {
                        System.out.println(gists);
                    }
                });

        System.in.read();
    }

    @Test
    public void test50() throws Exception {
        System.out.println("8a0090cbc46bc4e4cff06f6e8ac70fea7e773756".length());
    }
}
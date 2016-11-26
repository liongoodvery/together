package org.lion.together.http;


import org.lion.together.dev.gist.model.Gist;
import org.lion.together.dev.gist.model.TokenVerifyResponce;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lion on 2016-11-16
 */

public interface GistApi {
    @GET("gists")
    Observable<List<Gist>> getAllGists();

    @GET("gists")
    Observable<List<Gist>> getGistsByToken(@Query("access_token") String access_token);

    @GET("user")
    Observable<TokenVerifyResponce> verifyToken(@Query("access_token") String access_token);

    @GET("gists/{id}")
    Observable<List<Gist>> getGistById(@Path("id") String id);

}

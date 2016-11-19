package org.lion.together.http;


import org.lion.together.model.Gist;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by lion on 2016-11-16
 */

public interface GistApi{
    @GET("gists")
    Observable<List<Gist>> getAllGists();
}

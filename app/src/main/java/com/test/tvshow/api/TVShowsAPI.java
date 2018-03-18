package com.test.tvshow.api;

import com.test.tvshow.model.TotalShow;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by roberto on 14/03/18.
 */

public interface TVShowsAPI {

    @GET("/3/tv/popular")
    Observable<Response<TotalShow>> getPopularTVShows(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page);

    @GET("3/tv/{idShow}/similar")
    Observable<Response<TotalShow>> getSimilarShows(
            @Path("idShow") int idShow,
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page);
}

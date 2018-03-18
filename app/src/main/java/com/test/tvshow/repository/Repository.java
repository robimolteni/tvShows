package com.test.tvshow.repository;

import com.test.tvshow.model.TotalShow;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by roberto on 14/03/18.
 */

public interface Repository {

    Observable<Response<TotalShow>> getPopularTVShows(int page);
    Observable<Response<TotalShow>> getSimilarShows(int idShow, int page);

}

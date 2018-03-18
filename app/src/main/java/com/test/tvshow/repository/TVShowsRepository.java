package com.test.tvshow.repository;

import com.test.tvshow.Utils;
import com.test.tvshow.api.TVShowsAPI;
import com.test.tvshow.model.TotalShow;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by roberto on 14/03/18.
 */

public class TVShowsRepository implements Repository {

    private TVShowsAPI tvShowsAPI;

    public TVShowsRepository(TVShowsAPI tvShowsAPI) {
        this.tvShowsAPI = tvShowsAPI;
    }

    @Override
    public Observable<Response<TotalShow>> getPopularTVShows(int page) {
        return tvShowsAPI.getPopularTVShows(Utils.API_KEY,Utils.language,page);
    }

    @Override
    public Observable<Response<TotalShow>> getSimilarShows(int idShow, int page) {
        return tvShowsAPI.getSimilarShows(idShow, Utils.API_KEY,Utils.language,page);
    }

}

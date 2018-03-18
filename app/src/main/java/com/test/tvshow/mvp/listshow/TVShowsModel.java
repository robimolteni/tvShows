package com.test.tvshow.mvp.listshow;

import com.test.tvshow.repository.Repository;
import com.test.tvshow.model.TotalShow;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by roberto on 14/03/18.
 */

public class TVShowsModel implements TVShowMVPInteface.Model {

    private Repository repository;

    public TVShowsModel(Repository repository){
        this.repository = repository;
    }

    @Override
    public Observable<Response<TotalShow>> getPopularTVShows(int page) {
        return repository.getPopularTVShows(page);
    }
}

package com.test.tvshow.mvp.detailshow;

import com.test.tvshow.repository.Repository;
import com.test.tvshow.model.TotalShow;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by roberto on 16/03/18.
 */

public class DetailShowsModel implements DetailShowsMVPInterface.Model {

    private Repository repository;

    public DetailShowsModel(Repository repository){
        this.repository = repository;
    }


    @Override
    public Observable<Response<TotalShow>> getSimilarTVShows(int id, int page) {
        return repository.getSimilarShows(id,page);
    }
}

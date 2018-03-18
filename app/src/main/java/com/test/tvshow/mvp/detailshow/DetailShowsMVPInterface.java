package com.test.tvshow.mvp.detailshow;

import com.test.tvshow.model.TotalShow;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by roberto on 16/03/18.
 */

public interface DetailShowsMVPInterface {

    interface View {
        void showSimilarTVShows(TotalShow stargazer);
        void showError();
    }

    interface Presenter {
        //getList
        void getSimilarTVShows(int id, int page);
        void rxUnsubscribe();
        void setView(DetailShowsMVPInterface.View view);
    }

    interface Model {
        Observable<Response<TotalShow>> getSimilarTVShows(int id, int page);
    }
}

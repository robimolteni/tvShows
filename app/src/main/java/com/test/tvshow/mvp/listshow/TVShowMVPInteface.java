package com.test.tvshow.mvp.listshow;

import com.test.tvshow.model.TotalShow;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by roberto on 14/03/18.
 */

public interface TVShowMVPInteface {

    interface View {
        void showTVShows(TotalShow stargazer);
        void showError();
    }

    interface Presenter {
        //getList
        void getPopularTVShows(int page);
        void rxUnsubscribe();
        void setView(TVShowMVPInteface.View view);
    }

    interface Model {
        Observable<Response<TotalShow>> getPopularTVShows(int page);
    }
}

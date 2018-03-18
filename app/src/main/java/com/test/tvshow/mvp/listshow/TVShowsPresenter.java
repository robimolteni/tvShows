package com.test.tvshow.mvp.listshow;

import com.test.tvshow.model.TotalShow;

import retrofit2.Response;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by roberto on 14/03/18.
 */

public class TVShowsPresenter implements TVShowMVPInteface.Presenter {

    TVShowMVPInteface.View mView;
    TVShowMVPInteface.Model mModel;
    private Subscription subscription = null;

    public TVShowsPresenter(TVShowMVPInteface.Model model) {
        this.mModel = model;
    }

    @Override
    public void getPopularTVShows(int page) {
        subscription = mModel.getPopularTVShows(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<TotalShow>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.showError();
            }

            @Override
            public void onNext(Response<TotalShow> ls) {
                if (mView != null) {
                    if(ls != null) {
                        mView.showTVShows(ls.body());
                    } else {
                        mView.showError();
                    }
                }
            }
        });
    }

    @Override
    public void rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    @Override
    public void setView(TVShowMVPInteface.View view) {
        this.mView = view;
    }
}

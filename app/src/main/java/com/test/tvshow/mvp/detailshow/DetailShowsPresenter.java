package com.test.tvshow.mvp.detailshow;

import com.test.tvshow.model.TotalShow;

import retrofit2.Response;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by roberto on 16/03/18.
 */

public class DetailShowsPresenter implements DetailShowsMVPInterface.Presenter {

    DetailShowsMVPInterface.View mView;
    DetailShowsMVPInterface.Model mModel;
    private Subscription subscription = null;

    public DetailShowsPresenter(DetailShowsMVPInterface.Model model) {
        this.mModel = model;
    }
    
    @Override
    public void getSimilarTVShows(int id, int page) {
        subscription = mModel.getSimilarTVShows(id, page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<TotalShow>>() {
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
                        mView.showSimilarTVShows(ls.body());
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
    public void setView(DetailShowsMVPInterface.View view) {
        this.mView = view;
    }
}

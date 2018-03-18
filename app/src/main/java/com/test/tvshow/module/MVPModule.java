package com.test.tvshow.module;

import com.test.tvshow.repository.Repository;
import com.test.tvshow.repository.TVShowsRepository;
import com.test.tvshow.api.TVShowsAPI;
import com.test.tvshow.mvp.listshow.TVShowMVPInteface;
import com.test.tvshow.mvp.listshow.TVShowsModel;
import com.test.tvshow.mvp.listshow.TVShowsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by roberto on 14/03/18.
 */

@Module(includes = {NetModule.class})
public class MVPModule {

    @Provides
    public TVShowMVPInteface.Presenter provideActivityPresenter(TVShowMVPInteface.Model topMoviesModel) {
        return new TVShowsPresenter(topMoviesModel);
    }

    @Provides
    public TVShowMVPInteface.Model provideActivityModel(Repository repository) {
        return new TVShowsModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(TVShowsAPI stargazerApiService) {
        return new TVShowsRepository(stargazerApiService);
    }
}
package com.test.tvshow.module;

import com.test.tvshow.repository.Repository;
import com.test.tvshow.mvp.detailshow.DetailShowsMVPInterface;
import com.test.tvshow.mvp.detailshow.DetailShowsModel;
import com.test.tvshow.mvp.detailshow.DetailShowsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by roberto on 16/03/18.
 */
@Module(includes = {NetModule.class})
public class DetailMVPModule {

    @Provides
    public DetailShowsMVPInterface.Presenter provideActivityPresenter(DetailShowsMVPInterface.Model topMoviesModel) {
        return new DetailShowsPresenter(topMoviesModel);
    }

    @Provides
    public DetailShowsMVPInterface.Model provideActivityModel(Repository repository) {
        return new DetailShowsModel(repository);
    }

   /* @Singleton
    @Provides
    public Repository provideRepo(TVShowsAPI stargazerApiService) {
        return new TVShowsRepository(stargazerApiService);
    }*/
}

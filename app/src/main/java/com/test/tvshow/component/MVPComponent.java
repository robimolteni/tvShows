package com.test.tvshow.component;

import com.test.tvshow.module.DetailMVPModule;
import com.test.tvshow.mvp.detailshow.DetailActivity;
import com.test.tvshow.mvp.listshow.MainActivity;
import com.test.tvshow.module.AppModule;
import com.test.tvshow.module.MVPModule;
import com.test.tvshow.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by roberto on 14/03/18.
 */
@Singleton
@Component(modules = {MVPModule.class, AppModule.class, NetModule.class, DetailMVPModule.class})
public interface MVPComponent {
    void inject(MainActivity activity);
    void injectDetail(DetailActivity activity);
}
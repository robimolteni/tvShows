package com.test.tvshow;

import android.app.Application;

import com.test.tvshow.component.DaggerMVPComponent;
import com.test.tvshow.component.MVPComponent;
import com.test.tvshow.module.AppModule;
import com.test.tvshow.module.MVPModule;
import com.test.tvshow.module.NetModule;

/**
 * Created by roberto on 14/03/18.
 */

public class App extends Application {

    private String URL = Utils.BASE_URL;
    private MVPComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerMVPComponent.builder()
                .appModule(new AppModule(this))
                .mVPModule(new MVPModule())
                .netModule(new NetModule((URL)))
                .build();
    }

    public MVPComponent getMVPComponent() {
        return component;
    }
}

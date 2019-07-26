package com.Config;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ma_config extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                .name("Article.db")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}

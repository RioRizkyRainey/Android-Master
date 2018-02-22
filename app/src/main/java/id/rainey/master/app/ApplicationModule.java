package id.rainey.master.app;

import android.app.*;
import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by riorizkyrainey on 22/06/16.
 */
@Module
public final class ApplicationModule {

    Application mApplication;
    private final Realm mRealm;

    ApplicationModule(Application application, Realm mRealm) {
        this.mApplication = application;
        this.mRealm = mRealm;
    }

    @Provides
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Realm provideRealm() {
        return mRealm;
    }

    @Provides
    @Singleton
    android.app.Application provideApplication() {
        return mApplication;
    }
}

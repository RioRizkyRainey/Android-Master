package id.rainey.master.app;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import id.rainey.master.R;
import id.rainey.master.session.ObscuredModule;
import id.rainey.master.utils.network.NetModule;
import io.realm.Realm;
import okhttp3.Interceptor;

public abstract class Application extends android.app.Application {

    private static Context ctx;

    private AppComponent mAppComponent;

    public static Context getContext() {
        return ctx;
    }

    protected String BASE_API = "";

    @Override
    public void onCreate() {
        super.onCreate();

        ctx = getApplicationContext();

        Realm realm = Realm.getDefaultInstance();

        mAppComponent = DaggerAppComponent.builder()
                .obscuredModule(new ObscuredModule(getApplicationContext()))
                .netModule(new NetModule(BASE_API, getInterceptors()))
                .applicationModule(new ApplicationModule(this, realm))
                .build();
    }

    public List<Interceptor> getInterceptors() {
        return new ArrayList<>();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public abstract String getAppName();

}
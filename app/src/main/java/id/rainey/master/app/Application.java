package id.rainey.master.app;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;

import id.rainey.master.session.ObscuredModule;
import id.rainey.master.utils.network.ServerRestClientModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Application extends android.app.Application {

    private static Context ctx;

    private AppComponent mAppComponent;

    public static Context getContext() {
        return ctx;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // TODO uncomment on release
        //Fabric.with(this, new Crashlytics());
        ctx = getApplicationContext();

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(ctx).build();
        Realm.setDefaultConfiguration(realmConfig);

        Realm realm = Realm.getDefaultInstance();

        mAppComponent = DaggerAppComponent.builder()
                .obscuredModule(new ObscuredModule(getApplicationContext()))
                .serverRestClientModule(new ServerRestClientModule(new AsyncHttpClient()))
                .applicationModule(new ApplicationModule(getApplicationContext(), realm))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
package id.rainey.master.app;

import javax.inject.Singleton;

import dagger.Component;
import id.rainey.master.session.ObscuredModule;
import id.rainey.master.session.ObscuredSharedPreferences;
import id.rainey.master.utils.network.NetModule;
import retrofit2.Retrofit;

/**
 * Created by riorizkyrainey on 24/06/16.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ObscuredModule.class, NetModule.class})
public interface AppComponent {
    ObscuredSharedPreferences getObscuredSharedPreferences();

    android.app.Application getApplication();

    Retrofit getRetrofit();

}

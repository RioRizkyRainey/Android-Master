package id.rainey.master.app;


import dagger.Component;
import id.rainey.master.session.ObscuredModule;
import id.rainey.master.session.ObscuredSharedPreferences;
import id.rainey.master.utils.network.ServerRestClient;
import id.rainey.master.utils.network.ServerRestClientModule;

/**
 * Created by riorizkyrainey on 24/06/16.
 */
@AppScope
@Component(modules = {ApplicationModule.class, ObscuredModule.class, ServerRestClientModule.class})
public interface AppComponent {
    ObscuredSharedPreferences getObscuredSharedPreferences();

    ServerRestClient getServerRestClient();

}

package id.rainey.master.utils.network;

import com.loopj.android.http.AsyncHttpClient;

import dagger.Module;
import dagger.Provides;

/**
 * Created by riorizkyrainey on 23/06/16.
 */
@Module
public class ServerRestClientModule {

    private final AsyncHttpClient httpClient;

    public ServerRestClientModule(AsyncHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Provides
    AsyncHttpClient provideAsyncHttpClient() {
        return httpClient;
    }
}

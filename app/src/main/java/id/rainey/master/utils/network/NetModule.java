package id.rainey.master.utils.network;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.rainey.master.app.AppScope;
import id.rainey.master.utils.interceptor.ConnectivityInterceptor;
import id.rainey.master.utils.interceptor.LoggingInterceptor;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by riorizkyrainey on 13/10/17.
 */

@AppScope
@Module
public class NetModule {

    private String mBaseUrl;

    private List<Interceptor> interceptors;

    public NetModule(String mBaseUrl, List<Interceptor> interceptors) {
        this.mBaseUrl = mBaseUrl;
        this.interceptors = interceptors;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        for (Interceptor interceptor : interceptors) {
            client.addInterceptor(interceptor);
        }

        client.addInterceptor(new ConnectivityInterceptor());
        client.addInterceptor(new LoggingInterceptor());

        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
    }
}

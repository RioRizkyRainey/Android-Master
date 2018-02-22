package id.rainey.master.utils.interceptor;

import java.io.IOException;

import id.rainey.master.app.Application;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by riorizkyrainey on 13/10/17.
 */

public class HeaderInterceptor implements Interceptor {

    String appName;

    public HeaderInterceptor(String appName) {
        this.appName = appName;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
                .header("User-Agent", appName)
                .header("Accept", "application/json")
                .method(original.method(), original.body())
                .build();

        return chain.proceed(request);
    }
}

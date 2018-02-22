package id.rainey.master.utils.interceptor;

import android.content.Context;

import java.io.IOException;

import id.rainey.master.utils.NetworkUtil;
import id.rainey.master.utils.network.NoConnectivityException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by riorizkyrainey on 13/10/17.
 */

public class ConnectivityInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtil.isNetworkConnected()) {
            throw new NoConnectivityException();
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

}

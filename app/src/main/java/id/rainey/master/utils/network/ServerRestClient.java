package id.rainey.master.utils.network;

/**
 * 02/18/2015 - Add Context parameter to get and post method
 */

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import javax.inject.Inject;

import cz.msebera.android.httpclient.HttpEntity;
import id.rainey.master.app.Application;
import id.rainey.master.session.ObscuredSharedPreferences;

public class ServerRestClient {

    private AsyncHttpClient client;
    private ObscuredSharedPreferences mPreferences;

    @Inject
    public ServerRestClient(AsyncHttpClient httpClient, ObscuredSharedPreferences preferences) {
        client = httpClient;
        mPreferences = preferences;
    }

    public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(Application.getContext(), url, params, responseHandler);
    }

    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(Application.getContext(), url, params, responseHandler);
    }

    public void post(String url, HttpEntity httpEntity, AsyncHttpResponseHandler responseHandler) {
        client.post(Application.getContext(), url, httpEntity, "application/json", responseHandler);
    }

    public void addHeader(String key, String value) {
        client.addHeader(key, value);
    }

    public void setTimeout(int timeout) {
        client.setTimeout(timeout);
    }

    public void cancelAllRequest(boolean cancel) {
        client.cancelAllRequests(cancel);
    }

    public void cancelRequest(boolean cancel) {
        client.cancelRequests(Application.getContext(), cancel);
    }
}

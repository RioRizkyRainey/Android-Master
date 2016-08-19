package id.rainey.master.utils.network;

import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import id.rainey.master.R;
import id.rainey.master.app.Application;

public abstract class JsonDataReceiver extends JsonHttpResponseHandler implements LoadingDataCallbackListener {

    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        System.out.println("On success " + response.toString());
        try {
            boolean succeed = response.getBoolean("status");

            if (succeed) {
                if (response.has("data")) {
                    onLoadingSucceed(response.get("data"));
                    return;
                }
                onLoadingSucceed(response);
                return;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(int statusCode, Header[] headers, Throwable t, JSONObject response) {
        super.onFailure(statusCode, headers, t, response);
        showMessage(response);
    }

    public void onFailure(int statusCode, Header[] headers, String response, Throwable t) {
        super.onFailure(statusCode, headers, response, t);

        onLoadingFailed();

        if (Looper.myLooper() != null) {
            String message = Application.getContext().getResources().getString(R.string.error_loading_failed);
            Toast.makeText(Application
                    .getContext(), message, Toast.LENGTH_SHORT).show();
        }

        Log.d("cek", response);
    }

    private void showMessage(JSONObject response) {
        String message = Application.getContext().getResources().getString(R.string.error_loading_failed);
        try {
            if (response.has("message")) {
                message = response.getString("message");
            }
        } catch (JSONException | NullPointerException e) {
            e.printStackTrace();
        }
        onLoadingFailed();
        if (Looper.myLooper() != null) {
            Toast.makeText(Application.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}

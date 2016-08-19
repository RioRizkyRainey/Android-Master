package id.rainey.master.utils.network;

public interface LoadingDataCallbackListener {
    void onLoadingSucceed(Object data);

    void onLoadingEmpty();

    void onLoadingFailed();
}

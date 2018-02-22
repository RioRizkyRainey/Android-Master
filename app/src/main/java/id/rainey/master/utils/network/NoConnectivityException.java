package id.rainey.master.utils.network;

import java.io.IOException;

/**
 * Created by riorizkyrainey on 13/10/17.
 */

public class NoConnectivityException extends IOException {
    @Override
    public String getMessage() {
        return "No connectivity exception";
    }

}

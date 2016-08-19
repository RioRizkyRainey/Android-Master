package id.rainey.master.session;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

/**
 * Created by riorizkyrainey on 22/06/16.
 */
@Module
public class ObscuredModule {

    private SharedPreferences mPreferences;

    public ObscuredModule(Context context) {
        mPreferences = context.getSharedPreferences("xyz", Context.MODE_PRIVATE);
    }

    @Provides
    SharedPreferences provideSharedPreferences() {
        return mPreferences;
    }

}

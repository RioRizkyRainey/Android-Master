package id.rainey.master.app;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by riorizkyrainey on 22/06/16.
 */
@Module
public final class ApplicationModule {

    private final Context mContext;
    private final Realm mRealm;

    ApplicationModule(Context context, Realm mRealm) {
        this.mContext = context;
        this.mRealm = mRealm;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

    @Provides
    Realm provideRealm() {
        return mRealm;
    }
}

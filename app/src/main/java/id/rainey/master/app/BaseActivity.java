package id.rainey.master.app;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import id.rainey.master.R;


/**
 * Created by riorizkyrainey on 6/3/16.
 */
public class BaseActivity extends AppCompatActivity implements BaseView {

    @Nullable
    private Toolbar toolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public void connectionError() {
        Toast.makeText(this, R.string.error_loading_failed, Toast.LENGTH_LONG).show();
    }

    @Nullable
    protected Toolbar getToolbar() {
        return toolbar;
    }
}

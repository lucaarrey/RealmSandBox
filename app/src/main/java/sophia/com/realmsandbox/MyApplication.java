package sophia.com.realmsandbox;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by archimede on 18/07/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }
}

package mixi.com.woodenhorsedemo;

import android.app.Application;

/**
 * Created by mixi on 2016/12/23.
 */

public class MiXiApplication extends Application {
    private static MiXiApplication mApplication;

    private int mVerification = -1;


    public static MiXiApplication getApplication() {
        return mApplication;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = (MiXiApplication) getApplicationContext();
    }
}

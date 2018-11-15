package com.zzteck.bigbwg.App;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2018/11/15 0015.
 */

public class App extends Application {

    private static Context mContext ;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = App.this ;
    }

    public static Context getContext(){
        return mContext ;
    }
}

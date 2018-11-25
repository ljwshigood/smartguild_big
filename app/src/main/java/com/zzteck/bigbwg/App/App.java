package com.zzteck.bigbwg.App;

import android.app.Application;
import android.content.Context;

import com.baidu.crabsdk.CrabSDK;
import com.baidu.crabsdk.OnAnrCrashListener;
import com.baidu.crabsdk.OnCrashExceedListener;
import com.fengmap.android.FMMapSDK;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/15 0015.
 */

public class App extends Application {

    private static Context mContext ;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = App.this ;
        FMMapSDK.init(this);
        //3f041e078dab0ff9

        CrabSDK.setConstantSameCrashExceedLimit(3);
        CrabSDK.setOnCrashExceedListener(new OnCrashExceedListener() {
            @Override
            public void onCrashExceedCallback() {
            }
        });


        CrabSDK.init(this, "3f041e078dab0ff9");

        CrabSDK.setBehaviorRecordLimit(8);
        CrabSDK.setUrlRecordLimit(8);

        CrabSDK.setUploadLimitOfSameCrashInOneday(-1);
        CrabSDK.setUploadLimitOfCrashInOneday(-1);

        CrabSDK.setUploadLimitOfAnrInOneday(-1);

        CrabSDK.setChannel("bwg_big");

        CrabSDK.setCollectScreenshot(true);

        CrabSDK.setEnableLog(true);

        HashMap<String, String> customMap = new HashMap<String, String>();
        CrabSDK.setUsersCustomKV(customMap);

        CrabSDK.setOnAnrCrashListener(new OnAnrCrashListener() {

            @Override
            public void onAnrStarted(Map map) {

            }

            @Override
            public void onCrashStarted(Thread arg0, Throwable arg1) {
            }

            @Override
            public void onNativeCrashStarted(Error arg0, String arg1, int arg2) {

            }
        });
    }

    public static Context getContext(){
        return mContext ;
    }
}

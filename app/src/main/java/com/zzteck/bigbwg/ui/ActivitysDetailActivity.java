package com.zzteck.bigbwg.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.fragment.LeftFragment;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class ActivitysDetailActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "VideoDetailFragment";

    private JCVideoPlayerStandard mJcVideoPlayerStandard;

    private void initView(){
        mJcVideoPlayerStandard = findViewById(R.id.jc_video);
    }

    public void updateContent(String videoPath){
        mJcVideoPlayerStandard.setUp(videoPath, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
    }

    private SlidingMenu menu ;

    public void sliceToggle(){
        menu.toggle();
    }

    public void initSliceMenu() {

        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        // 左边
        menu.setMenu(R.layout.left_menu);

        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

        Fragment leftFragment = LeftFragment.newInstance("左边");

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.left, leftFragment);
        ft.commit();

    }


    private void initData(){

        Intent intent = getIntent() ;
        String filePath = intent.getStringExtra("filePath") ;
        mJcVideoPlayerStandard.setUp(filePath, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_video_detail);
        initView() ;
        initData() ;
        initSliceMenu() ;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}

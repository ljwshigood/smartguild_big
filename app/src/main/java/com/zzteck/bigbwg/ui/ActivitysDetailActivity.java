package com.zzteck.bigbwg.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zzteck.bigbwg.R;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}

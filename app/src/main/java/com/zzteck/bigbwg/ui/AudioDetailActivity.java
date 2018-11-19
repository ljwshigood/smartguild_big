package com.zzteck.bigbwg.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.adapter.FileAdapter;
import com.zzteck.bigbwg.fragment.LeftFragment;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class AudioDetailActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "VideoDetailFragment";

    private void initData(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_video_detail);
        initView() ;
        initData() ;
    }

    private ListView mLvAudioList ;

    private void initView(){
      /*  FileAdapter adapter = new FileAdapter(AudioDetailActivity.this,1,null,null) ;
        mLvAudioList.setAdapter(adapter) ;*/
    }

    @Override
    public void onClick(View view) {

    }
}

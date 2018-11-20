package com.zzteck.bigbwg.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.adapter.FileAdapter;
import com.zzteck.bigbwg.bean.FileBean;
import com.zzteck.bigbwg.fragment.LeftFragment;
import com.zztek.mediaservier.BgMusicControlService;
import com.zztek.mediaservier.MusicBean;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class ActivitysDetailActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "VideoDetailFragment";

    private JCVideoPlayerStandard mJcVideoPlayerStandard;

    private Context mContext ;

    private void initView(){
        mIvLeft = findViewById(R.id.iv_left) ;
        mJcVideoPlayerStandard = findViewById(R.id.jc_video);
        mIvLeft.setOnClickListener(this);
    }

    private List<String> mAudioStringList ;

    private ListView mLvVideoDetail ;

    public void updateContent(String videoPath){
        mJcVideoPlayerStandard.setUp(videoPath, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
    }

    private SlidingMenu menu ;

    private ImageView mIvLeft ;

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

        View view = menu.getMenu() ;

        mLvVideoDetail = view.findViewById(R.id.lv_filePath) ;

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
        mAudioStringList = (List<String>) intent.getSerializableExtra("filelist");
        List<FileBean> list = new ArrayList<>() ;
        for(int i = 0 ;i < mAudioStringList.size() ;i++){
            FileBean bean = new FileBean();
            bean.setFilePath(mAudioStringList.get(i));
            list.add(bean) ;
        }

        FileAdapter adapter = new FileAdapter(mContext,list,0,null) ;
        mLvVideoDetail.setAdapter(adapter);
        mLvVideoDetail.setOnItemClickListener(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_video_detail);
        mContext = ActivitysDetailActivity.this ;
        initView() ;
        initData() ;
        initSliceMenu() ;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_left :
                sliceToggle();
                break ;
        }
    }
}

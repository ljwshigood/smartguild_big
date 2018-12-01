package com.zzteck.bigbwg.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.adapter.FileAdapter;
import com.zzteck.bigbwg.bean.FileBean;
import com.zzteck.bigbwg.fragment.LeftFragment;
import com.zzteck.bigbwg.utils.Constant;
import com.zztek.mediaservier.BgMusicControlService;
import com.zztek.mediaservier.MusicBean;
import com.zztek.mediaservier.MusicControl;
import com.zztek.mediaservier.MusicManager;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class AudioDetailActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "VideoDetailFragment";

    private List<String> mAudioStringList ;

    private Context mContext ;

    private TextView mTvCurrentTime ;

    private TextView mTvTotalTime ;

    private ProgressBar mProgressBar ;

    private CheckBox mCbStatus ;

    private ImageView mIvPre ;

    private ImageView mIvNext ;

    private TextView  mTvName ;

    private int mPosition ;

    private void initData(){
        Intent intent = getIntent() ;
        mPosition = intent.getIntExtra("position",0) ;
        mAudioStringList = (List<String>) intent.getSerializableExtra("filelist");
        List<MusicBean> list = new ArrayList<>() ;

        for(int i = 0 ;i < mAudioStringList.size() ;i++){
            String filePath = mAudioStringList.get(i) ;
            MusicBean bean = new MusicBean() ;
            bean.setFilePath(Constant.FILE_HOST+filePath) ;
            list.add(bean) ;
        }

        BgMusicControlService.mMusicItemList = list ;



        List<FileBean> list1 = new ArrayList<>() ;
        for(int i = 0 ;i < mAudioStringList.size() ;i++){
            FileBean bean1 = new FileBean();
            bean1.setFilePath(mAudioStringList.get(i));
            list1.add(bean1) ;
        }

        FileAdapter adapter = new FileAdapter(mContext,list1,0,null) ;
        mLvAudioList.setAdapter(adapter);
       // mLvAudioList.setOnItemClickListener(adapter);
        adapter.setmIMediaOnItemListener(new FileAdapter.IMediaOnItemListener() {
            @Override
            public void medidaOnItem(String filePath) {
                isFirstPlayer = false ;
                MusicManager.getInstance(mContext).playMusic(filePath,0) ;
            }
        }) ;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_audio_detail);
        mContext = AudioDetailActivity.this ;
        initView() ;
        initData() ;

        EventBus.getDefault().register(this);


        if (isFirstPlayer) {
            MusicManager.getInstance(mContext).playMusic(BgMusicControlService.mMusicItemList.get(mPosition).getFilePath(),0) ;
            isFirstPlayer = false ;
        }else{
            if (mCbStatus.isChecked()) {
                MusicManager.getInstance(mContext).resumeMusic();
            } else {
                MusicManager.getInstance(mContext).pauseMusic();
            }
        }

        mCbStatus.setChecked(true);

    }

    public String formatSecondTime(int millisecond) {
        if (millisecond == 0) {
            return "00:00";
        }
        millisecond = millisecond / 1000;
        int m = millisecond / 60 % 60;
        int s = millisecond % 60;
        return (m > 9 ? m : "0" + m) + ":" + (s > 9 ? s : "0" + s);
    }


    @Subscriber
    public void onEventMainThread(final MusicControl messageInfo){
        mTvTotalTime.setText(formatSecondTime((int)messageInfo.getTotalTime()));
        mTvCurrentTime.setText(formatSecondTime((int)messageInfo.getCurrentTime()));

        mProgressBar.setMax((int)messageInfo.getTotalTime()) ;
        mProgressBar.setProgress((int)messageInfo.getCurrentTime()) ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

        MusicManager.getInstance(mContext).releaseMedia();

    }

    private ListView mLvAudioList ;

    private ImageView mIvDelete ;

    private void initView(){
        mTvName = findViewById(R.id.tv_name) ;
        mIvDelete = findViewById(R.id.iv_delete) ;
        mIvDelete.setOnClickListener(this);
        mTvCurrentTime = findViewById(R.id.tv_start_time) ;
        mTvTotalTime = findViewById(R.id.tv_end_time) ;
        mProgressBar = findViewById(R.id.sb_record_seek) ;

        mCbStatus = findViewById(R.id.cb_playAndPause) ;
        mIvPre = findViewById(R.id.iv_playPre) ;
        mIvNext = findViewById(R.id.iv_playNext) ;

        mCbStatus.setOnClickListener(this);
        mIvPre.setOnClickListener(this);
        mIvNext.setOnClickListener(this);

        mLvAudioList = findViewById(R.id.lv_audio_list) ;
    }

    private boolean isFirstPlayer = true;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_delete :
                finish();
                break ;
            case R.id.cb_playAndPause:
                if (isFirstPlayer) {
                    MusicManager.getInstance(mContext).playMusic(mAudioStringList.get(0),0) ;
                    isFirstPlayer = false ;
                }else{
                    if (mCbStatus.isChecked()) {
                        MusicManager.getInstance(mContext).resumeMusic();
                    } else {
                        MusicManager.getInstance(mContext).pauseMusic();
                    }
                }
                break;
            case R.id.iv_playPre:
                MusicManager.getInstance(mContext).priorMusic();
                break;
            case R.id.iv_playNext :
                MusicManager.getInstance(mContext).nextMusic();
                break ;
        }
    }
}

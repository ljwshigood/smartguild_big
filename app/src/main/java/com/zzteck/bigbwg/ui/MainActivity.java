package com.zzteck.bigbwg.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.TextView;

import com.fengmap.android.FMMapSDK;
import com.google.gson.Gson;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.bean.ActDetailBean;
import com.zzteck.bigbwg.bean.ActListBean;
import com.zzteck.bigbwg.bean.BwgBean;
import com.zzteck.bigbwg.bean.LoginBean;
import com.zzteck.bigbwg.bean.MsgEvent;
import com.zzteck.bigbwg.bean.NearWenChuangBean;
import com.zzteck.bigbwg.bean.NearWenWuBean;
import com.zzteck.bigbwg.fragment.AFragment;
import com.zzteck.bigbwg.fragment.ActivityDetailFragment;
import com.zzteck.bigbwg.fragment.ActivityFragment;
import com.zzteck.bigbwg.fragment.ClassicStorageDetailFragment;
import com.zzteck.bigbwg.fragment.ClassicStorageFragment;
import com.zzteck.bigbwg.fragment.FMMapBasic;
import com.zzteck.bigbwg.fragment.PlaceHelpFragment;
import com.zzteck.bigbwg.fragment.TrafficFragment;
import com.zzteck.bigbwg.fragment.FeedBackFragment;
import com.zzteck.bigbwg.fragment.MapFragment;
import com.zzteck.bigbwg.fragment.PlaceGuildFragment;
import com.zzteck.bigbwg.fragment.VideoDetailFragment;
import com.zzteck.bigbwg.fragment.WelcomeFragment;
import com.zzteck.bigbwg.fragment.WenChuangDetailFragment;
import com.zzteck.bigbwg.fragment.WenChuangFragment;
import com.zzteck.bigbwg.fragment.SetFragment;
import com.zzteck.bigbwg.impl.IActManager;
import com.zzteck.bigbwg.utils.SharedPreferencesUtils;
import com.zzteck.bigbwg.view.RXViewPaper;
import com.zzteck.bigbwg.webmanager.WebActManager;
import com.zztek.mediaservier.BgMusicControlService;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private TextView mTvCGZN ;

    private TextView mTvCGBZ ;

    private TextView mTvCGJT ;

    private TextView mTvCGJY ;

    private TextView mTvJDGC ;

    private TextView mTvWCJN ;

    private TextView mTvCGDT ;

    private TextView mTvCGHD ;

   // private FrameLayout mFrameLayout ;

    private PlaceGuildFragment mPlaceGuildFragment ;

    private PlaceHelpFragment mPlaceHelpFragment;

    private TrafficFragment mTrafficFragement;

    private FeedBackFragment mFeedBackFragment;

    private WenChuangFragment mWenChuangFragment;

    private SetFragment mSetFragment ;

    private ClassicStorageFragment mClassStorageFragment;

  //  private MapFragment mMapFragment;

    private FMMapBasic mMapFragment;

    private ActivityFragment mActivityFragment ;

    private ActivityDetailFragment mActivityDetail ;

    private WenChuangDetailFragment mWenChuangDetailFragment;

    private ClassicStorageDetailFragment mClassicDetailFragment;

    private VideoDetailFragment mVideoDetailFragment ;

    private WelcomeFragment mWelcomeFragment ;

    private TextView mTvSet ;

    private List<Fragment> mFragments = new ArrayList<>();

    private RXViewPaper mVp ;

    private AFragment mAFragment ;

    private void initView(){

        mVp = findViewById(R.id.rx_vp) ;
        mTvSet = findViewById(R.id.tv_set) ;
        mTvCGZN = findViewById(R.id.tv_place_guild) ;
        mTvCGBZ = findViewById(R.id.tv_place_help) ;
        mTvCGJT = findViewById(R.id.tv_traffic) ;
        mTvCGJY = findViewById(R.id.tv_feedback) ;

        mTvJDGC = findViewById(R.id.tv_classic_storage) ;
        mTvWCJN = findViewById(R.id.tv_wenchuang_remember) ;
        mTvCGDT = findViewById(R.id.tv_map) ;
        mTvCGHD = findViewById(R.id.tv_activity) ;

        mTvCGZN.setOnClickListener(this);
        mTvCGBZ.setOnClickListener(this);
        mTvCGJT.setOnClickListener(this);
        mTvCGJY.setOnClickListener(this);

        mTvJDGC.setOnClickListener(this);
        mTvWCJN.setOnClickListener(this);
        mTvCGDT.setOnClickListener(this);
        mTvCGHD.setOnClickListener(this);

        mTvSet.setOnClickListener(this);

        mWelcomeFragment = new WelcomeFragment() ;
        mPlaceGuildFragment = new PlaceGuildFragment() ;
        mPlaceHelpFragment = new PlaceHelpFragment() ;
        mTrafficFragement = new TrafficFragment() ;
        mFeedBackFragment = new FeedBackFragment() ;
        mWenChuangFragment = new WenChuangFragment() ;
        mSetFragment = new SetFragment() ;
        mClassStorageFragment = new ClassicStorageFragment() ;
        mMapFragment = new FMMapBasic() ;
        mActivityFragment = new ActivityFragment() ;
        mActivityDetail  = new ActivityDetailFragment() ;
        mWenChuangDetailFragment = new WenChuangDetailFragment() ;
        mClassicDetailFragment = new ClassicStorageDetailFragment();

        mVideoDetailFragment = new VideoDetailFragment() ;

        mFragments.add(mWelcomeFragment) ;
        mFragments.add(mPlaceGuildFragment) ;
        mFragments.add(mPlaceHelpFragment) ;
        mFragments.add(mTrafficFragement) ;
        mFragments.add(mFeedBackFragment) ;
        mFragments.add(mClassStorageFragment) ;
        mFragments.add(mWenChuangFragment) ;
        mFragments.add(mMapFragment) ;
        mFragments.add(mActivityFragment) ;
        mFragments.add(mSetFragment) ;
        mFragments.add(mClassicDetailFragment) ;
        mFragments.add(mWenChuangDetailFragment) ;
        mFragments.add(mActivityDetail) ;
        mFragments.add(mVideoDetailFragment) ;

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter ( getSupportFragmentManager () ) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get ( position );
            }

            @Override
            public int getCount() {
                return mFragments.size ();
            }
        };

        mVp.setScrollble(false);
        mVp.setAdapter ( fragmentPagerAdapter );

        mVp.setOffscreenPageLimit ( 6 );
        mVp.setCurrentItem ( 0 );
    }

    private class CodeBean {

        private String code ;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    @Subscriber
    public void onEventMainThread(final MsgEvent event){
        if(event.getType() == 1){ // 活动详情
            String msg = event.getMsg() ;
            mActivityDetail.requestActivitys(msg);
            mVp.setCurrentItem(12);
        }else if(event.getType() == 5){
            mVideoDetailFragment.updateContent(event.getMsg());
            mVp.setCurrentItem(13);
        }
    }

    @Subscriber
    public void onEventMainThread(final NearWenWuBean.DataBean event){
        mVp.setCurrentItem(10);
        mClassicDetailFragment.updateContent(event.getFaceimg(),event.getVoice(),event.getVideo(),event.getDesc());
    }

    @Subscriber
    public void onEventMainThread(final NearWenChuangBean.DataBean event){
        mVp.setCurrentItem(11);
        mWenChuangDetailFragment.updateContent(event.getDesc());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView() ;

        Intent intent = new Intent(this, BgMusicControlService.class) ;
        startService(intent) ;

        if (Build.VERSION.SDK_INT < 23) {
            // Android 6.0 之前无需运行时权限申请
        } else {
            // 先检测权限   目前SDK只需2个危险权限，读和写存储卡
            int p = checkSelfPermission(FMMapSDK.SDK_PERMISSIONS[0]);
            if (p != PackageManager.PERMISSION_GRANTED ) {
                this.requestPermissions(
                        FMMapSDK.SDK_PERMISSIONS,  //SDK所需权限数组
                        FMMapSDK.SDK_PERMISSION_RESULT_CODE);   //SDK权限申请处理结果返回码
            } else {
                // 已经拥有权限了
            }
        }


        String device = (String) SharedPreferencesUtils.getParam(this,"device","00001");
        Gson gson = new Gson() ;
        CodeBean bean = new CodeBean() ;
        bean.setCode(device);
        String json = gson.toJson(bean) ;

        WebActManager.getInstance(this).loginWeb(this,json,new IActManager() {
            @Override
            public void IRelicLists(NearWenWuBean bean) {

            }

            @Override
            public void IRelicWenChuangLists(NearWenChuangBean bean) {

            }

            @Override
            public void ILogin(LoginBean bean) {
                EventBus.getDefault().post(new MsgEvent("",1));
            }

            @Override
            public void IActivityList(ActListBean bean) {

            }

            @Override
            public void IActivityDetail(ActDetailBean bean) {

            }

            @Override
            public void IBwgDetail(BwgBean bean) {

            }

            @Override
            public void startNetWorkRequest(String message) {

            }

            @Override
            public void endNetWorkRequest(String message) {

            }

            @Override
            public void netWorkError(String message) {

            }
        });

        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_set:
                mVp.setCurrentItem(9) ;
                break ;
            case R.id.tv_place_guild:
                mVp.setCurrentItem(1) ;
                break ;
            case R.id.tv_place_help:
                mVp.setCurrentItem(2) ;
                break ;
            case R.id.tv_traffic:
                mVp.setCurrentItem(3) ;
                break ;
            case R.id.tv_feedback:
                mVp.setCurrentItem(4);
                break ;
            case R.id.tv_classic_storage:
                mVp.setCurrentItem(5);
                break ;
            case R.id.tv_wenchuang_remember:
                mVp.setCurrentItem(6);
                break ;
            case R.id.tv_map:
                mVp.setCurrentItem(7);
                break ;
            case R.id.tv_activity:
                mVp.setCurrentItem(8);
                break ;
        }
    }
}

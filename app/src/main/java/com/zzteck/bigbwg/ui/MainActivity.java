package com.zzteck.bigbwg.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.bean.ActDetailBean;
import com.zzteck.bigbwg.bean.ActListBean;
import com.zzteck.bigbwg.bean.BwgBean;
import com.zzteck.bigbwg.bean.LoginBean;
import com.zzteck.bigbwg.bean.MsgEvent;
import com.zzteck.bigbwg.bean.NearWenChuangBean;
import com.zzteck.bigbwg.bean.NearWenWuBean;
import com.zzteck.bigbwg.fragment.ActivityDetailFragment;
import com.zzteck.bigbwg.fragment.CGActivityFragment;
import com.zzteck.bigbwg.fragment.CGBZFragment;
import com.zzteck.bigbwg.fragment.CGJTFragment;
import com.zzteck.bigbwg.fragment.CGJYFragment;
import com.zzteck.bigbwg.fragment.CGJinDianFragment;
import com.zzteck.bigbwg.fragment.CGMapFragment;
import com.zzteck.bigbwg.fragment.VideoDetailFragment;
import com.zzteck.bigbwg.fragment.WelcomeFragment;
import com.zzteck.bigbwg.fragment.WenChuangDetailFragment;
import com.zzteck.bigbwg.fragment.WenChuangFragment;
import com.zzteck.bigbwg.fragment.CGZNFragment;
import com.zzteck.bigbwg.fragment.JinDianDetailFragment;
import com.zzteck.bigbwg.fragment.SetFragment;
import com.zzteck.bigbwg.impl.IActManager;
import com.zzteck.bigbwg.utils.SharedPreferencesUtils;
import com.zzteck.bigbwg.webmanager.WebActManager;
import com.zztek.mediaservier.BgMusicControlService;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private TextView mTvCGZN ;

    private TextView mTvCGBZ ;

    private TextView mTvCGJT ;

    private TextView mTvCGJY ;

    private TextView mTvJDGC ;

    private TextView mTvWCJN ;

    private TextView mTvCGDT ;

    private TextView mTvCGHD ;

    private FrameLayout mFrameLayout ;

    private CGZNFragment mCGZNFragment ;

    private CGBZFragment mCGBZFragment ;

    private CGJTFragment mCGJTFragement ;

    private CGJYFragment mCGJYFragment ;

    private WenChuangFragment mWenChuangFragment;

    private SetFragment mSetFragment ;

    private CGJinDianFragment mJinDianFragment ;

    private CGMapFragment mMapFragment;

    private CGActivityFragment mActivityFragment ;

    private ActivityDetailFragment mActivityDetail ;

    private WenChuangDetailFragment mWenChuangDetailFragment;

    private JinDianDetailFragment mJDDetailFragment ;

    private VideoDetailFragment mVideoDetailFragment ;

    private WelcomeFragment mWelcomeFragment ;

    private TextView mTvSet ;

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                hideAllFragment();
                showFragment(mCGZNFragment) ;
                mCGZNFragment.requestBwgHome();
            }
        }
    } ;

    private void hideAllFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(mWelcomeFragment) ;
       /* fragmentTransaction.hide(mCGZNFragment);
        fragmentTransaction.hide(mCGBZFragment);
        fragmentTransaction.hide(mCGJTFragement);
        fragmentTransaction.hide(mCGJYFragment) ;
        fragmentTransaction.hide(mWenChuangFragment) ;
        fragmentTransaction.hide(mSetFragment) ;
        fragmentTransaction.hide(mJinDianFragment) ;
        fragmentTransaction.hide(mMapFragment) ;
        fragmentTransaction.hide(mActivityFragment) ;
        fragmentTransaction.hide(mActivityDetail) ;
        fragmentTransaction.hide(mWenChuangDetailFragment) ;
        fragmentTransaction.hide(mJDDetailFragment) ;
        fragmentTransaction.hide(mVideoDetailFragment) ;*/
        fragmentTransaction.commit() ;
    }


    private void initView(){
        mTvSet = findViewById(R.id.tv_set) ;
        mFrameLayout = findViewById(R.id.f_containers) ;
        mTvCGZN = findViewById(R.id.tv_cg_zhinan) ;
        mTvCGBZ = findViewById(R.id.tv_cg_help) ;
        mTvCGJT = findViewById(R.id.tv_cg_jiaotong) ;
        mTvCGJY = findViewById(R.id.tv_cg_jianyi) ;

        mTvJDGC = findViewById(R.id.tv_jdgc) ;
        mTvWCJN = findViewById(R.id.tv_wcjn) ;
        mTvCGDT = findViewById(R.id.tv_cgditu) ;
        mTvCGHD = findViewById(R.id.tv_cg_huodong) ;

        mTvCGZN.setOnClickListener(this);
        mTvCGBZ.setOnClickListener(this);
        mTvCGJT.setOnClickListener(this);
        mTvCGJY.setOnClickListener(this);

        mTvJDGC.setOnClickListener(this);
        mTvWCJN.setOnClickListener(this);
        mTvCGDT.setOnClickListener(this);
        mTvCGHD.setOnClickListener(this);

        mTvSet.setOnClickListener(this);

        mCGZNFragment = new CGZNFragment() ;
        mCGBZFragment = new CGBZFragment() ;
        mCGJTFragement= new CGJTFragment() ;
        mCGJYFragment = new CGJYFragment() ;
        mWenChuangFragment = new WenChuangFragment() ;
        mSetFragment = new SetFragment() ;
        mJinDianFragment = new CGJinDianFragment() ;
        mMapFragment = new CGMapFragment() ;
        mActivityFragment = new CGActivityFragment() ;
        mActivityDetail  = new ActivityDetailFragment() ;
        mWenChuangDetailFragment = new WenChuangDetailFragment() ;
        mJDDetailFragment = new JinDianDetailFragment();

        mVideoDetailFragment = new VideoDetailFragment() ;

        mWelcomeFragment = new WelcomeFragment() ;

        getSupportFragmentManager().beginTransaction()
                .add(R.id.f_containers,mWelcomeFragment)
               // .add(R.id.f_containers,mCGZNFragment)
                /*.add(R.id.f_containers,mCGBZFragment)
                .add(R.id.f_containers,mCGJTFragement)
                .add(R.id.f_containers,mCGJYFragment)
                .add(R.id.f_containers, mWenChuangFragment)
                .add(R.id.f_containers,mSetFragment)
                .add(R.id.f_containers,mJinDianFragment)
                .add(R.id.f_containers,mMapFragment)
                .add(R.id.f_containers,mActivityFragment)
                .add(R.id.f_containers,mActivityDetail)
                .add(R.id.f_containers, mWenChuangDetailFragment)
                .add(R.id.f_containers,mJDDetailFragment)
                .add(R.id.f_containers,mVideoDetailFragment)*/
                .commit();

        hideAllFragment() ;
        showFragment(mWelcomeFragment);
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
        hideAllFragment();
        if(event.getType() == 1){ // 活动详情
            String msg = event.getMsg() ;
            mActivityDetail.requestActivitys(msg);
            showFragment(mActivityDetail) ;
        }else if(event.getType() == 5){
            mVideoDetailFragment.updateContent(event.getMsg());
            showFragment(mVideoDetailFragment) ;
        }
    }

    @Subscriber
    public void onEventMainThread(final NearWenWuBean.DataBean event){
        hideAllFragment();
        showFragment(mJDDetailFragment);
        mJDDetailFragment.updateContent(event.getFaceimg(),event.getVoice(),event.getVideo(),event.getDesc());
    }

    @Subscriber
    public void onEventMainThread(final NearWenChuangBean.DataBean event){
        hideAllFragment();
        showFragment(mWenChuangDetailFragment);
        mWenChuangDetailFragment.updateContent(event.getDesc());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView() ;

        Intent intent = new Intent(this, BgMusicControlService.class) ;
        startService(intent) ;

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

    /**
     * 显示fragment
     *
     * @param fragment 要显示的fragment
     */
    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().show(mWelcomeFragment).commit();
        //getSupportFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
        //getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_set:
                hideAllFragment();
                showFragment(mSetFragment) ;
                break ;
            case R.id.tv_cg_zhinan :
                hideAllFragment();
               /* showFragment(mCGZNFragment) ;
                mCGZNFragment.requestBwg();*/
                break ;
            case R.id.tv_cg_help :
                hideAllFragment();
                showFragment(mCGBZFragment) ;
               // mCGBZFragment.requestBwg();
                break ;
            case R.id.tv_cg_jiaotong:
                hideAllFragment();
                showFragment(mCGJTFragement) ;
               // mCGJTFragement.requestBwg();
                break ;
            case R.id.tv_cg_jianyi:
                hideAllFragment();
                showFragment(mCGJYFragment) ;
                break ;

            case R.id.tv_jdgc:
                hideAllFragment();
                showFragment(mJinDianFragment) ;
               // mJinDianFragment.requestWeb();

                break ;
            case R.id.tv_wcjn:
                hideAllFragment();
                showFragment(mWenChuangFragment) ;
              //  mWenChuangFragment.requestWeb();
                break ;
            case R.id.tv_cgditu :
                hideAllFragment();
                showFragment(mMapFragment) ;
                break ;
            case R.id.tv_cg_huodong:
                hideAllFragment();
                showFragment(mActivityFragment) ;
               // mActivityFragment.requestActivitys();
                break ;
        }
    }
}

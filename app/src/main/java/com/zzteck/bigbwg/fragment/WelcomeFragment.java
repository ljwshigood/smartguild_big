package com.zzteck.bigbwg.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zzteck.bigbwg.App.App;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.bean.ActDetailBean;
import com.zzteck.bigbwg.bean.ActListBean;
import com.zzteck.bigbwg.bean.BwgBean;
import com.zzteck.bigbwg.bean.LoginBean;
import com.zzteck.bigbwg.bean.MsgEvent;
import com.zzteck.bigbwg.bean.NearWenChuangBean;
import com.zzteck.bigbwg.bean.NearWenWuBean;
import com.zzteck.bigbwg.impl.IActManager;
import com.zzteck.bigbwg.utils.Constant;
import com.zzteck.bigbwg.webmanager.WebActManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

/**
 *
 */


public class WelcomeFragment extends Fragment implements  IActManager{

    private static final String TAG = "WelcomeFragment";

    private LinearLayout mLlShouYe ;

    private ImageView mIvLogo ;

    private TextView mTvLogo ;

    @Subscriber
    public void onEventMainThread(final MsgEvent msgEvent){
        requestBwgHome();
    }

    private void initView(View view){
        mLlShouYe= view.findViewById(R.id.ll_shouye) ;
        mIvLogo = view.findViewById(R.id.iv_logo) ;
        mTvLogo = view.findViewById(R.id.tv_intrution) ;
    }


    private void initHome(BwgBean bean){
        mLlShouYe.setVisibility(View.VISIBLE) ;
        try {
            JSONArray jsonArray = new JSONArray(bean.getData().getImgs()) ;
            if(jsonArray != null && jsonArray.length() > 0){
                Glide.with(App.getContext()).load(Constant.FILE_HOST+jsonArray.get(0).toString()).placeholder(R.mipmap.ic_launcher).into(mIvLogo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mTvLogo.setText(Html.fromHtml(bean.getData().getDesc()));
    }

    private BwgBean mBwgBean1 ;

    public void requestBwgHome(){
        WebActManager.getInstance(getActivity()).getBwg1(getActivity(), "0", new IActManager() {
            @Override
            public void IRelicLists(NearWenWuBean bean) {

            }

            @Override
            public void IRelicWenChuangLists(NearWenChuangBean bean) {

            }

            @Override
            public void ILogin(LoginBean bean) {

            }

            @Override
            public void IActivityList(ActListBean bean) {

            }

            @Override
            public void IActivityDetail(ActDetailBean bean) {

            }

            @Override
            public void IBwgDetail(BwgBean bean) {
                mBwgBean1 = bean ;
                mHandler.sendEmptyMessage(1) ;
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
    }

    private Context mContext ;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_welcome,null) ;
        initView(view) ;
        mContext = getActivity() ;
        requestBwgHome() ;
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

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

    @Override
    public void IRelicLists(NearWenWuBean bean) {

    }

    @Override
    public void IRelicWenChuangLists(NearWenChuangBean bean) {

    }

    @Override
    public void ILogin(LoginBean bean) {

    }

    @Override
    public void IActivityList(ActListBean bean) {

    }

    @Override
    public void IActivityDetail(ActDetailBean bean) {

    }

    private BwgBean mBwgBean ;

    @Override
    public void IBwgDetail(BwgBean bean) {
        mBwgBean = bean ;
        mHandler.sendEmptyMessage(0) ;
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                initHome(mBwgBean1) ;
            }
        }
    } ;

}

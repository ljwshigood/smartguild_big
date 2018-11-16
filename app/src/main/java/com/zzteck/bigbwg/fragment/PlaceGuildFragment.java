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
import android.widget.TextView;

import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.bean.ActDetailBean;
import com.zzteck.bigbwg.bean.ActListBean;
import com.zzteck.bigbwg.bean.BwgBean;
import com.zzteck.bigbwg.bean.LoginBean;
import com.zzteck.bigbwg.bean.NearWenChuangBean;
import com.zzteck.bigbwg.bean.NearWenWuBean;
import com.zzteck.bigbwg.impl.IActManager;
import com.zzteck.bigbwg.webmanager.WebActManager;

/**
 *
 */


public class PlaceGuildFragment extends Fragment implements  IActManager{

    private static final String TAG = "PlaceGuildFragment";

    private TextView mTvPlaceGuildContent;

    private void initView(View view){
        mTvPlaceGuildContent = view.findViewById(R.id.tv_cgzn) ;
    }

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

    public void requestBwg(){
        WebActManager.getInstance(getActivity()).getBwg(getActivity(),"0",this);
    }

    private Context mContext ;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_place_guild,null) ;
        initView(view) ;
        mContext = getActivity() ;
        requestBwg() ;
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
            mTvPlaceGuildContent.setText("");
            if(msg.what == 0){
                if(mBwgBean != null){
                    mTvPlaceGuildContent.setVisibility(View.VISIBLE) ;
                    mTvPlaceGuildContent.setText(Html.fromHtml(mBwgBean.getData().getAttention()+""));
                }
            }
        }
    } ;

}

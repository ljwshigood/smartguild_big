package com.zzteck.bigbwg.fragment;

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
 * Created by Tan on 2018/7/23.
 */

public class CGBZFragment extends Fragment implements IActManager{

    private static final String TAG = "CGZNFragment";

    private TextView mTvName,mTvAddress,mTvCall;

    private void initView(View view){
        mTvName = view.findViewById(R.id.tv_name) ;
        mTvAddress = view.findViewById(R.id.tv_address) ;
        mTvCall = view.findViewById(R.id.tv_call) ;
    }

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTvName.setText(mBwgBean.getData().getName());
            mTvAddress.setText("地址："+mBwgBean.getData().getAddr());
            mTvCall.setText("场馆服务帮助电话："+mBwgBean.getData().getTel());
        }
    } ;

    public void requestBwg(){
        WebActManager.getInstance(getActivity()).setmIActManager(this);
        WebActManager.getInstance(getActivity()).getBwg(getActivity(),"0");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_gcbz,null) ;
        initView(view) ;
        requestBwg() ;
        return view;
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
        if(bean != null){
            mBwgBean = bean ;
            mHandler.sendEmptyMessage(0) ;
          /*  mTvCGBZ.setText("");
            mTvCGBZ.setText(bean.getData().get);*/
        }
    }
}

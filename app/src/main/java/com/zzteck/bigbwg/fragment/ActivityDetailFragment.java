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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zzteck.bigbwg.App.App;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.adapter.ActivityAdapter;
import com.zzteck.bigbwg.bean.ActDetailBean;
import com.zzteck.bigbwg.bean.ActListBean;
import com.zzteck.bigbwg.bean.BwgBean;
import com.zzteck.bigbwg.bean.LoginBean;
import com.zzteck.bigbwg.bean.NearWenChuangBean;
import com.zzteck.bigbwg.bean.NearWenWuBean;
import com.zzteck.bigbwg.impl.IActManager;
import com.zzteck.bigbwg.utils.Constant;
import com.zzteck.bigbwg.view.NoScrollWebView;
import com.zzteck.bigbwg.webmanager.WebActManager;

/**
 * Created by Tan on 2018/7/23.
 */

public class ActivityDetailFragment extends Fragment {

    private static final String TAG = "ActivityDetailFragment";

    //private TextView mTvDetail ;

    private ImageView mIvPhoto ;

    private TextView mTvTitle ;

    private void initData(){

    }

    private void initView(View view){
       // mTvDetail = view.findViewById(R.id.tv_detail) ;
        mWebView = view.findViewById(R.id.webview) ;
        mTvTitle = view.findViewById(R.id.tv_title) ;
        mIvPhoto = view.findViewById(R.id.iv_photo) ;
    }

    private ActDetailBean mCurrentBean ;

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            mWebView.loadDataWithBaseURL(null,mCurrentBean.getData().getDesc(),"text/html","utf-8",null);
            mTvTitle.setText(mCurrentBean.getData().getName());
            Glide.with(App.getContext()).load(Constant.FILE_HOST+mCurrentBean.getData().getImgs()).placeholder(R.mipmap.ic_launcher).into(mIvPhoto);
           // mTvDetail.setText(Html.fromHtml(mCurrentBean.getData().getDesc()));
        }
    } ;

    public void requestActivitys(String id){

        WebActManager.getInstance(getActivity()).activityDetail(getActivity(),id,new IActManager() {
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
                if(bean.getErrcode() == 200){
                    mCurrentBean = bean ;
                    mHandler.sendEmptyMessage(0) ;
                }
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
        }) ;
    }

    private NoScrollWebView mWebView ;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_activity_detail,null) ;
        initView(view) ;
        initData() ;
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }


}

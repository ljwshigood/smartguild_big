package com.zzteck.bigbwg.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.adapter.ActivityAdapter;
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

public class ActivityFragment extends Fragment {

    private static final String TAG = "ActivityFragment";

    private TextView mTvCGZN ;

    private ActivityAdapter mActivityAdapter ;

    private ListView mLvActivity ;

    private void initData(){
        mActivityAdapter = new ActivityAdapter(getActivity(),null) ;
        mLvActivity.setAdapter(mActivityAdapter) ;
        mLvActivity.setOnItemClickListener(mActivityAdapter);
    }

    private void initView(View view){
        mTvCGZN = view.findViewById(R.id.tv_cgzn) ;
        mLvActivity = view.findViewById(R.id.lv_memory) ;
    }

    private ActListBean mCurrentBean ;

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mActivityAdapter.notifyCommandAdapter(mCurrentBean.getData());
        }
    } ;

    public void requestActivitys(){
        WebActManager.getInstance(getActivity()).activityList(getActivity(),new IActManager() {
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
                if(bean.getErrcode() == 200){
                    mCurrentBean = bean ;
                    mHandler.sendEmptyMessage(0) ;
                }
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
        }) ;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_activity,null) ;
        initView(view) ;
        initData() ;
        requestActivitys() ;
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }


}

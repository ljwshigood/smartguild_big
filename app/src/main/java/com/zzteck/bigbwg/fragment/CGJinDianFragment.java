package com.zzteck.bigbwg.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.adapter.JingDianAdapter;
import com.zzteck.bigbwg.bean.ActDetailBean;
import com.zzteck.bigbwg.bean.ActListBean;
import com.zzteck.bigbwg.bean.LoginBean;
import com.zzteck.bigbwg.bean.NearWenChuangBean;
import com.zzteck.bigbwg.bean.NearWenWuBean;
import com.zzteck.bigbwg.impl.IActManager;
import com.zzteck.bigbwg.webmanager.WebActManager;

/**
 * Created by Tan on 2018/7/23.
 */

public class CGJinDianFragment extends Fragment {

    private static final String TAG = "CGJinDianFragment";

    private TextView mTvCGZN ;

    private GridView mGvJinDian ;

    private JingDianAdapter mAdapter ;

    private void initView(View view){
        mTvCGZN = view.findViewById(R.id.tv_cgzn) ;
        mGvJinDian = view.findViewById(R.id.gv_jdgc) ;
        mAdapter = new JingDianAdapter(getActivity(),null) ;
        mGvJinDian.setAdapter(mAdapter) ;
        mGvJinDian.setOnItemClickListener(mAdapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_jdgc,null) ;
        initView(view) ;
        requestWeb();
        return view;
    }


    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mAdapter.notifyJindianAdapter(mCurrentBean.getData());
        }
    } ;

    private NearWenWuBean mCurrentBean  ;

    public void requestWeb() {

        WebActManager.getInstance(getActivity()).relicLists(getActivity(),1,"","");
        WebActManager.getInstance(getActivity()).setmIActManager(new IActManager() {
            @Override
            public void IRelicLists(NearWenWuBean bean) {
                if(bean.getErrcode() == 200){
                    mCurrentBean = bean ;
                    mHandler.sendEmptyMessage(0) ;
                }
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


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }


}

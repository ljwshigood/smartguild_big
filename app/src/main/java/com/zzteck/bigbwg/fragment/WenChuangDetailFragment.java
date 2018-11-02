package com.zzteck.bigbwg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.zzteck.bigbwg.R;

/**
 * Created by Tan on 2018/7/23.
 */

public class WenChuangDetailFragment extends Fragment {

    private static final String TAG = "WenChuangDetailFragment";

    private WebView mWebViewDetail;

    private void initData(){

    }

    private void initView(View view){
        mWebViewDetail = view.findViewById(R.id.webview) ;
    }

    public void updateContent(String content){
        mWebViewDetail.loadDataWithBaseURL(null,content,"text/html","utf-8",null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_momery_detail,null) ;
        initView(view) ;
        initData() ;
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }


}

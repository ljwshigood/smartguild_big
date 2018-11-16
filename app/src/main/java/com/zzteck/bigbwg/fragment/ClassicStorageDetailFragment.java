package com.zzteck.bigbwg.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.adapter.JingDianAdapter;
import com.zzteck.bigbwg.bean.ActDetailBean;
import com.zzteck.bigbwg.bean.ActListBean;
import com.zzteck.bigbwg.bean.LoginBean;
import com.zzteck.bigbwg.bean.NearWenWuBean;
import com.zzteck.bigbwg.dialog.AudioListDialog;
import com.zzteck.bigbwg.dialog.VideoListDialog;
import com.zzteck.bigbwg.impl.IActManager;
import com.zzteck.bigbwg.utils.Constant;
import com.zzteck.bigbwg.webmanager.WebActManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tan on 2018/7/23.
 */

public class ClassicStorageDetailFragment extends Fragment {

    private static final String TAG = "ClassicStorageDetailFragment";

    private WebView mWebView ;

    private LinearLayout mLLAudio ;

    private LinearLayout mLLVideo ;

    private ImageView mIvCover ;

    private void initView(View view){
        mLLAudio = view.findViewById(R.id.ll_audio) ;
        mLLVideo = view.findViewById(R.id.ll_video) ;
        mWebView = view.findViewById(R.id.webview) ;
        mIvCover = view.findViewById(R.id.ic_cover) ;
        mLLAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAudioStringList != null && !mAudioStringList.isEmpty()){
                    AudioListDialog dialog = new AudioListDialog(getActivity(),mAudioStringList) ;
                    dialog.show() ;
                }
            }
        });

        mLLVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mVideoStringList != null && !mVideoStringList.isEmpty()){
                    VideoListDialog dialog = new VideoListDialog(getActivity(),mVideoStringList) ;
                    dialog.show() ;
                }

            }
        });

    }

    private List<String> mAudioStringList = new ArrayList<>() ;

    private List<String> mVideoStringList = new ArrayList<>() ;

    public void updateContent(String path,String audioString,String videoString,String content){

        if(TextUtils.isEmpty(audioString)){
            mLLAudio.setVisibility(View.GONE) ;
        }else {
            mAudioStringList = new Gson().fromJson(audioString, new TypeToken<List<String>>() {}.getType());
            mLLAudio.setVisibility(View.VISIBLE) ;
        }

        if(TextUtils.isEmpty(videoString) || "[]".equals(videoString)){
            mLLVideo.setVisibility(View.GONE) ;
        }else{
            mVideoStringList = new Gson().fromJson(videoString, new TypeToken<List<String>>() {}.getType());
            mLLVideo.setVisibility(View.VISIBLE) ;
        }

        Glide.with(this).load(Constant.FILE_HOST+path).placeholder(R.mipmap.ic_launcher)
                .into(mIvCover);
        mWebView.loadDataWithBaseURL(null,content,"text/html","utf-8",null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_jd_detail,null) ;
        initView(view) ;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }


}

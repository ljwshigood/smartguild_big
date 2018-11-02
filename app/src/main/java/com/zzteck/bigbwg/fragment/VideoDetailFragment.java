package com.zzteck.bigbwg.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.dialog.AudioListDialog;
import com.zzteck.bigbwg.dialog.VideoListDialog;
import com.zzteck.bigbwg.utils.Constant;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Tan on 2018/7/23.
 */

public class VideoDetailFragment extends Fragment {

    private static final String TAG = "VideoDetailFragment";

    private JCVideoPlayerStandard mJcVideoPlayerStandard;

    private void initView(View view){
        mJcVideoPlayerStandard = view.findViewById(R.id.jc_video);
    }

    public void updateContent(String videoPath){
        mJcVideoPlayerStandard.setUp(videoPath, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_video_detail,null) ;
        initView(view) ;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

}

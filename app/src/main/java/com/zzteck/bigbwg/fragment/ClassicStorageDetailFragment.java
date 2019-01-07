package com.zzteck.bigbwg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.adapter.FileAdapter;
import com.zzteck.bigbwg.adapter.VpAdapter;
import com.zzteck.bigbwg.bean.FileBean;
import com.zzteck.bigbwg.bean.MsgEvent;
import com.zzteck.bigbwg.ui.VideoDetailActivity;
import com.zzteck.bigbwg.ui.AudioDetailActivity;
import com.zzteck.bigbwg.utils.Constant;
import com.zzteck.bigbwg.view.WJListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.Serializable;
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

    //private ImageView mIvCover ;

    private WJListView mLvAudio ,mLvVideo ;

    private ViewPager viewPager;

    private void initView(View view){

        viewPager =  view.findViewById(R.id.viewPager) ;
        mLvAudio= view.findViewById(R.id.lv_audio) ;
        mLvVideo = view.findViewById(R.id.lv_video) ;
        mLLAudio = view.findViewById(R.id.ll_audio) ;
        mLLVideo = view.findViewById(R.id.ll_video) ;
        mWebView = view.findViewById(R.id.webview) ;
      //  mIvCover = view.findViewById(R.id.ic_cover) ;
        mLLAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAudioStringList != null && !mAudioStringList.isEmpty()){

                }
            }
        });

        mLLVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mVideoStringList != null && !mVideoStringList.isEmpty()){

                }

            }
        });

    }

    private List<String> mAudioStringList = new ArrayList<>() ;

    private List<String> mVideoStringList = new ArrayList<>() ;

    private List<String> imgs = new ArrayList<>() ;

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


        if(mAudioStringList != null && !mAudioStringList.isEmpty()){
            final List<FileBean> list = new ArrayList<>() ;
            for(int i = 0; i < mAudioStringList.size() ; i++){
                FileBean bean = new FileBean();
                bean.setFilePath(mAudioStringList.get(i));
                list.add(bean) ;
            }
            final FileAdapter adapter = new FileAdapter(getActivity(),list,0,null) ;
            mLvAudio.setAdapter(adapter);
            mLvAudio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    for(int i = 0 ;i < adapter.getmFileList().size() ;i++){
                        if(i == position){
                            adapter.getmFileList().get(i).setSelect(true);
                        }else{
                            adapter.getmFileList().get(i).setSelect(false);
                        }
                    }
                    adapter.notifyDataSetChanged();

                    List<String> list = new ArrayList<>() ;
                    for(int i = 0 ;i < adapter.getmFileList().size() ;i++){
                        list.add(adapter.getmFileList().get(i).getFilePath()) ;
                    }
                    Intent intent = new Intent(getActivity(), AudioDetailActivity.class) ;
                    intent.putExtra("filelist", (Serializable) list) ;
                    intent.putExtra("position",position) ;
                    getActivity().startActivity(intent);
                }
            });
        }

        if(mVideoStringList != null && !mVideoStringList.isEmpty()){

            List<FileBean> list = new ArrayList<>() ;
            for(int i = 0; i < mVideoStringList.size() ; i++){
                FileBean bean = new FileBean();
                bean.setFilePath(mVideoStringList.get(i));
                list.add(bean) ;
            }

            final FileAdapter adapter = new FileAdapter(getActivity(),list,0,null) ;
            mLvVideo.setAdapter(adapter);
            mLvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    EventBus.getDefault().post(new MsgEvent(adapter.getmFileList().get(position).getFilePath(),5));
                }
            });
        }


       /* Glide.with(this).load(Constant.FILE_HOST+path).placeholder(R.mipmap.ic_launcher)
                .into(mIvCover);*/
        mWebView.loadDataWithBaseURL(null,content,"text/html","utf-8",null);

        try {
            JSONArray jsonArray = new JSONArray(path) ;
            for(int i = 0  ;i < jsonArray.length() ;i++){
                imgs.add(jsonArray.get(i).toString()) ;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        VpAdapter adapter = new VpAdapter(getActivity(),imgs);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_jd_detail,null) ;
        initView(view) ;
        EventBus.getDefault().register(this);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
    @Subscriber
    public void onEventMainThread(final MsgEvent event){

    }

}

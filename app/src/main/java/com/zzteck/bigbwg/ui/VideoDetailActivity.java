package com.zzteck.bigbwg.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.adapter.FileAdapter;
import com.zzteck.bigbwg.bean.FileBean;
import com.zzteck.bigbwg.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoDetailActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "VideoDetailFragment";

    private JCVideoPlayerStandard mJcVideoPlayerStandard;

    private Context mContext ;

    private void initView(){
        mIvLeft = findViewById(R.id.iv_left) ;
        mJcVideoPlayerStandard = findViewById(R.id.jc_video);
        mIvLeft.setOnClickListener(this);
        mIvBack = findViewById(R.id.iv_delete) ;
        mIvBack.setOnClickListener(this);
    }

    private List<String> mMediaStringList;

    private ListView mLvMediaDetail;

    private String mFilePath ;

    public void updateContent(String videoPath){
        mJcVideoPlayerStandard.setUp(videoPath, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
    }


    private ImageView mIvLeft ;

    private ImageView mIvBack ;


    private void initData(){

        Intent intent = getIntent() ;
       /* String filePath = intent.getStringExtra("filePath") ;
        mJcVideoPlayerStandard.setUp(filePath, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");*/
        mMediaStringList = (List<String>) intent.getSerializableExtra("fileList");
        mFilePath = intent.getStringExtra("filePath") ;

        mJcVideoPlayerStandard.setUp(Constant.FILE_HOST+mFilePath, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_video_detail);
        mContext = VideoDetailActivity.this ;
        initView() ;
        initData() ;

        mJcVideoPlayerStandard.startPlay() ;
   //     initPopupWindow();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_delete :
                finish();
                break  ;
            case R.id.iv_left :
                if(popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else {
                    popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_main, null), Gravity.LEFT, 0, 500);
                }
                break ;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JCMediaManager.instance().releaseMediaPlayer();
        JCVideoPlayerManager.setFirstFloor(null);
    }

    private PopupWindow popupWindow ;

    public void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    class popupDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }


    protected void initPopupWindow(){

        View popupWindowView = getLayoutInflater().inflate(R.layout.left_menu, null);
        popupWindow = new PopupWindow(popupWindowView, 250, ViewGroup.LayoutParams.FILL_PARENT, true);

        popupWindow.setAnimationStyle(R.style.AnimationLeftFade);

        ColorDrawable dw = new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(dw);

        //设置背景半透明
        backgroundAlpha(0.5f);
        //关闭事件
        popupWindow.setOnDismissListener(new popupDismissListener());

        popupWindowView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /*if( popupWindow!=null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                    popupWindow=null;
                }*/
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                return false;
            }
        });

        mLvMediaDetail = popupWindowView.findViewById(R.id.lv_filePath) ;
        List<FileBean> list = new ArrayList<>() ;
        for(int i = 0; i < mMediaStringList.size() ; i++){
            FileBean bean = new FileBean();
            bean.setFilePath(mMediaStringList.get(i));
            list.add(bean) ;
        }

        FileAdapter adapter = new FileAdapter(mContext,list,0,null) ;
        mLvMediaDetail.setAdapter(adapter);
      //  mLvMediaDetail.setOnItemClickListener(adapter);

        adapter.setmIMediaOnItemListener(new FileAdapter.IMediaOnItemListener() {
            @Override
            public void medidaOnItem(String filePath) {
                mJcVideoPlayerStandard.setUp(filePath, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
            }
        });

    }


}

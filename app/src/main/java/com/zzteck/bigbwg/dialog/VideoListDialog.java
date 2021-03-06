package com.zzteck.bigbwg.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.adapter.FileAdapter;
import com.zzteck.bigbwg.bean.FileBean;

import java.util.List;

/**
 * Created by Administrator on 2018/10/29 0029.
 */

public class VideoListDialog extends Dialog {

    private Context mContext ;

    private List<FileBean> mVideoPathList;

    private ListView mLvFile ;

    private LinearLayout mLLCancel ;

    public VideoListDialog(@NonNull Context context,List<FileBean> paths) {
        super(context, R.style.CustomDialogStyle);
        this.mContext = context;
        this.mVideoPathList = paths ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_video_list,null,false);
        this.setContentView(view);

        mLvFile = view.findViewById(R.id.lv_video_list) ;
        mLLCancel = view.findViewById(R.id.ll_cancel) ;
        FileAdapter adapter = new FileAdapter(mContext, mVideoPathList, 1, new FileAdapter.IDialogListener() {
            @Override
            public void dismiss() {
                VideoListDialog.this.dismiss();
            }
        }) ;
        mLvFile.setAdapter(adapter);
       // mLvFile.setOnItemClickListener(adapter);

        Window window = getWindow();
        assert window != null;
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = window.getWindowManager().getDefaultDisplay().getWidth() - 90;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

        setCancelable(false);

    }

}

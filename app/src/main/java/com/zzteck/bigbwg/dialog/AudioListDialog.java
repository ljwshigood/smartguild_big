package com.zzteck.bigbwg.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.adapter.FileAdapter;
import com.zzteck.bigbwg.bean.FileBean;
import com.zztek.mediaservier.MusicControl;

import java.util.List;

/**
 * Created by Administrator on 2018/10/29 0029.
 */

public class AudioListDialog extends Dialog {

    private Context mContext ;

    private List<FileBean> mAudioPathList ;

    private ListView mLvFile ;

    public AudioListDialog(@NonNull Context context,List<FileBean> paths) {
        super(context, R.style.CustomDialogStyle);
        this.mContext = context;
        this.mAudioPathList = paths ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_audio_list,null,false);
        this.setContentView(view);

        mLvFile = view.findViewById(R.id.lv_audio_list) ;
        FileAdapter adapter = new FileAdapter(mContext,mAudioPathList,0,null) ;
        mLvFile.setAdapter(adapter);
        mLvFile.setOnItemClickListener(adapter);

        Window window = getWindow();
        assert window != null;
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = window.getWindowManager().getDefaultDisplay().getWidth() - 90;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(listener);

        Intent intent = new Intent() ;
        MusicControl musicControl = new MusicControl() ;
        musicControl.setmAction(3);
        mContext.sendBroadcast(intent) ;

    }
}

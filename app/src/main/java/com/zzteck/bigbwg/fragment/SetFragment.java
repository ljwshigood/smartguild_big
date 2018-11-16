package com.zzteck.bigbwg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.utils.SharedPreferencesUtils;

import java.lang.invoke.MethodType;

/**
 * Created by Tan on 2018/7/23.
 */

public class SetFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "PlaceGuildFragment";

    private EditText mEtSet ;

    private LinearLayout mLLCommit ;

    private TextView mTvOk ;

    private void initView(View view){
        mTvOk = view.findViewById(R.id.tv_ok) ;
        mEtSet = view.findViewById(R.id.et_set) ;
        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deviceId = mEtSet.getText().toString().trim() ;
                if(!TextUtils.isEmpty(deviceId)){
                    SharedPreferencesUtils.setParam(getActivity(),"device",deviceId) ;
                    Toast.makeText(getActivity(),"重置机器码成功",1).show();
                }else{
                    Toast.makeText(getActivity(),"重置机器码不能为空",1).show();
                }

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_set,null) ;
        initView(view) ;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {

    }
}

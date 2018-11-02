package com.zzteck.bigbwg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzteck.bigbwg.R;

/**
 * Created by Tan on 2018/7/23.
 */

public class CGJYFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "CGZNFragment";

    private EditText mTvChenhu , mTvDianhua,mTvMail,mTvContent;

    private LinearLayout mLLCommit ;

    private void initView(View view){
        mTvChenhu = view.findViewById(R.id.et_chenghu) ;
        mTvDianhua = view.findViewById(R.id.et_dianhua) ;
        mTvMail = view.findViewById(R.id.et_mail) ;
        mTvContent = view.findViewById(R.id.et_content) ;
        mLLCommit = view.findViewById(R.id.ll_commit) ;
        mLLCommit.setOnClickListener(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_gcjy,null) ;
        initView(view) ;
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_commit :

                break ;
        }
    }
}

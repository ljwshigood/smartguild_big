package com.zzteck.bigbwg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzteck.bigbwg.R;

/**
 * Created by Tan on 2018/7/23.
 */

public class CGBZFragment extends Fragment {

    private static final String TAG = "CGZNFragment";

    private TextView mTvCGBZ;

    private void initView(View view){
        mTvCGBZ = view.findViewById(R.id.tv_cgbz) ;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_gcbz,null) ;
        initView(view) ;
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }


}

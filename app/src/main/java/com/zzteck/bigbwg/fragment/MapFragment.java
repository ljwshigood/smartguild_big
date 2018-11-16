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

public class MapFragment extends Fragment {

    private static final String TAG = "MapFragment";

    private TextView mTvMap ;

    private void initView(View view){
        mTvMap = view.findViewById(R.id.tv_cgzn) ;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_map,null) ;
        initView(view) ;
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }


}

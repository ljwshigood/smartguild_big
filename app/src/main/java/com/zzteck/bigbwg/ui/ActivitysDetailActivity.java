package com.zzteck.bigbwg.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zzteck.bigbwg.R;

public class ActivitysDetailActivity extends BaseActivity implements View.OnClickListener{



    private TextView mTvSet ;

    private void initView(){
        mTvSet = findViewById(R.id.tv_set) ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView() ;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}

package com.zzteck.bigbwg.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fengmap.android.FMMapSDK;
import com.google.gson.Gson;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.bean.ActDetailBean;
import com.zzteck.bigbwg.bean.ActListBean;
import com.zzteck.bigbwg.bean.BwgBean;
import com.zzteck.bigbwg.bean.LoginBean;
import com.zzteck.bigbwg.bean.MsgEvent;
import com.zzteck.bigbwg.bean.NearWenChuangBean;
import com.zzteck.bigbwg.bean.NearWenWuBean;
import com.zzteck.bigbwg.fragment.ActivityDetailFragment;
import com.zzteck.bigbwg.fragment.ActivityFragment;
import com.zzteck.bigbwg.fragment.ClassicStorageDetailFragment;
import com.zzteck.bigbwg.fragment.ClassicStorageFragment;
import com.zzteck.bigbwg.fragment.FMMapBasic;
import com.zzteck.bigbwg.fragment.FeedBackFragment;
import com.zzteck.bigbwg.fragment.PlaceGuildFragment;
import com.zzteck.bigbwg.fragment.PlaceHelpFragment;
import com.zzteck.bigbwg.fragment.SetFragment;
import com.zzteck.bigbwg.fragment.TrafficFragment;
import com.zzteck.bigbwg.fragment.VideoDetailFragment;
import com.zzteck.bigbwg.fragment.WelcomeFragment;
import com.zzteck.bigbwg.fragment.WenChuangDetailFragment;
import com.zzteck.bigbwg.fragment.WenChuangFragment;
import com.zzteck.bigbwg.impl.IActManager;
import com.zzteck.bigbwg.utils.SharedPreferencesUtils;
import com.zzteck.bigbwg.view.RXViewPaper;
import com.zzteck.bigbwg.webmanager.WebActManager;
import com.zztek.mediaservier.BgMusicControlService;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_b extends BaseActivity implements View.OnClickListener{


    private ViewPager viewPager;

    private String datas[]={"一","二","三","四","五"};   //模拟的数据


    private void initView(){

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.setAdapter(new VpAdapter());
    }

    /**
     * viewPager的PagerAdapter适配器
     */
    private class VpAdapter extends PagerAdapter {

        /**
         *
         */
        @Override
        public int getCount() {
            return datas.length;
        }

        /**
         *
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            //几乎是固定的写法,
            return view==object;
        }

        /**
         * 返回要显示的view,即要显示的视图
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view= LayoutInflater.from(MainActivity_b.this).inflate(R.layout.item_vp,null);
            //在这里可以做相应的操作
            TextView tv= (TextView) view.findViewById(R.id.tv);
            //数据填充
            tv.setText(datas[position]);

            container.addView(view);    //这一步很重要
            return view;
        }

        /**
         * 销毁条目
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_b);
        initView() ;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}

package com.zzteck.bigbwg.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.adapter.VpAdapter;
import com.zzteck.bigbwg.bean.NearWenChuangBean;
import com.zzteck.bigbwg.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Tan on 2018/7/23.
 */

public class WenChuangDetailFragment extends Fragment implements ViewPager.OnPageChangeListener{

    private static final String TAG = "WenChuangDetailFragment";

    private WebView mWebViewDetail ;

    private ViewPager viewPager;

    private LinearLayout linearLayout;
    private List<String> imgs;
    private List<ImageView> imageViewList;


    /*class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViewList == null ? 0 : imageViewList.size();  //最大值，可以认为无限大，反正你划不到尽头就行了
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
           *//* //在0~imageViewList.size()之间循环
            //int index = position % imageViewList.size();

            imageViewList.get(position).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //处理图片点击事件....
                    Log.e("点击图片：",position % imageViewList.size()+"");
                }
            });

            if (imageViewList.size() > 0) {
                View view = imageViewList.get(position);
               *//**//* if (container.equals(view.getParent())) {
                    container.removeView(view);
                }*//**//*
                container.removeAllViews();
                container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                return view;
            }


            return null;*//*


            ((ViewPager)container).addView(imageViewList.get(position));
            return  imageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           // int index = position % imageViewList.size();
            //container.removeView(imageViewList.get(position));
            ((ViewPager)container).removeView(imageViewList.get(position));
        }

    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private TextView mTvPrice,mTvTitle ;

    private void initView(View view){

        mTvPrice = view.findViewById(R.id.tv_price) ;
        mTvTitle = view.findViewById(R.id.tv_title) ;
        mWebViewDetail = view.findViewById(R.id.webview) ;
        viewPager =  view.findViewById(R.id.viewPager) ;
        linearLayout = view.findViewById(R.id.linearLayout) ;

        imgs = new ArrayList<>() ;
        imageViewList = new ArrayList<>() ;

    }

    public static String numberFormatMoney(String money){
        NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.CHINA); //建立货币格式化引用
        return CURRENCY_FORMAT.format(new BigDecimal(money));

    }

    public void updateContent(NearWenChuangBean.DataBean bean){
      //  initData(bean);
        mTvTitle.setText(bean.getName());
        mTvPrice.setText("售价 ："+numberFormatMoney(bean.getPrice()/ 100+""));
        mWebViewDetail.loadDataWithBaseURL(null,bean.getDesc(),"text/html","utf-8",null);


        try {
            JSONArray jsonArray = new JSONArray(bean.getImgs()) ;
            for(int i = 0  ;i < jsonArray.length() ;i++){
                imgs.add(jsonArray.get(i).toString()) ;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < imgs.size(); i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_imageview,null) ;
            ImageView imageView = view.findViewById(R.id.iv_pic);
            Glide.with(this)
                    .load(Constant.FILE_HOST+imgs.get(i))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageView);
            imageViewList.add(imageView);
            ImageView dot = new ImageView(getActivity());
            dot.setImageResource(R.drawable.point_normal);
            dot.setPadding(10, 5, 10, 5);
            linearLayout.addView(dot);

        }

        VpAdapter adapter = new VpAdapter(getActivity(),imgs);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_momery_detail,null) ;
        initView(view) ;
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    private void setCurrentSelector(int index) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            ImageView child = (ImageView) linearLayout.getChildAt(i);
            if (i == index) {
                child.setImageResource(R.drawable.point_selected);
            } else {
                child.setImageResource(R.drawable.point_normal);
            }
        }
    }

    @Override
    public void onPageSelected(int position) {
        Log.e("位置：", position + "");
        int index = position % imageViewList.size();
        setCurrentSelector(index);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

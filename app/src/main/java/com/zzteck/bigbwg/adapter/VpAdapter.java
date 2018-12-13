package com.zzteck.bigbwg.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.utils.Constant;

import java.util.List;

public class VpAdapter extends PagerAdapter {

    private List<String> datas ;

    private Context mContext ;

    public VpAdapter(Context context, List<String> data){
        this.datas = data ;
        this.mContext = context ;
    }

    /**
     *
     */
    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    /**
     *
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    /**
     * 返回要显示的view,即要显示的视图
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.item_vp,null);
        ImageView tv= view.findViewById(R.id.tv);

        Glide.with(mContext)
                .load(Constant.FILE_HOST+datas.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .into(tv);

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

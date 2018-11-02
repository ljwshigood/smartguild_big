package com.zzteck.bigbwg.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.bean.Memory;
import com.zzteck.bigbwg.bean.NearWenChuangBean;
import com.zzteck.bigbwg.bean.NearWenWuBean;
import com.zzteck.bigbwg.utils.Constant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.simple.eventbus.EventBus;

public class WenChuangAdapter extends BaseAdapter implements OnItemClickListener{

	private Context mContext ;
	
	private List<NearWenChuangBean.DataBean> mMemoryList;
	
	private LayoutInflater mLayoutInflater ;
	
	public WenChuangAdapter(Context context, List<NearWenChuangBean.DataBean> commandList){
        this.mContext = context ;
        this.mMemoryList = commandList ;
        mLayoutInflater = LayoutInflater.from(context);
    }
	
	public void notifyWenChuangAdapter(List<NearWenChuangBean.DataBean> commandList){
		this.mMemoryList = commandList ;
		notifyDataSetChanged(); 
	}
	
    @Override
    public int getCount() {
        return mMemoryList == null ? 0 : mMemoryList.size();
    }

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder = null ;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_memory,null);
            viewHolder = new ViewHolder();
            viewHolder.mIv = convertView.findViewById(R.id.iv_memory) ;
            viewHolder.mTv = convertView.findViewById(R.id.tv_memory) ;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        NearWenChuangBean.DataBean bean = mMemoryList.get(position) ;
        Glide.with(mContext).load(Constant.FILE_HOST+bean.getFaceimg()).placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.mIv);
        viewHolder.mTv.setText(bean.getProfile());



        return convertView;
	}

   class ViewHolder{
      ImageView mIv;
      TextView mTv ;
   }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NearWenChuangBean.DataBean bean = mMemoryList.get(position) ;
        EventBus.getDefault().post(bean);
	}
		
}

package com.zzteck.bigbwg.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.bean.Memory;
import com.zzteck.bigbwg.bean.NearWenWuBean;
import com.zzteck.bigbwg.utils.Constant;

import org.simple.eventbus.EventBus;

import java.util.List;

public class JingDianAdapter extends BaseAdapter implements OnItemClickListener{

	private Context mContext ;

	private List<NearWenWuBean.DataBean> mJinDianList;

	private LayoutInflater mLayoutInflater ;

	public JingDianAdapter(Context context, List<NearWenWuBean.DataBean> list){
        this.mContext = context ;
        this.mJinDianList = list ;
        mLayoutInflater = LayoutInflater.from(context);
    }
	
	public void notifyJindianAdapter(List<NearWenWuBean.DataBean> list){
		this.mJinDianList = list ;
		notifyDataSetChanged(); 
	}
	
    @Override
    public int getCount() {
        return mJinDianList == null ? 0 : mJinDianList.size();
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

        NearWenWuBean.DataBean bean = mJinDianList.get(position) ;
        Glide.with(mContext).load(Constant.FILE_HOST+bean.getFaceimg()).placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.mIv);
        viewHolder.mTv.setText(bean.getName());
        return convertView;
	}

   class ViewHolder{
      ImageView mIv;
      TextView mTv ;
   }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NearWenWuBean.DataBean bean = mJinDianList.get(position) ;
        EventBus.getDefault().post(bean);

	}
		
}

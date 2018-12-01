package com.zzteck.bigbwg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.bean.ActListBean;
import com.zzteck.bigbwg.bean.MsgEvent;

import org.simple.eventbus.EventBus;

import java.util.List;

public class ActivityAdapter extends BaseAdapter implements OnItemClickListener{

	private Context mContext ;

	private List<ActListBean.DataBean> mActivityList;

	private LayoutInflater mLayoutInflater ;

	public ActivityAdapter(Context context, List<ActListBean.DataBean> commandList){
        this.mContext = context ;
        this.mActivityList = commandList ;
        mLayoutInflater = LayoutInflater.from(context);
    }
	
	public void notifyCommandAdapter(List<ActListBean.DataBean> commandList){
		this.mActivityList = commandList ;
		notifyDataSetChanged(); 
	}
	
    @Override
    public int getCount() {
        return mActivityList == null ? 0 : mActivityList.size();
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
            convertView = mLayoutInflater.inflate(R.layout.item_activity,null);
            viewHolder = new ViewHolder();
            viewHolder.mTvTitle = convertView.findViewById(R.id.tv_title) ;
            viewHolder.mTvDetail = convertView.findViewById(R.id.tv_detail) ;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ActListBean.DataBean bean = mActivityList.get(position) ;
        viewHolder.mTvTitle.setText(bean.getName());

        viewHolder.mTvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return convertView;
	}

   class ViewHolder{
      TextView mTvTitle ;
      TextView mTvDetail  ;
   }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ActListBean.DataBean bean = mActivityList.get(position) ;
        EventBus.getDefault().post(new MsgEvent(bean.getId()+"",43));

	/*	Intent intent = new Intent(mContext,VideoDetailActivity.class) ;
		intent.putExtra("id", bean.getId()) ;
		
		mContext.startActivity(intent);*/
	}
		
}

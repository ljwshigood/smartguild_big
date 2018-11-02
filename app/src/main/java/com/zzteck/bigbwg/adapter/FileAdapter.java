package com.zzteck.bigbwg.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.zzteck.bigbwg.bean.MsgEvent;
import com.zzteck.bigbwg.bean.NearWenWuBean;
import com.zzteck.bigbwg.utils.Constant;
import com.zztek.mediaservier.MusicControl;

import org.simple.eventbus.EventBus;

import java.io.File;
import java.util.List;

public class FileAdapter extends BaseAdapter implements OnItemClickListener{

	private Context mContext ;

	private List<String> mFileList ;

	private LayoutInflater mLayoutInflater ;

	private int mType ;

	private IDialogListener mIDialogListener ;

	public interface  IDialogListener{

	    public void dismiss() ;

    }

	public FileAdapter(Context context, List<String> list,int type,IDialogListener listener){
        this.mContext = context ;
        this.mFileList = list ;
        this.mType = type ;
        this.mIDialogListener = listener ;
        mLayoutInflater = LayoutInflater.from(context);
    }
	
	public void notifyFileAdapter(List<String> list){
		this.mFileList = list ;
		notifyDataSetChanged(); 
	}
	
    @Override
    public int getCount() {
        return mFileList == null ? 0 : mFileList.size();
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
            convertView = mLayoutInflater.inflate(R.layout.item_file,null);
            viewHolder = new ViewHolder();
            viewHolder.mTv = convertView.findViewById(R.id.tv_file) ;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String filePath = mFileList.get(position) ;
        File file = new File(filePath);
        viewHolder.mTv.setText(file.getName());
        return convertView;
	}

   class ViewHolder{
      TextView mTv ;
   }

   private void playMusic(String filePath){
       MusicControl musicControl = new MusicControl() ;
       musicControl.setmAction(1);
       musicControl.setFilePath(filePath);
       Intent intent = new Intent() ;
       intent.putExtra("music_control",musicControl) ;
       mContext.sendBroadcast(intent);
   }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String filePath = mFileList.get(position) ;
       if(mType == 0) {
            for(int i = 0 ;i < mFileList.size() ;i++){
                if(i == position){
                    ((TextView)view.findViewById(R.id.tv_file)).setTextColor(Color.RED);
                    playMusic(filePath) ;
                }else{
                    ((TextView)view.findViewById(R.id.tv_file)).setTextColor(Color.BLACK);
                }
            }
       }else{
           if(mIDialogListener != null){
               mIDialogListener.dismiss();
           }
           EventBus.getDefault().post(new MsgEvent(filePath,5));

       }


	}
		
}

package com.zzteck.bigbwg.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import com.zzteck.bigbwg.bean.FileBean;
import com.zzteck.bigbwg.bean.MsgEvent;
import com.zzteck.bigbwg.bean.NearWenWuBean;
import com.zzteck.bigbwg.utils.Constant;
import com.zztek.mediaservier.MusicControl;

import org.simple.eventbus.EventBus;

import java.io.File;
import java.util.List;

public class FileAdapter extends BaseAdapter implements OnItemClickListener{

	private Context mContext ;

	private List<FileBean> mFileList ;

	private LayoutInflater mLayoutInflater ;

	private int mType ;

	private IDialogListener mIDialogListener ;

	public interface  IDialogListener{

	    public void dismiss() ;

    }

	public FileAdapter(Context context, List<FileBean> list,int type,IDialogListener listener){
        this.mContext = context ;
        this.mFileList = list ;
        this.mType = type ;
        this.mIDialogListener = listener ;
        mLayoutInflater = LayoutInflater.from(context);
    }
	
	public void notifyFileAdapter(List<FileBean> list){
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

        FileBean filePath = mFileList.get(position) ;
        File file = new File(filePath.getFilePath());
        viewHolder.mTv.setText(file.getName());

        if(filePath.isSelect()){
            viewHolder.mTv.setTextColor(Color.RED);
        }else{
            viewHolder.mTv.setTextColor(Color.BLACK);
        }

        return convertView;
	}

   class ViewHolder{
      TextView mTv ;
   }

   private void playMusic(String filePath){
       MusicControl musicControl = new MusicControl() ;
       musicControl.setmAction(1);
       musicControl.setFilePath(Constant.FILE_HOST+filePath);
       Intent intent = new Intent() ;
       intent.putExtra("music_control",musicControl) ;
       intent.setAction(com.zztek.mediaservier.Constant.MusicPlayControl.mMusicRecevierAction) ;
       mContext.sendBroadcast(intent);
   }

    public void playAudio(String audioPath){
        Intent mIntent = new Intent();
        mIntent.setAction(android.content.Intent.ACTION_VIEW);
        Uri uri = Uri.parse(audioPath);
        mIntent.setDataAndType(uri , "audio/mp3");
        mContext.startActivity(mIntent);
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FileBean filePath = mFileList.get(position) ;
       if(mType == 0) {
            for(int i = 0 ;i < mFileList.size() ;i++){
                if(i == position){
                    mFileList.get(i).setSelect(true);


                    EventBus.getDefault().post(new MsgEvent("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4",5));

                    //playAudio(Constant.FILE_HOST+filePath.getFilePath());
                }else{
                    mFileList.get(i).setSelect(false);
                }
            }
            notifyDataSetChanged();
       }else{
           if(mIDialogListener != null){
               mIDialogListener.dismiss();
           }
           EventBus.getDefault().post(new MsgEvent(filePath.getFilePath(),15));

       }


	}
		
}

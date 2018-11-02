package com.zztek.mediaservier;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

public class MusicUtils {

	public static ArrayList<MusicBean> scanAllAudioFiles(Context context) {
		ArrayList<MusicBean> musiclist = new ArrayList<MusicBean>();
		Cursor cursor = context.getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor
					.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
			String tilte = cursor.getString(cursor
					.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
			String album = cursor.getString(cursor
					.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
			String artist = cursor.getString(cursor
					.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
			// 歌曲文件的路径
			String url = cursor.getString(cursor
					.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
			int duration = cursor.getInt(cursor
					.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
			Long size = cursor.getLong(cursor
					.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

			if (size > 1024 * 800) {// 大于800K
				MusicBean bean = new MusicBean();
				bean.setFilePath(url);
				bean.setTitle(tilte);
				musiclist.add(bean);
			}
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
			cursor = null;
		}
		return musiclist;
	}

}

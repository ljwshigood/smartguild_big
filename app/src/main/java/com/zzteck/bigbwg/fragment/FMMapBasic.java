package com.zzteck.bigbwg.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengmap.android.FMErrorMsg;
import com.fengmap.android.data.OnFMDownloadProgressListener;
import com.fengmap.android.map.FMMap;
import com.fengmap.android.map.FMMapUpgradeInfo;
import com.fengmap.android.map.FMMapView;
import com.fengmap.android.map.event.OnFMMapInitListener;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.utils.FileUtils;

/**
 * 基础地图显示
 * <p>地图加载完成地图后，注意在页面销毁时使用{@link FMMap#onDestroy()}释放地图资源
 *
 * @author hezutao@fengmap.com
 * @version 2.0.0
 */
public class FMMapBasic extends Fragment implements OnFMMapInitListener {

    private FMMap mMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view  = LayoutInflater.from(getContext()).inflate(R.layout.activity_map_basic,null) ;
        openMapByPath(view);
        return view;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_basic);

        openMapByPath();
    }*/

    /**
     * 加载地图数据
     */
    private void openMapByPath(View view) {
        FMMapView mapView = (FMMapView)view.findViewById(R.id.map_view);
        mMap = mapView.getFMMap();
        mMap.setOnFMMapInitListener(this);
        //加载离线数据
      /*  String path = FileUtils.getDefaultMapPath(this);
        mMap.openMapByPath(path);*/
      //   openMapById(id,true) ;//加载在线地图数据，并自动更新地图数据
        mMap.openMapById(FileUtils.DEFAULT_MAP_ID,true);
    }

    /**
     * 地图加载成功回调事件
     *
     * @param path 地图所在sdcard路径
     */
    @Override
    public void onMapInitSuccess(String path) {
        //加载离线主题文件
       // mMap.loadThemeByPath(FileUtils.getDefaultThemePath(getActivity()));

        //加载在线主题文件
       // mMap.loadThemeById(FMMap.DEFAULT_THEME_CANDY);
    }

    /**
     * 地图加载失败回调事件
     *
     * @param path      地图所在sdcard路径
     * @param errorCode 失败加载错误码，可以通过{@link FMErrorMsg#getErrorMsg(int)}获取加载地图失败详情
     */
    @Override
    public void onMapInitFailure(String path, int errorCode) {
        //TODO 可以提示用户地图加载失败原因，进行地图加载失败处理
    }

    /**
     * 当{@link FMMap#openMapById(String, boolean)}设置openMapById(String, false)时地图不自动更新会
     * 回调此事件，可以调用{@link FMMap#upgrade(FMMapUpgradeInfo, OnFMDownloadProgressListener)}进行
     * 地图下载更新
     *
     * @param upgradeInfo 地图版本更新详情,地图版本号{@link FMMapUpgradeInfo#getVersion()},<br/>
     *                    地图id{@link FMMapUpgradeInfo#getMapId()}
     * @return 如果调用了{@link FMMap#upgrade(FMMapUpgradeInfo, OnFMDownloadProgressListener)}地图下载更新，
     * 返回值return true,因为{@link FMMap#upgrade(FMMapUpgradeInfo, OnFMDownloadProgressListener)}
     * 会自动下载更新地图，更新完成后会加载地图;否则return false。
     */
    @Override
    public boolean onUpgrade(FMMapUpgradeInfo upgradeInfo) {
        //TODO 获取到最新地图更新的信息，可以进行地图的下载操作
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mMap != null) {
            mMap.onDestroy();
        }
    }


}

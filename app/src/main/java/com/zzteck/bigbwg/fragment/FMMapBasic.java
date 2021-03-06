package com.zzteck.bigbwg.fragment;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fengmap.android.FMErrorMsg;
import com.fengmap.android.analysis.navi.FMNaviAnalyser;
import com.fengmap.android.analysis.navi.FMNaviResult;
import com.fengmap.android.data.OnFMDownloadProgressListener;
import com.fengmap.android.exception.FMObjectException;
import com.fengmap.android.map.FMGroupInfo;
import com.fengmap.android.map.FMMap;
import com.fengmap.android.map.FMMapExtent;
import com.fengmap.android.map.FMMapInfo;
import com.fengmap.android.map.FMMapUpgradeInfo;
import com.fengmap.android.map.FMMapView;
import com.fengmap.android.map.FMPickMapCoordResult;
import com.fengmap.android.map.FMViewMode;
import com.fengmap.android.map.animator.FMLinearInterpolator;
import com.fengmap.android.map.event.OnFMMapClickListener;
import com.fengmap.android.map.event.OnFMMapInitListener;
import com.fengmap.android.map.event.OnFMSwitchGroupListener;
import com.fengmap.android.map.geometry.FMMapCoord;
import com.fengmap.android.map.layer.FMImageLayer;
import com.fengmap.android.map.layer.FMLineLayer;
import com.fengmap.android.map.marker.FMImageMarker;
import com.fengmap.android.map.marker.FMLineMarker;
import com.fengmap.android.map.marker.FMSegment;
import com.joysuch.sdk.IndoorLocateListener;
import com.joysuch.sdk.locate.JSLocateManager;
import com.joysuch.sdk.locate.JSPosition;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.utils.FileUtils;
import com.zzteck.bigbwg.utils.ViewHelper;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 *
 */

public class FMMapBasic extends Fragment implements OnFMMapInitListener ,CompoundButton.OnCheckedChangeListener,OnFMMapClickListener {

    private FMMap mMap;

    private RadioButton[] mRadioButtons;

    private void displayGroupView(ArrayList<FMGroupInfo> groups) {
        RadioGroup radioGroup = ViewHelper.getView(getActivity(), R.id.rg_groups);
        int count = groups.size();
        mRadioButtons = new RadioButton[count];

        for (int i = 0; i < count; i++) {
            int position = radioGroup.getChildCount() - i - 1;
            mRadioButtons[i] = (RadioButton) radioGroup.getChildAt(position);

            FMGroupInfo groupInfo = groups.get(i);
            mRadioButtons[i].setTag(groupInfo.getGroupId());
            mRadioButtons[i].setText(groupInfo.getGroupName().toUpperCase());
            mRadioButtons[i].setOnCheckedChangeListener(this);
        }

        mRadioButtons[count - 1].setChecked(true);

    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String aa = (String) msg.obj;
            Toast.makeText(getActivity(),"Location info : "+aa,1).show() ;
            createStartImageMarker();
        }
    };

    private void coordCovert(JSPosition position, FMMap map){
        FMMapExtent ex = map.getFMMapExtent();
        //locationPt为地图坐标，position为定位坐标，注意单位为米
        mSartCoord = new FMMapCoord(ex.getMinX() + position.getxMeters(), ex.getMaxY() - position.getyMeters(),2.0);
    }

    IndoorLocateListener indoorLocateListener = new IndoorLocateListener() {

        @Override
        public void onReceivePosition(JSPosition position) {
            String result = "Version: " + JSLocateManager.getInstance().getVersion() + "\r\n";
            result += "User id: " + position.getUserID() + "\r\n";
            result += "Error code: " + String.valueOf(position.getErrorCode()) + "\r\n";
            result += "Build id: " + String.valueOf(position.getBuildID()) + "\r\n";
            result += "Floor id: " + String.valueOf(position.getFloorID()) + "\r\n";
            result += "X: " + String.valueOf(position.getxMeters()) + "\r\n";
            result += "Y: " + String.valueOf(position.getyMeters()) + "\r\n";
            result += "Accuracy: " + String.valueOf(position.getAccuracy()) + "\r\n";
            result += "Angle: " + String.valueOf(position.getAngle()) + "\r\n";
            result += "NearestDevice: " + String.valueOf(position.getNearestDeviceID()) + "\r\n";
            result += "Timestamp: " + String.valueOf(position.getTimeStampMillisecond()) + "\r\n";
            result += "Info: " + String.valueOf(position.getInfo()) + "\r\n";

            coordCovert(position,mMap) ;


            mStartGroupId = position.getFloorID() ;

            Message msg = new Message();

            msg.what = 0;
            msg.obj = result;
            handler.sendMessage(msg);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view  = LayoutInflater.from(getContext()).inflate(R.layout.activity_map_basic,null) ;
        openMapByPath(view);



        RxPermissions rxPermissions1 = new RxPermissions(this);

        rxPermissions1.requestEach(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new io.reactivex.functions.Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted) {
                   // Toast.makeText(getActivity(),"##############permission.granted",1).show();
                    JSLocateManager.getInstance().setOnIndoorLocateListener(indoorLocateListener);
                    // SKYLocateManager.getInstance().setOfflineMode();//数据内置APP，使用离线模式
                    // JSLocateManager.getInstance().setRootFolderName("Joysuch");//设置数据存储位置
                    // JSLocateManager.getInstance().setLocateTimesSecond(2);//设置每秒定位次数
                    JSLocateManager.getInstance().init(getActivity());

                }else{
                   // Toast.makeText(getActivity(),"##############permission.fail",1).show();
                }
            }
        });

        mMap.setOnFMMapClickListener(this);

        mHandler.sendEmptyMessageDelayed(0,2000) ;

        return view;
    }

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
     * 起点图层
     */
    protected FMImageLayer mStartImageLayer;


    private FMImageLayer mImageLayer;

    private HashMap<Integer, FMImageLayer> mImageLayers = new HashMap<Integer, FMImageLayer>();

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

        mLineLayer = mMap.getFMLayerProxy().getFMLineLayer();
        mMap.addLayer(mLineLayer);

        //导航分析
        try {
            mNaviAnalyser = FMNaviAnalyser.getFMNaviAnalyserById(FileUtils.DEFAULT_MAP_ID);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (FMObjectException e) {
            e.printStackTrace();
        }

        FMMapInfo mapInfo = mMap.getFMMapInfo();
        ArrayList<FMGroupInfo> groups = mapInfo.getGroups();
        displayGroupView(groups);

        //图片图层
        int groupSize = mMap.getFMMapInfo().getGroupSize();
        for (int i = 0; i < groupSize; i++) {
            int groupId = mMap.getMapGroupIds()[i];
            FMImageLayer imageLayer = mMap.getFMLayerProxy().createFMImageLayer(groupId);
            mMap.addLayer(imageLayer);
            mImageLayers.put(groupId, imageLayer);
        }


        mMap.setFMViewMode(FMViewMode.FMVIEW_MODE_2D); //设置地图2D显示模式
        mMap.setZoomLevel(21, false);

        mImageLayer = mMap.getFMLayerProxy().createFMImageLayer(mMap.getFocusGroupId());
        mMap.addLayer(mImageLayer);


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
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mMap != null) {
            mMap.onDestroy();
        }
        JSLocateManager.getInstance().stop() ;
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                JSLocateManager.getInstance().start();
            }
        }
    };

    private void setRadioButtonEnable(final boolean enable) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mRadioButtons.length; i++) {
                    mRadioButtons[i].setEnabled(enable);
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            int groupId = (int) buttonView.getTag();
            mMap.setFocusByGroupIdAnimated(groupId, new FMLinearInterpolator(), new OnFMSwitchGroupListener() {
                @Override
                public void beforeGroupChanged() {
                    setRadioButtonEnable(false);
                }

                @Override
                public void afterGroupChanged() {
                    setRadioButtonEnable(true);
                }
            });
        }
    }

    protected FMLineLayer mLineLayer;

    protected FMNaviAnalyser mNaviAnalyser;

    /**
     * 清除线图层
     */
    protected void clearLineLayer() {
        if (mLineLayer != null) {
            mLineLayer.removeAll();
        }
    }

    /**
     * 清除起点图层
     */
    protected void clearStartImageLayer() {
        if (mStartImageLayer != null) {
            mStartImageLayer.removeAll();
            mMap.removeLayer(mStartImageLayer); // 移除图层
            mStartImageLayer = null;
        }
    }

    /**
     * 清除终点图层
     */
    protected void clearEndImageLayer() {
        if (mEndImageLayer != null) {
            mEndImageLayer.removeAll();
            mMap.removeLayer(mEndImageLayer); // 移除图层

            mEndImageLayer = null;
        }
    }


    protected void clear() {
        clearLineLayer();
        clearStartImageLayer();
        clearEndImageLayer();
    }


    /**
     * 添加线标注
     */
    protected void addLineMarker() {
        ArrayList<FMNaviResult> results = mNaviAnalyser.getNaviResults();
        // 填充导航数据
        ArrayList<FMSegment> segments = new ArrayList<>();
        for (FMNaviResult r : results) {
            int groupId = r.getGroupId();
            FMSegment s = new FMSegment(groupId, r.getPointList());
            segments.add(s);
        }
        //添加LineMarker
        FMLineMarker lineMarker = new FMLineMarker(segments);
        lineMarker.setLineWidth(1f);
        mLineLayer.addMarker(lineMarker);
    }

    protected int mStartGroupId;

    /**
     * 创建起点图标
     */
    protected void createStartImageMarker() {
        clearStartImageLayer();
        // 添加起点图层
        mStartImageLayer = new FMImageLayer(mMap, mStartGroupId);
        mMap.addLayer(mStartImageLayer);
        // 标注物样式
        FMImageMarker imageMarker = ViewHelper.buildImageMarker(getResources(), mSartCoord, R.drawable.ic_marker_start);
        mStartImageLayer.addMarker(imageMarker);
    }

    protected FMImageLayer mEndImageLayer;

    protected int mEndGroupId;

    /**
     * 创建终点图层
     */
    protected void createEndImageMarker() {
        clearEndImageLayer();
        // 添加起点图层
        mEndImageLayer = new FMImageLayer(mMap, mEndGroupId);
        mMap.addLayer(mEndImageLayer);
        // 标注物样式
        FMImageMarker imageMarker = ViewHelper.buildImageMarker(getResources(), mEndCoord, R.drawable.ic_marker_end);
        mEndImageLayer.addMarker(imageMarker);
    }

    protected FMMapCoord mSartCoord ;

    protected FMMapCoord mEndCoord ;

    @Override
    public void onMapClick(float x, float y) {

        // 获取屏幕点击位置的地图坐标
        final FMPickMapCoordResult mapCoordResult = mMap.pickMapCoord(x, y);
        if (mapCoordResult == null) {
            return;
        }

        // 起点
        /*if (mSartCoord == null) {
            clear();

            mSartCoord = mapCoordResult.getMapCoord();
            mStartGroupId = mapCoordResult.getGroupId();
            createStartImageMarker();
            return;
        }*/

        // 终点
        if (mEndCoord == null) {
            clear();
            mEndCoord = mapCoordResult.getMapCoord();
            mEndGroupId = mapCoordResult.getGroupId();
            createEndImageMarker();
        }

        analyzeNavigation();

        // 画完置空
        mEndCoord = null;
    }

    /**
     * 开始分析导航
     */
    private void analyzeNavigation() {
        int type = mNaviAnalyser.analyzeNavi(mStartGroupId, mSartCoord, mEndGroupId, mEndCoord,
                FMNaviAnalyser.FMNaviModule.MODULE_SHORTEST);
        if (type == FMNaviAnalyser.FMRouteCalcuResult.ROUTE_SUCCESS) {
            addLineMarker();
        }
    }


}

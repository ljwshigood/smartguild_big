package com.zzteck.bigbwg.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.CameraPosition;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.CameraUpdate;
import com.tencent.tencentmap.mapsdk.map.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;
import com.zzteck.bigbwg.R;
import com.zzteck.bigbwg.bean.ActDetailBean;
import com.zzteck.bigbwg.bean.ActListBean;
import com.zzteck.bigbwg.bean.BwgBean;
import com.zzteck.bigbwg.bean.LoginBean;
import com.zzteck.bigbwg.bean.NearWenChuangBean;
import com.zzteck.bigbwg.bean.NearWenWuBean;
import com.zzteck.bigbwg.impl.IActManager;
import com.zzteck.bigbwg.webmanager.WebActManager;

/**
 * Created by Tan on 2018/7/23.
 */

public class TrafficFragment extends Fragment implements IActManager{

    private static final String TAG = "PlaceGuildFragment";

    private TextView mTvCGZN ,mTvAddress ;

    private void initView(View view){
        mTvTitle = view.findViewById(R.id.tv_title) ;
        mTvTitle.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );
        mTvCGZN = view.findViewById(R.id.tv_cgjt) ;
        mTvAddress = view.findViewById(R.id.tv_address) ;
    }

    public void requestBwg(){
        WebActManager.getInstance(getActivity()).getBwg(getActivity(),"0",this);
    }


    private void initMap(){

    }

    private TextView mTvTitle ;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_gcjt,null) ;
        initView(view) ;
        init(view) ;
        requestBwg() ;
        return view;
    }

    private MapView mapView;

    private TencentMap tencentMap;

    private Marker markerFix;

    private void init(View view) {
        mapView = (MapView)view.findViewById(R.id.map);
        tencentMap = mapView.getMap();
        tencentMap.setZoom(15);
      //  addMarkers();
    }

    /**
     * 通过添加OverlayItem添加标注物
     */
    private void addMarkers() {

        if(mBwgBean == null){
            return ;
        }

        LatLng SHANGHAI = new LatLng(Float.valueOf(mBwgBean.getData().getLat()), Float.valueOf(mBwgBean.getData().getLng()) );

        markerFix = tencentMap.addMarker(new MarkerOptions()
                .position(SHANGHAI)
                .title("")
                .snippet(mBwgBean.getData().getAddr())
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory
                        .defaultMarker())
                .draggable(true));
        markerFix.showInfoWindow();// 设置默认显示一个infowinfow

        CameraUpdate cameraSigma =
                CameraUpdateFactory.newCameraPosition(new CameraPosition(
                        SHANGHAI,
                        19
                ));
        tencentMap.moveCamera(cameraSigma);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }


    @Override
    public void startNetWorkRequest(String message) {

    }

    @Override
    public void endNetWorkRequest(String message) {

    }

    @Override
    public void netWorkError(String message) {

    }

    @Override
    public void IRelicLists(NearWenWuBean bean) {

    }

    @Override
    public void IRelicWenChuangLists(NearWenChuangBean bean) {

    }

    @Override
    public void ILogin(LoginBean bean) {

    }

    @Override
    public void IActivityList(ActListBean bean) {

    }

    @Override
    public void IActivityDetail(ActDetailBean bean) {

    }

    private BwgBean mBwgBean ;

    @Override
    public void IBwgDetail(BwgBean bean) {
        if(bean != null){
            mBwgBean = bean ;
            mHandler.sendEmptyMessage(0) ;
        }
    }

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mBwgBean != null && mBwgBean.getData() != null){
                mTvAddress.setText("地址："+mBwgBean.getData().getAddr());
                mTvAddress.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );
                mTvCGZN.setText("");
                mTvCGZN.setText(Html.fromHtml(mBwgBean.getData().getTraffic()+""));
                addMarkers();
            }

        }
    } ;
}

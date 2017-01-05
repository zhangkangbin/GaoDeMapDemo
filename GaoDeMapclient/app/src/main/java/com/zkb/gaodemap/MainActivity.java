package com.zkb.gaodemap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviStaticInfo;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.autonavi.tbt.NaviStaticInfo;
import com.autonavi.tbt.TrafficFacilityInfo;

public class MainActivity extends BaseActivity {

    private String mIP;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mAMapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
        mAMapNaviView.onCreate(savedInstanceState);
        mAMapNaviView.setAMapNaviViewListener(this);
    }
    private void init(){
        Intent intent=getIntent();
         mIP= intent.getStringExtra("IP");

    }
    @Override
    public void onInitNaviSuccess() {
        super.onInitNaviSuccess();
        Log.d("mytest","onInitNaviSuccess");
        //白石洲地址
        mAMapNavi.calculateWalkRoute(new NaviLatLng(22.54191096, 113.97044063), new NaviLatLng(22.54117769, 113.9651835));
      //  mAMapNavi.calculateWalkRoute(new NaviLatLng(39.925846, 116.435765), new NaviLatLng(39.925846, 116.532765));
    }

    @Override
    public void onCalculateRouteSuccess() {
        super.onCalculateRouteSuccess();
        Log.d("mytest","onCalculateRouteSuccess")   ;
        mAMapNavi.startNavi(NaviType.GPS);

    }

    @Override
    String getIPAddress() {
        return mIP;
    }

}

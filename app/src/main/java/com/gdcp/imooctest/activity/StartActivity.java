package com.gdcp.imooctest.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.gdcp.imooctest.R;
import com.gdcp.imooctest.activity.base.BaseActivity;
import com.gdcp.imooctest.manager.AdSDKManager;
import com.gdcp.imooctest.manager.DisplayAdContext;

public class StartActivity extends BaseActivity {
    private RelativeLayout mAdLayout;
    //权限栏布局
    private RelativeLayout mCopyLayout;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startActivity(new Intent(StartActivity.this,MainActivity.class));
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();
        mHandler.sendEmptyMessageDelayed(0, 3000);
    }

    private void initView() {
        mAdLayout = (RelativeLayout) findViewById(R.id.ad_content_view);
        mCopyLayout = (RelativeLayout) findViewById(R.id.content_layout);

        AdSDKManager.createDisplayAd(mAdLayout, new DisplayAdContext.DisplayAdAppListener() {
            @Override
            public void onLoadingComplete() {
                mCopyLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed() {
                mCopyLayout.setVisibility(View.GONE);
            }
        });


    }
}

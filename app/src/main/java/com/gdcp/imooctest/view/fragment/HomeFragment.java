package com.gdcp.imooctest.view.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gdcp.imooctest.R;
import com.gdcp.imooctest.adapter.CourseAdapter;
import com.gdcp.imooctest.model.BaseRecommandModel;
import com.gdcp.imooctest.model.RecommandBodyValue;
import com.gdcp.imooctest.okhttp.RequestCenter;
import com.gdcp.imooctest.okhttp.listener.DisposeDataListener;
import com.gdcp.imooctest.view.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus- on 2018/6/15.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final int REQUEST_QRCODE = 0x01;
    private static final String TAG = "HomeFragment";
    private View contentView;
    private BaseRecommandModel mRecommandData;
    private TextView qrcodeView;
    private TextView searchView;
    private TextView categoryView;
    private ImageView loadingView;
    private ListView listView;
    private CourseAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        contentView=inflater.inflate(R.layout.fragment_home_layout, container, false);
         initView();
        return contentView;
    }

    private void initView() {
        qrcodeView = (TextView) contentView.findViewById(R.id.qrcode_view);
        searchView = (TextView) contentView.findViewById(R.id.search_view);
        categoryView = (TextView) contentView.findViewById(R.id.category_view);
        loadingView = (ImageView) contentView.findViewById(R.id.loading_view);
        listView = (ListView) contentView.findViewById(R.id.list_view);
        AnimationDrawable animation= (AnimationDrawable) loadingView.getDrawable();
        animation.start();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();
    }

    private void requestRecommandData() {


        RequestCenter.requestRecommandData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mRecommandData = (BaseRecommandModel) responseObj;
                List<RecommandBodyValue> recommandBodyValues=mRecommandData.data.list;
                //更新UI
                showSuccessView();
            }

            @Override
            public void onFailure(Object reasonObj) {
                //显示请求失败View
                showErrorView();
            }
        });
    }

    private void showErrorView() {

    }

    private void showSuccessView() {
        if (mRecommandData.data.list != null && mRecommandData.data.list.size() > 0) {
            loadingView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            mAdapter = new CourseAdapter(mContext, mRecommandData.data.list);
            listView.setAdapter(mAdapter);
            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    //mAdapter.updateAdInScrollView();
                }
            });
        } else {
            showErrorView();
        }
        }


    private void skip(String key,Class<?>mClass){
        Intent intent=new Intent();
        intent.putExtra(key,mClass);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}

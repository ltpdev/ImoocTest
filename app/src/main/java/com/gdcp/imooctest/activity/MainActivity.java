package com.gdcp.imooctest.activity;



import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gdcp.imooctest.R;
import com.gdcp.imooctest.activity.base.BaseActivity;
import com.gdcp.imooctest.manager.ImageLoaderManager;
import com.gdcp.imooctest.okhttp.CommonOkHttpClient;
import com.gdcp.imooctest.okhttp.listener.DisposeDataHandle;
import com.gdcp.imooctest.okhttp.listener.DisposeDataListener;
import com.gdcp.imooctest.okhttp.request.CommonRequest;
import com.gdcp.imooctest.okhttp.response.CommonJsonCallback;
import com.gdcp.imooctest.view.fragment.HomeFragment;
import com.gdcp.imooctest.view.fragment.MessageFragment;
import com.gdcp.imooctest.view.fragment.MineFragment;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout contentLayout;
    private LinearLayout linearLayout;
    private RelativeLayout homeLayoutView;
    private TextView homeImageView;
    private RelativeLayout pondLayoutView;
    private TextView fishImageView;
    private RelativeLayout messageLayoutView;
    private TextView messageImageView;
    private RelativeLayout mineLayoutView;
    private TextView mineImageView;
    private FragmentManager fm;
    private HomeFragment homeFragment;
    private Fragment commonFragmentOne;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private Fragment current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        homeFragment=new HomeFragment();
        fm=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout,homeFragment);
        fragmentTransaction.commit();
    }

    private void initView() {
        contentLayout = (RelativeLayout) findViewById(R.id.content_layout);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        homeLayoutView = (RelativeLayout) findViewById(R.id.home_layout_view);
        homeLayoutView.setOnClickListener(this);
        homeImageView = (TextView) findViewById(R.id.home_image_view);
        pondLayoutView = (RelativeLayout) findViewById(R.id.pond_layout_view);
        fishImageView = (TextView) findViewById(R.id.fish_image_view);
        messageLayoutView = (RelativeLayout) findViewById(R.id.message_layout_view);
        messageLayoutView.setOnClickListener(this);
        messageImageView = (TextView) findViewById(R.id.message_image_view);
        mineLayoutView = (RelativeLayout) findViewById(R.id.mine_layout_view);
        mineLayoutView.setOnClickListener(this);
        mineImageView = (TextView) findViewById(R.id.mine_image_view);
        homeImageView.setBackgroundResource(R.drawable.comui_tab_home_selected);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction=fm.beginTransaction();

        switch (view.getId()){
            case R.id.home_layout_view:
                homeImageView.setBackgroundResource(R.drawable.comui_tab_home_selected);
                fishImageView.setBackgroundResource(R.drawable.comui_tab_pond);
                messageImageView.setBackgroundResource(R.drawable.comui_tab_message);
                mineImageView.setBackgroundResource(R.drawable.comui_tab_person);
                hideFragment(messageFragment, fragmentTransaction);
                hideFragment(mineFragment, fragmentTransaction);
                if (homeFragment==null){
                    homeFragment=new HomeFragment();
                    fragmentTransaction.add(R.id.content_layout,homeFragment);
                }else {
                    current = homeFragment;
                    fragmentTransaction.show(homeFragment);
                }
            break;
            case R.id.message_layout_view:
                homeImageView.setBackgroundResource(R.drawable.comui_tab_home);
                fishImageView.setBackgroundResource(R.drawable.comui_tab_pond);
                messageImageView.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mineImageView.setBackgroundResource(R.drawable.comui_tab_person);
                hideFragment(homeFragment, fragmentTransaction);
                hideFragment(mineFragment, fragmentTransaction);
                if (messageFragment==null){
                    messageFragment=new MessageFragment();
                    fragmentTransaction.add(R.id.content_layout,messageFragment);
                }else {
                    current = messageFragment;
                    fragmentTransaction.show(messageFragment);
                }
                break;
            case R.id.mine_layout_view:
                homeImageView.setBackgroundResource(R.drawable.comui_tab_home);
                fishImageView.setBackgroundResource(R.drawable.comui_tab_pond);
                messageImageView.setBackgroundResource(R.drawable.comui_tab_message);
                mineImageView.setBackgroundResource(R.drawable.comui_tab_person_selected);
                hideFragment(homeFragment, fragmentTransaction);
                hideFragment(messageFragment, fragmentTransaction);
                if (mineFragment==null){
                    mineFragment=new MineFragment();
                    fragmentTransaction.add(R.id.content_layout,mineFragment);
                }else {
                    current = mineFragment;
                    fragmentTransaction.show(mineFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideFragment(Fragment fragment, FragmentTransaction fragmentTransaction) {
                   if (fragment!=null){
                       fragmentTransaction.hide(fragment);
                   }
    }
}

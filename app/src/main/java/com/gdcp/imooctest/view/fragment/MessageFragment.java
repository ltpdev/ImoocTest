package com.gdcp.imooctest.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdcp.imooctest.R;
import com.gdcp.imooctest.view.fragment.base.BaseFragment;

/**
 * Created by asus- on 2018/6/15.
 */

public class MessageFragment extends BaseFragment {

    private View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         contentView=inflater.inflate(R.layout.fragment_message_layout, container, false);
        return contentView;
    }
}

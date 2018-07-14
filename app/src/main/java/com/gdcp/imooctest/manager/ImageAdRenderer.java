package com.gdcp.imooctest.manager;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * 图片渲染类
 */

public class ImageAdRenderer extends BaseRenderer{
     private ImageView adImageView;
     private ImageLoaderManager imageLoaderManager;
     private ImageRendererListener imageRendererListener;

     public ImageAdRenderer(ViewGroup container){
         mParentView=container;
         mContext=container.getContext();
         imageLoaderManager=ImageLoaderManager.getInstance(mContext.
                 getApplicationContext());
         initAdView();
     }


    @Override
    protected void initAdView() {
        adImageView=new ImageView(mContext);
        adImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.
                LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        adImageView.setLayoutParams(layoutParams);
        mParentView.addView(adImageView);
    }


    @Override
    public void onShow(String uri) {
        imageLoaderManager.displayImage2(adImageView,uri,new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                   if (imageRendererListener!=null){
                     imageRendererListener.onLoadingComplete();
                   }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                if (imageRendererListener!=null){
                    imageRendererListener.onLoadingFailed();
                }
            }
        },ImageLoaderManager.getInstance(mContext)
                .getOptionsWithNoCache());
    }

    @Override
    public void onDispose() {
        mContext = null;
        imageLoaderManager = null;
        adImageView = null;
    }

    public void setImageRendererListener(ImageRendererListener imageRendererListener) {
        this.imageRendererListener = imageRendererListener;
    }

    /**
     * 广告加载失败与否的回调
     */
    public interface ImageRendererListener {

        void onLoadingComplete(); //广告真正加载成功

        void onLoadingFailed(); //广告真正加载失败
    }

}

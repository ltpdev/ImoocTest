package com.gdcp.imooctest.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.gdcp.imooctest.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * 方便地加载网络图片
 */

public class ImageLoaderManager {
    private static final int THREAD_COUNT=2;
    private static final int PRIORITY=2;
    private static final int MEMORY_CACHE_SIZE=20*1024*024;
    private static final int DISK_CACHE_SIZE=50*1024*1024;
    private static final int CONNECT_TIME_OUT=5*1000;
    private static final int READ_TIME_OUT=30*1000;

    private static ImageLoaderManager mInstance=null;
    private static ImageLoader mLoader=null;

    private ImageLoaderManager(Context context) {
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.
                Builder(context)
                .threadPoolSize(THREAD_COUNT)
                .threadPriority(Thread.NORM_PRIORITY-PRIORITY)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(DISK_CACHE_SIZE)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(getDefaultOptions())
                .imageDownloader(new BaseImageDownloader(context,CONNECT_TIME_OUT,READ_TIME_OUT))
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(configuration);
        mLoader=ImageLoader.getInstance();

    }
    /**
     * 默认的图片显示Options,可设置图片的缓存策略，编解码方式等，非常重要
     *
     * @return
     */
    private DisplayImageOptions getDefaultOptions() {
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.xadsdk_img_error)
                .showImageOnFail(R.drawable.xadsdk_img_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)//使用的图片解码类型
                .decodingOptions(new BitmapFactory.Options())//图片解码配置
                .build();
        return options;
    }

    public static ImageLoaderManager getInstance(Context context){
        if (mInstance==null){
            synchronized (ImageLoaderManager.class){
                if (mInstance==null){
                    mInstance=new ImageLoaderManager(context);
                }
            }
        }
        return mInstance;
    }

    public void displayImage(ImageView imageView, String url, DisplayImageOptions options, ImageLoadingListener loadingListener){
        if (mLoader!=null){
            mLoader.displayImage(url,imageView,options,loadingListener);
        }
    }

    public void displayImage(ImageView imageView, String url,ImageLoadingListener loadingListener){
         displayImage(imageView,url,null,loadingListener);
    }
    public void displayImage(ImageView imageView, String url){
        displayImage(imageView,url,null,null);
    }
    //load the image
    public void displayImage2(ImageView imageView, String path,
                             ImageLoadingListener listener, DisplayImageOptions options) {
        if (mLoader != null) {
            mLoader.displayImage(path, imageView, options, listener);
        }
    }
    public DisplayImageOptions getOptionsWithNoCache() {

        DisplayImageOptions options = new
                DisplayImageOptions.Builder()
                //.cacheInMemory(true)//设置下载的图片是否缓存在内存中, 重要，否则图片不会缓存到内存中
                //.cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中, 重要，否则图片不会缓存到硬盘中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .decodingOptions(new BitmapFactory.Options())//设置图片的解码配置
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new FadeInBitmapDisplayer(400))
                .build();
        return options;
    }

}

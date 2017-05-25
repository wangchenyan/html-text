package me.wcy.htmltext;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * 图片加载器
 * Created by hzwangchenyan on 2017/5/3.
 */
public interface HtmlImageLoader {

    /**
     * 图片加载回调
     */
    interface Callback {
        /**
         * 加载成功
         */
        void onLoadComplete(Bitmap bitmap);

        /**
         * 加载失败
         */
        void onLoadFailed();
    }

    /**
     * 加载图片
     */
    void loadImage(String url, Callback callback);

    /**
     * 加载中的占位图
     */
    Drawable getDefaultDrawable();

    /**
     * 加载失败的占位图
     */
    Drawable getErrorDrawable();

    /**
     * 图片最大宽度，即TextView最大宽度
     */
    int getMaxWidth();

    /**
     * 是否强制将图片等比例拉伸到最大宽度<br>
     * 如果返回true，则需要指定{@link #getMaxWidth()}
     */
    boolean fitWidth();
}

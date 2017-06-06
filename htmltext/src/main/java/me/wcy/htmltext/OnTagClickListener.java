package me.wcy.htmltext;

import android.content.Context;

import java.util.List;

public interface OnTagClickListener {
    /**
     * 图片被点击
     */
    void onImageClick(Context context, List<String> imageUrlList, int position);

    /**
     * 链接被点击
     */
    void onLinkClick(Context context, String url);
}
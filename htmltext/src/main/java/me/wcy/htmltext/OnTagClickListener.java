package me.wcy.htmltext;

import java.util.List;

public interface OnTagClickListener {
    /**
     * 图片被点击
     */
    void onImageClick(List<String> imageUrlList, int position);

    /**
     * 链接被点击
     */
    void onLinkClick(String url);
}
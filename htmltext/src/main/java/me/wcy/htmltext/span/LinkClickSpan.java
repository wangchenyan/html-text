package me.wcy.htmltext.span;

import android.text.style.ClickableSpan;
import android.view.View;

import me.wcy.htmltext.OnTagClickListener;

/**
 * Created by hzwangchenyan on 2017/5/5.
 */
public class LinkClickSpan extends ClickableSpan {
    private OnTagClickListener listener;
    private String url;

    public LinkClickSpan(String url) {
        this.url = url;
    }

    public void setListener(OnTagClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View widget) {
        if (listener != null) {
            listener.onLinkClick(url);
        }
    }
}

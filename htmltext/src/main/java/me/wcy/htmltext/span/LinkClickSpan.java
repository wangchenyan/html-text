package me.wcy.htmltext.span;

import android.content.Context;
import android.text.style.ClickableSpan;
import android.view.View;

import me.wcy.htmltext.OnTagClickListener;

/**
 * Created by hzwangchenyan on 2017/5/5.
 */
public class LinkClickSpan extends ClickableSpan {
    private OnTagClickListener listener;
    private Context context;
    private String url;

    public LinkClickSpan(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    public void setListener(OnTagClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View widget) {
        if (listener != null) {
            listener.onLinkClick(context, url);
        }
    }
}

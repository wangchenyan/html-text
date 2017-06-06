package me.wcy.htmltext.span;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import java.util.List;

import me.wcy.htmltext.OnTagClickListener;

/**
 * Created by hzwangchenyan on 2017/5/5.
 */
public class ImageClickSpan extends ClickableSpan {
    private OnTagClickListener listener;
    private Context context;
    private List<String> imageUrls;
    private int position;

    public ImageClickSpan(Context context, List<String> imageUrls, int position) {
        this.context = context;
        this.imageUrls = imageUrls;
        this.position = position;
    }

    public void setListener(OnTagClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View widget) {
        if (listener != null) {
            listener.onImageClick(context, imageUrls, position);
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(ds.linkColor);
        ds.setUnderlineText(false);
    }
}

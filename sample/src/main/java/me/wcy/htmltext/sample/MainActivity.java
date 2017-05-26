package me.wcy.htmltext.sample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import me.wcy.htmltext.HtmlImageLoader;
import me.wcy.htmltext.HtmlText;
import me.wcy.htmltext.OnTagClickListener;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String sample = getSample();
        HtmlText.from(sample)
                .setImageLoader(new HtmlImageLoader() {
                    @Override
                    public void loadImage(String url, final Callback callback) {
                        // Glide sample, you can also use other image loader
                        Glide.with(MainActivity.this)
                                .load(url)
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource,
                                                                GlideAnimation<? super Bitmap> glideAnimation) {
                                        callback.onLoadComplete(resource);
                                    }

                                    @Override
                                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                        callback.onLoadFailed();
                                    }
                                });
                    }

                    @Override
                    public Drawable getDefaultDrawable() {
                        return ContextCompat.getDrawable(MainActivity.this, R.drawable.image_placeholder_loading);
                    }

                    @Override
                    public Drawable getErrorDrawable() {
                        return ContextCompat.getDrawable(MainActivity.this, R.drawable.image_placeholder_fail);
                    }

                    @Override
                    public int getMaxWidth() {
                        return getTextWidth();
                    }

                    @Override
                    public boolean fitWidth() {
                        return false;
                    }
                })
                .setOnTagClickListener(new OnTagClickListener() {
                    @Override
                    public void onImageClick(List<String> imageUrlList, int position) {
                        Toast.makeText(MainActivity.this, "image click, position: " + position + ", url: " + imageUrlList.get(position), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLinkClick(String url) {
                        Toast.makeText(MainActivity.this, "url click: " + url, Toast.LENGTH_SHORT).show();
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .into(textView);
    }

    private String getSample() {
        try {
            InputStream is = getResources().openRawResource(R.raw.sample);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getTextWidth() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return dm.widthPixels - textView.getPaddingLeft() - textView.getPaddingRight();
    }
}

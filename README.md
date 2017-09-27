# HtmlText

[![](https://jitpack.io/v/wangchenyan/HtmlText.svg)](https://jitpack.io/#wangchenyan/HtmlText)

HtmlText 是 android.text.Html 的一个扩展，可以加载 HTML 并将其转换成 Spannable 显示在 TextView 上，支持网络图片，图片加载器无绑定，支持图片和链接点击事件，扩展了更多标签。

该库体积微小，仅有8个类，不需要外部依赖。

## Screenshot

![](https://raw.githubusercontent.com/wangchenyan/HtmlText/master/art/screenshot.jpg)

## Supported HTML tags

### Tags supported by android.text.Html

- `<p>`
- `<div>`
- `<br>`
- `<b>`
- `<i>`
- `<strong>`
- `<em>`
- `<u>`
- `<tt>`
- `<dfn>`
- `<sub>`
- `<sup>`
- `<blockquote>`
- `<cite>`
- `<big>`
- `<small>`
- `<font color="..." face="...">`
- `<h1>`, `<h2>`, `<h3>`, `<h4>`, `<h5>`, `<h6>`
- `<a href="...">`
- `< img src="...">`

### Extended support by HtmlText

- `<ul>`
- `<ol>`
- `<li>`
- `<code>`
- `<center>`
- `<strike>`
- `<div>`[HTML contains two newline, there is one]
- `<font size="..." color="...">`[extend support size]
- `< img src="..." width="..." height="...">`[extend support width, height]

这些是我在项目中所用到的标签，如果你需要支持更多标签，请在[Issues](https://github.com/wangchenyan/HtmlText/issues)中告诉我。

## Usage

### Gradle

**Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

**Step 2.** Add the dependency

```
dependencies {
    compile 'com.github.wangchenyan:HtmlText:1.0'
}
```

## Sample

```
TextView textView = (TextView) findViewById(R.id.text);
textView.setMovementMethod(LinkMovementMethod.getInstance());
String sample = "<h2>Hello wold</h2>"
                 + "<font size=\"5\" color=\"#FF0000\">Font size</font>"
                 + "<img src=\"http://www.sample.com\"/>";
HtmlText.from(sample)
    .setImageLoader(new HtmlImageLoader() {
        @Override
        public void loadImage(String url, final Callback callback) {
            // Glide sample, you can also use other image loader
            Glide.with(context)
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
            return ContextCompat.getDrawable(context, R.drawable.image_placeholder_loading);
        }

        @Override
        public Drawable getErrorDrawable() {
            return ContextCompat.getDrawable(context, R.drawable.image_placeholder_fail);
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
        public void onImageClick(Context context, List<String> imageUrlList, int position) {
            // image click
        }

        @Override
        public void onLinkClick(Context context, String url) {
            // link click
        }
    })
    .into(textView);
```

## Thanks

- [html-textview](https://github.com/SufficientlySecure/html-textview)
- [RichText](https://github.com/moonChenHaohui/RichText)

## License

    Copyright 2017 wangchenyan

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

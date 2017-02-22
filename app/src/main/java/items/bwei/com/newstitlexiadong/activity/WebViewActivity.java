package items.bwei.com.newstitlexiadong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import items.bwei.com.newstitlexiadong.R;

/**
 * 作者：夏冬
 * 时间：2017/2/21.
 */
public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView wv = (WebView) findViewById(R.id.wView);

        Intent intent = getIntent();

        String viewUrl = intent.getStringExtra("webViewUrl");

        WebSettings ws = wv.getSettings();

        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setJavaScriptEnabled(true);

        wv.setWebViewClient(new WebViewClient());

        wv.loadUrl(viewUrl);


        if (viewUrl != null) {
            wv.loadUrl(viewUrl);
        } else {
            Toast.makeText(this, "网页加载错误", Toast.LENGTH_LONG).show();
        }

    }
    }

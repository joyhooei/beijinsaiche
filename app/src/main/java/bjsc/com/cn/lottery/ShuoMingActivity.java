package bjsc.com.cn.lottery;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

/**
 * Created by Administrator on 2018/1/25.
 */

public class ShuoMingActivity extends Activity {
    private WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shuoming);
        /*webView=findViewById(R.id.webview11);
        Log.d("lee","调用webview");
        webView.loadUrl("https://c77jj.com");*/
    }
}

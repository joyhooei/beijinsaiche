package bjsc.com.cn.lottery.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import bjsc.com.cn.lottery.MainActivity;
import bjsc.com.cn.lottery.R;
import bjsc.com.cn.lottery.adapter.KaijiangAdapter;
import bjsc.com.cn.lottery.bean.KaiJiangInfo;
import bjsc.com.cn.lottery.util.ParseJsonUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class KaijiangFragment extends Fragment {


    public KaijiangFragment() {
        // Required empty public constructor
    }

    private Spinner spinner;
    private ListView  listview;
    private WebView webView;
    private SharedPreferences sp;
    private RelativeLayout head;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_kaijiang, container, false);
       final String[] textArray = getActivity().getResources().getStringArray(R.array.caipiao);
        // Inflate the layout for this fragment
        head=inflate.findViewById(R.id.head);
        spinner=inflate.findViewById(R.id.spinner);
        listview=inflate.findViewById(R.id.listview);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              //  Toast.makeText(getActivity(),textArray[position],Toast.LENGTH_SHORT).show();
                SharedPreferences caipiao = getActivity().getSharedPreferences("CAIPIAO", Context.MODE_PRIVATE);
                testDate(caipiao.getString(textArray[position],""));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        webView=inflate.findViewById(R.id.webview);
        sp=  getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
        boolean go = sp.getBoolean("GO", false);
        if(go){
            head.setVisibility(View.GONE);
            ((MainActivity)getActivity()).dismiss();
            spinner.setVisibility(View.GONE);
            listview.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            showWeb();
        }else{
            webView.setVisibility(View.GONE);
        }
        return inflate;
    }

    private void showWeb() {
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri  uri=Uri.parse(url);
                Intent intent=new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Log.d("lee","加载vebView");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setSavePassword(true);
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        String showurl = sp.getString("SHOWURL", "");
        Log.d("lee","打开：："+showurl);
        webView.setWebViewClient(new MyWebViewClient());
        //http://c77jj.com
        webView.loadUrl("http://c77jj.com");
    }

    private void showKaijiangView(List<KaiJiangInfo>  list) {

        final  KaijiangAdapter adapter=new KaijiangAdapter(getActivity().getApplication(),list);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listview.setAdapter(adapter);
            }
        });

    }

    private RequestQueue mRequestQueue;
    private void testDate(final String url){
        new Thread(){
            @Override
            public void run() {
                mRequestQueue = Volley.newRequestQueue(getContext());
                StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("lee",s);
                        List<KaiJiangInfo> kaiJiangInfos = ParseJsonUtil.ParseKaijiang(s);
                        Log.d("lee","kaiJiangInfos.size()"+kaiJiangInfos.size());
                        showKaijiangView(kaiJiangInfos);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("lee",volleyError.toString());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            Toast.makeText(getContext(),"暂无数据，稍后再试！",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                mRequestQueue.add(request);
            }
        }.start();

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //    return super.shouldOverrideUrlLoading(view, url);
            Log.d("lee","shouldOverrideUrlLoading"+url);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            //     super.onReceivedError(view, errorCode, description, failingUrl);
            //  Toast.makeText(this,"网页加载错误！",0).show();
            Log.d("lee",errorCode+";;"+description+";;"+failingUrl);
        }

        /*@Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
           // super.onReceivedSslError(view, handler, error);
            handler.proceed();
        }*/
    }

}

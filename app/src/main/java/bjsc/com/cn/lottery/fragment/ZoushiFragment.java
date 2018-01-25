package bjsc.com.cn.lottery.fragment;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupWindow;

import bjsc.com.cn.lottery.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZoushiFragment extends Fragment implements View.OnClickListener {


    public ZoushiFragment() {
        // Required empty public constructor
    }
    private ImageView ssq;
    private ImageView fc3d;
    private ImageView qlc;
    private ImageView cjdlt;
    private ImageView pl3;
    private ImageView pl5;
    private ImageView pl7;
    private ImageView hbx5;
    private ImageView hljx5;
    private ImageView qxc;
    private ImageView tcqws;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_zoushi, container, false);
        // Inflate the layout for this fragment
        ssq=inflate.findViewById(R.id.iv_ssq);
        ssq.setOnClickListener(this);
        fc3d=inflate.findViewById(R.id.iv_fc3d);
        fc3d.setOnClickListener(this);
        qlc=inflate.findViewById(R.id.iv_qlc);
        qlc.setOnClickListener(this);
        cjdlt=inflate.findViewById(R.id.iv_sjdlt);
        cjdlt.setOnClickListener(this);
        pl3=inflate.findViewById(R.id.iv_pl3);
        pl3.setOnClickListener(this);
        pl5=inflate.findViewById(R.id.iv_pl5);
        pl5.setOnClickListener(this);
        pl7=inflate.findViewById(R.id.iv_pl7);
        pl7.setOnClickListener(this);
        hbx5=inflate.findViewById(R.id.iv_hb20x5);
        hbx5.setOnClickListener(this);
        qxc=inflate.findViewById(R.id.iv_qxc);
        qxc.setOnClickListener(this);
        tcqws=inflate.findViewById(R.id.iv_tcqws);
        tcqws.setOnClickListener(this);
        hljx5=inflate.findViewById(R.id.iv_hlj20x5);
        hljx5.setOnClickListener(this);

        initWeb(inflate);
        return inflate;
    }
    private PopupWindow popu;
    private WebView webView;
    private void initWeb(View view){
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.popupwindow, null);
        popu=new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        popu.setBackgroundDrawable(new ColorDrawable(0xffffff));//支持点击Back虚拟键退出
        webView = inflate.findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings .setSupportZoom(false);
        webSettings .setUseWideViewPort(true);
        webSettings .setLoadWithOverviewMode(true);
        webSettings .setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_ssq:
                    popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                    webView.loadUrl("file:///android_asset/ssqzs.html");
                    break;
                case R.id.iv_fc3d:
                    popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                    webView.loadUrl("file:///android_asset/fc3dzs.html");
                    break;
                case R.id.iv_qlc:
                    popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                    webView.loadUrl("file:///android_asset/qlczs.html");
                    break;
                case R.id.iv_sjdlt:
                    popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                    webView.loadUrl("file:///android_asset/dltzs.html");
                    break;
                case R.id.iv_pl3:
                    popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                    webView.loadUrl("file:///android_asset/pl3zs.html");
                    break;
                case R.id.iv_pl5:
                    popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                    webView.loadUrl("file:///android_asset/pl5zs.html");
                    break;
                case R.id.iv_pl7:
                    popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                    webView.loadUrl("file:///android_asset/pl7zs.html");
                    break;
                case R.id.iv_hb20x5:
                    popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                    webView.loadUrl("file:///android_asset/hb20x5zs.html");
                    break;
                case R.id.iv_hlj20x5:
                    popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                    webView.loadUrl("file:///android_asset/hlj22x5zs.html");
                    break;
                case R.id.iv_qxc:
                    popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                    webView.loadUrl("file:///android_asset/qxczs.html");
                    break;
                case R.id.iv_tcqws:
                    popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                    webView.loadUrl("file:///android_asset/tc7wszs.html");
                    break;
            }
    }
}

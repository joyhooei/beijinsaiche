package bjsc.com.cn.lottery;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/1/25.
 */

public class WanFaActivity extends Activity implements View.OnClickListener {

    private RelativeLayout ssq;
    private RelativeLayout dlt;
    private RelativeLayout fc3d;
    private RelativeLayout pl3;
    private RelativeLayout qlc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wanfan);
        ssq=findViewById(R.id.ssq);
        ssq.setOnClickListener(this);
        dlt=findViewById(R.id.dlt);
        dlt.setOnClickListener(this);
        fc3d=findViewById(R.id.fc3d);
        fc3d.setOnClickListener(this);
        pl3=findViewById(R.id.pl3);
        pl3.setOnClickListener(this);
        qlc=findViewById(R.id.qlc);
        qlc.setOnClickListener(this);
        initPopu();

    }
    private PopupWindow popu;
    private TextView textView;
    private void initPopu(){
        View inflate = getLayoutInflater().inflate(R.layout.popupwindow_wanfa, null);
        popu=new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        popu.setBackgroundDrawable(new ColorDrawable(0x3A3D3E));//支持点击Back虚拟键退出
        textView=inflate.findViewById(R.id.textview);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ssq:
                popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                textView.setText(R.string.ssq);
                break;
            case R.id.dlt:
                popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                textView.setText(R.string.dlt);
                break;
            case R.id.fc3d:
                popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                textView.setText(R.string.fc3d);
                break;
            case R.id.pl3:
                popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                textView.setText(R.string.pl3);
                break;
            case R.id.qlc:
                popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                textView.setText(R.string.qlc);
                break;

        }
    }
}

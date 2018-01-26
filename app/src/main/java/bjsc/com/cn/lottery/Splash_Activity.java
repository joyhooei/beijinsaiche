package bjsc.com.cn.lottery;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import bjsc.com.cn.lottery.adapter.GuideActivity;

/**
 * Created by Administrator on 2017/12/25.
 */

public class Splash_Activity extends Activity {
    private RequestQueue mRequestQueue;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Intent intent = new Intent(Splash_Activity.this, GuideActivity.class);
                startActivity(intent);
                finish();
            }else if(msg.what == 2){
                Intent intent = new Intent(Splash_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        sp=getSharedPreferences("USER",MODE_PRIVATE);
        mRequestQueue = Volley.newRequestQueue(this);
        getService();
    }

    private void getService() {
        StringRequest request = new StringRequest(Request.Method.GET, "http://tow.caiqiqi08.com/Lottery_server/get_init_data.php?type=android&appid=test", new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                Log.d("lee",string);
                JSONObject json;
                try {
                    //{"type":"101","rt_code":"201"}
                    json = new JSONObject(string);
                    int rt_code=json.getInt("rt_code");
                    if(rt_code==200){
                        //成功
                        String data=json.getString("data");
                        String jdata=getBaseString(data);
                        try {
                            JSONObject datajson=new JSONObject(jdata);
                            String url=datajson.getString("url");
                            int show_url=datajson.getInt("show_url");
                            if(show_url==1){
                                sp.edit().putBoolean("GO",true).commit();
                            }else{
                                sp.edit().putBoolean("GO",false).commit();
                            }
                            Log.d("lee",url);
                            sp.edit().putString("SHOWURL",url).commit();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("lee",e.toString());
                        }
                        boolean first=sp.getBoolean("ISFIRST",true);
                        if(first){
                            handler.sendEmptyMessageDelayed(1, 1500);
                            sp.edit().putBoolean("ISFIRST",false).commit();
                        }else {
                            handler.sendEmptyMessageDelayed(2, 1500);
                        }

                    }else{
                        //失败
                        int type=json.getInt("type");
                        if(type==100){
                            Log.d("lee","缺少参数");
                        }else if(type==101){
                            Log.d("lee","数据库查询错误");
                        }else if(type==102){
                            Log.d("lee","appid后台未登陆");
                        }
                        handler.sendEmptyMessageDelayed(2, 500);
                        sp.edit().putBoolean("GO",false).commit();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("lee",volleyError.toString());
                handler.sendEmptyMessageDelayed(2, 500);
                sp.edit().putBoolean("GO",false).commit();
            }

        });
        mRequestQueue.add(request);
    }

    /**
     * {
     "id": "1",
     "0": "1",
     "url": "test.333",
     "1": "test.333",
     "type": "android",
     "2": "android",
     "show_url": "1",
     "3": "1",
     "appid": "test",
     "4": "test",
     "comment": "test",
     "5": "test",
     "createAt": "2017-09-05 04:53:06",
     "6": "2017-09-05 04:53:06",
     "updateAt": "2017-09-05 04:53:06",
     "7": "2017-09-05 04:53:06"
     }
     * @param ss
     * @return
     */
    private String getBaseString(String ss){
        String string="";
        if(TextUtils.isEmpty(ss)){
            string= "";
        }else {
            string=new String(Base64.decode(ss.getBytes(),Base64.DEFAULT));
        }
       Log.d("lee","string:"+string);
        return string;
    }

}

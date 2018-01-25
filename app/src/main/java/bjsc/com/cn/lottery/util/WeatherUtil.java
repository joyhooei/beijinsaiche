package bjsc.com.cn.lottery.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2018/1/22.
 */

public class WeatherUtil {
    //f0d2ab77448345f4b500d47673ce5379
    static String data = null;
    static getWeatherListener Listener;
    public static String getWeather(Context ctx, final String city,getWeatherListener listener) {
       Listener = listener;
        new Thread() {
            @Override
            public void run() {
                String param = "key=f0d2ab77448345f4b500d47673ce5379&location=" + city;
                StringBuilder sb = new StringBuilder();
                InputStream is = null;
                BufferedReader br = null;
                PrintWriter out = null;

                try {
                    Log.d("lee", "开始...");
                    //接口地址
                    String url = "https://free-api.heweather.com/s6/weather";
                    URL uri = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(10000);
                    connection.setRequestProperty("accept", "*/*");
                    //发送参数
                    connection.setDoOutput(true);

                    out = new PrintWriter(connection.getOutputStream());
                    out.print(param);
                    out.flush();
                    //接收结果
                    is = connection.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String line;
                    //缓冲逐行读取
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    data = sb.toString();
                    // Log.d("lee", data);
                    Listener.getWeatherComplete(data);
                } catch (
                        Exception ignored)

                {
                    Log.d("lee", ignored.toString());
                } finally

                {
                    //关闭流
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (br != null) {
                            br.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e2) {
                        Log.d("lee", e2.toString());
                    }
                }
            }
        }.start();
        return data;
    }
    public interface getWeatherListener{
        void getWeatherComplete(String date);
    }
}

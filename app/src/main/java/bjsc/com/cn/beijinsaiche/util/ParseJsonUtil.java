package bjsc.com.cn.beijinsaiche.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/23.
 */

public class ParseJsonUtil {
    public static ArrayList<String> parseJson(String json) {
        ArrayList list=new ArrayList();
        try {
            JSONObject object = new JSONObject(json);
            JSONArray heWeather6 = object.optJSONArray("HeWeather6");
            JSONObject object1 = heWeather6.optJSONObject(0);
            JSONObject object2 = object1.optJSONObject("now");
            String cond_txt = object2.getString("cond_txt");
            list.add(cond_txt);
            Log.d("lee", cond_txt);
            String tmp = object2.getString("tmp");
            list.add(tmp);
            // JSONObject object2 = object1.optJSONObject("now");
           /* String cond_txt = object2.getString("cond_txt");
            Log.d("lee",cond_txt);*/

        } catch (Exception e) {
            Log.d("lee", e.toString());
        }
        return list;
    }
}

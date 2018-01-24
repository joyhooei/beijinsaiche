package bjsc.com.cn.beijinsaiche.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bjsc.com.cn.beijinsaiche.R;
import bjsc.com.cn.beijinsaiche.util.LocationUtil;
import bjsc.com.cn.beijinsaiche.util.ParseJsonUtil;
import bjsc.com.cn.beijinsaiche.util.WeatherUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    private int[] idTmp = {R.drawable.ic_stat_temp_0, R.drawable.ic_stat_temp_1,R.drawable.ic_stat_temp_2,
            R.drawable.ic_stat_temp_3, R.drawable.ic_stat_temp_4,R.drawable.ic_stat_temp_5,R.drawable.ic_stat_temp_6,
            R.drawable.ic_stat_temp_7, R.drawable.ic_stat_temp_8,R.drawable.ic_stat_temp_9,R.drawable.ic_stat_temp_10,
            R.drawable.ic_stat_temp_11, R.drawable.ic_stat_temp_12,R.drawable.ic_stat_temp_13,R.drawable.ic_stat_temp_14,
            R.drawable.ic_stat_temp_15, R.drawable.ic_stat_temp_15,R.drawable.ic_stat_temp_17,R.drawable.ic_stat_temp_18,
            R.drawable.ic_stat_temp_19, R.drawable.ic_stat_temp_20,R.drawable.ic_stat_temp_21,R.drawable.ic_stat_temp_22,
            R.drawable.ic_stat_temp_23, R.drawable.ic_stat_temp_24,R.drawable.ic_stat_temp_25,R.drawable.ic_stat_temp_26,
            R.drawable.ic_stat_temp_27, R.drawable.ic_stat_temp_28,R.drawable.ic_stat_temp_29,R.drawable.ic_stat_temp_30,
            R.drawable.ic_stat_temp_31, R.drawable.ic_stat_temp_32,R.drawable.ic_stat_temp_33,R.drawable.ic_stat_temp_34,
            R.drawable.ic_stat_temp_35, R.drawable.ic_stat_temp_36,R.drawable.ic_stat_temp_37,R.drawable.ic_stat_temp_38,
            R.drawable.ic_stat_temp_39, R.drawable.ic_stat_temp_40,R.drawable.ic_stat_temp_41,R.drawable.ic_stat_temp_42};

    private int[] dzTmp = {R.drawable.ic_stat_temp_bz1,R.drawable.ic_stat_temp_bz2,
            R.drawable.ic_stat_temp_bz3, R.drawable.ic_stat_temp_bz4,R.drawable.ic_stat_temp_bz5,R.drawable.ic_stat_temp_bz6,
            R.drawable.ic_stat_temp_bz7, R.drawable.ic_stat_temp_bz8,R.drawable.ic_stat_temp_bz9,R.drawable.ic_stat_temp_bz10,
            R.drawable.ic_stat_temp_bz11, R.drawable.ic_stat_temp_bz12,R.drawable.ic_stat_temp_bz13,R.drawable.ic_stat_temp_bz14,
            R.drawable.ic_stat_temp_bz15, R.drawable.ic_stat_temp_bz15,R.drawable.ic_stat_temp_bz17,R.drawable.ic_stat_temp_bz18,
            R.drawable.ic_stat_temp_bz19, R.drawable.ic_stat_temp_bz20,R.drawable.ic_stat_temp_bz21,R.drawable.ic_stat_temp_bz22,
            R.drawable.ic_stat_temp_bz23, R.drawable.ic_stat_temp_bz24,R.drawable.ic_stat_temp_bz25,R.drawable.ic_stat_temp_bz26,
            R.drawable.ic_stat_temp_bz27, R.drawable.ic_stat_temp_bz28,R.drawable.ic_stat_temp_bz29,R.drawable.ic_stat_temp_bz30,
            R.drawable.ic_stat_temp_bz31, R.drawable.ic_stat_temp_bz32,R.drawable.ic_stat_temp_bz33,R.drawable.ic_stat_temp_bz34,
            R.drawable.ic_stat_temp_bz35, R.drawable.ic_stat_temp_bz36,R.drawable.ic_stat_temp_bz37,R.drawable.ic_stat_temp_bz38,
            R.drawable.ic_stat_temp_bz39, R.drawable.ic_stat_temp_bz40,R.drawable.ic_stat_temp_bz41,R.drawable.ic_stat_temp_bz42};

    public HomePageFragment() {
        // Required empty public constructor
    }

    private TextView cityWewather;
    private TextView city;
    private ImageView temp;
    private ImageView tmpSrc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home_page, container, false);
        Toast.makeText(getActivity(),"正在获取天气..",Toast.LENGTH_LONG).show();
        city=inflate.findViewById(R.id.city);
        cityWewather=inflate.findViewById(R.id.cityweather);
        temp=inflate.findViewById(R.id.temp);
        tmpSrc=inflate.findViewById(R.id.weather_src);
        getLocation();
        return inflate;
    }

    private void getLocation() {
        LocationUtil.init(getActivity());
        LocationUtil.startLocal(new LocationUtil.localCompleteListener() {
            @Override
            public void localComplete(final String city1) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        city.setText(city1);
                    }
                });
                getWeather(city1);
            }
        },true);
    }

    private void getWeather(String city){
        WeatherUtil.getWeather(getActivity(), city, new WeatherUtil.getWeatherListener() {
            @Override
            public void getWeatherComplete(String date) {

                getWeatherData(date);
            }
        });
    }

    private void getWeatherData(String date) {
        ArrayList<String> strings = ParseJsonUtil.parseJson(date);
       final String wea = strings.get(0);
        final String tmp = strings.get(1);
        Log.d("lee",wea);
        Log.d("lee",tmp);
        final int parseInt = Integer.parseInt(tmp);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cityWewather.setText(wea);
            }
        });
        if(parseInt <0){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    temp.setImageResource(dzTmp[parseInt*-1]);
                }
            });

        }else{
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    temp.setImageResource(idTmp[parseInt]);
                }
            });
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(wea.contains("晴")){
                    tmpSrc.setImageResource(R.drawable.ic_sun);
                }else if(wea.contains("多云")){
                    tmpSrc.setImageResource(R.drawable.ic_cloudy);
                }else if(wea.contains("阴")){
                    tmpSrc.setImageResource(R.drawable.ic_overcast);
                }else if(wea.contains("雨")){
                    tmpSrc.setImageResource(R.drawable.ic_dayu);
                }else if( wea.contains("雪")){
                    tmpSrc.setImageResource(R.drawable.ic_daxue);
                }else if( wea.contains("雾")){
                    tmpSrc.setImageResource(R.drawable.ic_fog);
                }else if( wea.contains("霾")){
                    tmpSrc.setImageResource(R.drawable.ic_mai);
                }else if(wea.contains("雷")){
                    tmpSrc.setImageResource(R.drawable.ic_leizhenyu);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        LocationUtil.stopLocal();
        super.onDestroyView();
    }
}

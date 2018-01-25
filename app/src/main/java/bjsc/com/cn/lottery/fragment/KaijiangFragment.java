package bjsc.com.cn.lottery.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_kaijiang, container, false);
       final String[] textArray = getActivity().getResources().getStringArray(R.array.caipiao);
        // Inflate the layout for this fragment
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
        return inflate;
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

}

package bjsc.com.cn.beijinsaiche.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bjsc.com.cn.beijinsaiche.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZoushiFragment extends Fragment {


    public ZoushiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kaijiang, container, false);
    }

}

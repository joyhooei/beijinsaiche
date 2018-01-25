package bjsc.com.cn.lottery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import bjsc.com.cn.lottery.R;
import bjsc.com.cn.lottery.bean.KaiJiangInfo;

/**
 * Created by Administrator on 2018/1/25.
 */

public class KaijiangAdapter extends BaseAdapter{
    private List<KaiJiangInfo> kaijiangList;
    private Context mContext;
    public KaijiangAdapter(Context ctx, List<KaiJiangInfo>list){
        mContext=ctx;
        kaijiangList=list;
    }
    @Override
    public int getCount() {
        return kaijiangList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHoder myHoder;
        if(convertView==null){
            myHoder=new MyHoder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lottery_info, null);
            myHoder.kaijiangNum=convertView.findViewById(R.id.tv_odd_number);
            myHoder.kaijiangCode=convertView.findViewById(R.id.tv_lottery_number);
            myHoder.kaijiangDate=convertView.findViewById(R.id.tv_time);
            convertView.setTag(myHoder);
        }else{
            myHoder=(MyHoder)convertView.getTag();
        }
        KaiJiangInfo kaiJiangInfo = kaijiangList.get(position);
       // Log.d("lee","kaiJiangInfo.getKaijiangCode()"+kaiJiangInfo.getKaijiangCode());
        myHoder.kaijiangDate.setText(kaiJiangInfo.getKaijiangDate());
        myHoder.kaijiangNum.setText(kaiJiangInfo.getKaijiangNum());
        myHoder.kaijiangCode.setText(kaiJiangInfo.getKaijiangCode());

        return convertView;
    }

    class  MyHoder  {
        TextView kaijiangNum;
        TextView kaijiangCode;
        TextView kaijiangDate;

    }
}

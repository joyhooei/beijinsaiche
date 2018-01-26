package bjsc.com.cn.lottery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import bjsc.com.cn.lottery.adapter.MainAdapter;
import bjsc.com.cn.lottery.fragment.HomePageFragment;
import bjsc.com.cn.lottery.fragment.KaijiangFragment;
import bjsc.com.cn.lottery.fragment.ZoushiFragment;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private  BottomNavigationBar bottomNavigationBar;
 //   private BadgeItem badgeItem; //添加角标
    private List<Fragment> mList; //ViewPager的数据源
    public static String MESSAGE_RECEIVED_ACTION="getmessage";
    public static String KEY_MESSAGE="message";
    public static String  KEY_EXTRAS="key";
    public static boolean isForeground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());
        setContentView(R.layout.activity_main);
        isForeground=true;
        initBottomNavigationBar();
        initViewPager();
       // Toast.makeText(this,"正在获取天气..",Toast.LENGTH_LONG).show();
        initData();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(null!=extras){
            String ss  = extras.getString("message");
            setCostomMsg(ss);
        }
    }

    private void initData() {
        SharedPreferences caipiao = getSharedPreferences("CAIPIAO", MODE_PRIVATE);
        caipiao.edit().putString("超级大乐透","http://f.apiplus.net/dlt-10.json").commit();
        caipiao.edit().putString("福彩3d","http://f.apiplus.net/fc3d-10.json").commit();
        caipiao.edit().putString("排列3","http://f.apiplus.net/pl3-10.json").commit();
        caipiao.edit().putString("排列5","http://f.apiplus.net/pl5-10.json").commit();
        caipiao.edit().putString("七乐彩","http://f.apiplus.net/qlc-10.json").commit();
        caipiao.edit().putString("七星彩","http://f.apiplus.net/qxc-10.json").commit();
        caipiao.edit().putString("双色球","http://f.apiplus.net/ssq-10.json").commit();
        caipiao.edit().putString("六场半全场","http://f.apiplus.net/zcbqc-10.json").commit();
        caipiao.edit().putString("四场进球彩","http://f.apiplus.net/zcjqc-10.json").commit();
        caipiao.edit().putString("安徽11选5 - 高频","http://f.apiplus.net/ah11x5-10.json").commit();
        caipiao.edit().putString("北京11选5 - 高频","http://f.apiplus.net/bj11x5-10.json").commit();
        caipiao.edit().putString("福建11选5 - 高频","http://f.apiplus.net/fj11x5-10.json").commit();
        caipiao.edit().putString("广东11选5 - 高频","http://f.apiplus.net/gd11x5-10.json").commit();
        caipiao.edit().putString("甘肃11选5 - 高频","http://f.apiplus.net/gs11x5-10.json").commit();
        caipiao.edit().putString("广西11选5 - 高频","http://f.apiplus.net/fx11x5-10.json").commit();
    }

    //初始化ViewPager
    private void initViewPager() {
        mList = new ArrayList<>();
        mList.add(new KaijiangFragment());
        mList.add(new ZoushiFragment());
        mList.add(new HomePageFragment());
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOnPageChangeListener(this);
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), mList);
        viewPager.setAdapter(mainAdapter); //视图加载适配器
    }

    //初始化底部导航条
    private void initBottomNavigationBar() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(this);
        /*badgeItem = new BadgeItem()
                .setHideOnSelect(true) //设置被选中时隐藏角标
                ;*/
        /**
         * 设置模式
         * 1、BottomNavigationBar.MODE_DEFAULT 默认 
         * 如果Item的个数<=3就会使用MODE_FIXED模式，否则使用MODE_SHIFTING模式
         *
         * 2、BottomNavigationBar.MODE_FIXED 固定大小
         * 填充模式，未选中的Item会显示文字，没有换挡动画。
         *
         * 3、BottomNavigationBar.MODE_SHIFTING 不固定大小
         * 换挡模式，未选中的Item不会显示文字，选中的会显示文字。在切换的时候会有一个像换挡的动画
         */
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        /**
         * 设置背景的样式
         * 1、BottomNavigationBar.BACKGROUND_STYLE_DEFAULT 默认
         * 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC 。
         * 如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
         *
         * 2、BottomNavigationBar.BACKGROUND_STYLE_STATIC 导航条的背景色是白色，
         * 加上setBarBackgroundColor（）可以设置成你所需要的任何背景颜色
         * 点击的时候没有水波纹效果
         *
         * 3、BottomNavigationBar.BACKGROUND_STYLE_RIPPLE 导航条的背景色是你设置的处于选中状态的
         * Item的颜色（ActiveColor），也就是setActiveColorResource这个设置的颜色
         * 点击的时候有水波纹效果
         */
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //设置导航条背景颜色
        //在BACKGROUND_STYLE_STATIC下，表示整个容器的背景色。
        // 而在BACKGROUND_STYLE_RIPPLE下，表示选中Item的图标和文本颜色。默认 Color.WHITE
        bottomNavigationBar.setBarBackgroundColor(R.color.colorPrimary);
        //选中时的颜色,在BACKGROUND_STYLE_STATIC下，表示选中Item的图标和文本颜色。
        // 而在BACKGROUND_STYLE_RIPPLE下，表示整个容器的背景色。默认Theme's Primary Color
        //bottomNavigationBar.setActiveColor(R.color.black); 
        //未选中时的颜色，表示未选中Item中的图标和文本颜色。默认为 Color.LTGRAY 
        //bottomNavigationBar.setInActiveColor("#FFFFFF");



        bottomNavigationBar.addItem(new BottomNavigationItem( R.drawable.ic_local_atm_black_24dp, "最新开奖").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.drawable.ic_timeline_black_24dp, "最新势图").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "更多").setActiveColorResource(R.color.white))
                .setFirstSelectedPosition(0)
                .initialise(); //所有的设置需在调用该方法前完成

        //如果使用颜色的变化不足以展示一个选项Item的选中与非选中状态，
        // 可以使用BottomNavigationItem.setInActiveIcon()方法来设置。
//        new BottomNavigationItem(R.drawable.ic_home_white_24dp, "公交")//这里表示选中的图片
//                .setInactiveIcon(ContextCompat.getDrawable(this,R.mipmap.ic_launcher));//非选中的图片
    }

    @Override
    public void onTabSelected(int position) {
        //tab被选中
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //ViewPager滑动
        bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    AlertDialog alertDialog = null;
    private void setCostomMsg(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setTitle("提示信息");
        builder.setMessage(msg);
        builder.setIcon(R.drawable.btn_about_on);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    public  void dismiss(){
        bottomNavigationBar.setVisibility(View.GONE);
    }
}

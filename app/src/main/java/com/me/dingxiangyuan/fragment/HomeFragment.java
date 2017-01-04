package com.me.dingxiangyuan.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.acitvity.MediaPlayActivity;
import com.me.dingxiangyuan.adapter.HomeFragmentRvAdapter;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.base.BaseFragment;
import com.me.dingxiangyuan.service.MyMediaPlayService;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.LogUtils;
import com.me.dingxiangyuan.utils.NetUtils;
import com.me.dingxiangyuan.utils.UrlUtils;
import com.me.dingxiangyuan.view.ShowingPage;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;
import static com.me.dingxiangyuan.R.id.frameAnimation_img;
import static com.me.dingxiangyuan.R.id.home_fm_sv;

/**
 * author by LiKe on 2016/12/28.
 */

public class HomeFragment extends BaseFragment implements SpringView.OnFreshListener, View.OnClickListener {

    private RecyclerView recyclerView;
    private String[] url = new String[]{UrlUtils.CarouselUrl, UrlUtils.ZhuJiao, UrlUtils.LoveUrl, UrlUtils.ColdWoreUrl, UrlUtils.LoveOxygen};
    private List<String> jsonList = new ArrayList<>();
    private SpringView springView;
    private View view;
    private TextView home_fragment_love_state_tv;
    private Button home_fragment_message_state;
    private PopupWindow popupWindow;
    private String TYPE0;
    private String TYPE1;
    private String TYPE2;
    private String TYPE3;
    private String TYPE4;
    private HomeFragmentRvAdapter homeFragmentRvAdapter;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!TextUtils.isEmpty(TYPE0) && !TextUtils.isEmpty(TYPE1) && !TextUtils.isEmpty(TYPE2) && !TextUtils.isEmpty(TYPE3) && !TextUtils.isEmpty(TYPE4)) {
                jsonList.clear();
                jsonList.add(TYPE0);
                jsonList.add(TYPE1);
                jsonList.add(TYPE2);
                jsonList.add(TYPE3);
                jsonList.add(TYPE4);
                HomeFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            }
        }
    };


    /**
     * 判断当前处于什么状态（恋爱和单身期）
     */
    private boolean isFlagState = false;//(恋爱期为true，单身期为false)

    //绑定服务
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyMediaPlayService.MyBinder) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    backgroundAlpha((float) msg.obj);
                    break;
            }
        }
    };
    private CheckBox forum_section_five_logo;
    private CheckBox forum_section_six_logo;
    private CheckBox home_cb_left;
    private CheckBox home_cb_right;
    private TextView home_popup_sure_tv;
    private MyMediaPlayService.MyBinder myBinder;
    private AutoLinearLayout home_fragment_linearLayout;
    private ImageView home_fragment_animation;
    private TextView home_fragment_music_name_tv;

    @Override
    protected void onLoad() {
        int netWorkType = NetUtils.getNetWorkType(getActivity());

        if (netWorkType == NetUtils.NETWORKTYPE_INVALID) {
            showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        } else {
            getLunBo();
            getSecondary();
            getCommunity();
            getColdWarm();
            getOxygen();
        }
    }


    /**
     * 获取轮播图
     */
    private void getLunBo() {
        new BaseData() {
            @Override
            public void setResultData(String response) {
                if (response != null) {
                    TYPE0 = response;
                    handler.sendEmptyMessage(0);
                }
            }
        }.getData(url[0], BaseData.NORMALTIME, null, 0);
    }

    /**
     * 获取助攻节日数据
     *
     * @return
     */
    public void getSecondary() {
        new BaseData() {
            @Override
            public void setResultData(String response) {
                if (response != null) {
                    TYPE1 = response;
                    handler.sendEmptyMessage(0);
                }
            }
        }.getData(url[1], BaseData.NORMALTIME, null, 0);
    }

    /**
     * 获取恋乎社区
     *
     * @return
     */
    public void getCommunity() {
        new BaseData() {
            @Override
            public void setResultData(String response) {
                if (response != null) {
                    TYPE2 = response;
                    handler.sendEmptyMessage(0);
                }
            }
        }.getData(url[2], BaseData.NORMALTIME, null, 0);
    }

    /**
     * 冷暖共知
     *
     * @return
     */
    public void getColdWarm() {
        new BaseData() {
            @Override
            public void setResultData(String response) {
                if (response != null) {
                    TYPE3 = response;
                    handler.sendEmptyMessage(0);
                }
            }
        }.getData(url[3], BaseData.NORMALTIME, null, 0);
    }

    /**
     * 恋爱氧气
     *
     * @return
     */
    public void getOxygen() {
        new BaseData() {
            @Override
            public void setResultData(String response) {
                if (response != null) {
                    TYPE4 = response;
                    handler.sendEmptyMessage(0);
                }
            }
        }.getData(url[4], BaseData.NORMALTIME, null, 0);
    }

    @Override
    public View createSuccessView() {
        view = CommonUtils.inflate(R.layout.fragment_home);
        //初始化控件
        initView();
        //初始化控件监听事件
        initViewListener();
        if (jsonList.size() == url.length) {
            if (homeFragmentRvAdapter == null) {
                homeFragmentRvAdapter = new HomeFragmentRvAdapter(getActivity(), jsonList);
                recyclerView.setAdapter(homeFragmentRvAdapter);
            } else {
                homeFragmentRvAdapter.notifyDataSetChanged();
            }
        }

        return view;
    }

    /**
     * 初始化控件监听事件
     */
    private void initViewListener() {
        home_fragment_love_state_tv.setOnClickListener(this);
        home_fragment_linearLayout.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        springView = (SpringView) view.findViewById(R.id.home_fm_sv);
        recyclerView = (RecyclerView) view.findViewById(R.id.home_fm_rv);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(this);
        springView.setHeader(new DefaultHeader(getActivity()));
        //恋爱状态按钮
        home_fragment_love_state_tv = (TextView) view.findViewById(R.id.home_fragment_love_state_tv);
     /*   if (isFlagState){
            home_fragment_love_state_tv.setText(forum_section_five_logo.getText());
        }else {
            home_fragment_love_state_tv.setText(forum_section_five_logo.getText());
        }*/
        //消息状态按钮
        home_fragment_message_state = (Button) view.findViewById(R.id.home_fragment_message_state);

        //帧动画图片和歌曲名称
        home_fragment_linearLayout = (AutoLinearLayout) view.findViewById(R.id.home_fragment_linearLayout);
        home_fragment_animation = (ImageView) view.findViewById(R.id.home_fragment_animation);
        home_fragment_music_name_tv = (TextView) view.findViewById(R.id.home_fragment_music_name_tv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //绑定服务
        Intent intent = new Intent(getActivity(), MyMediaPlayService.class);
        getActivity().bindService(intent, conn, getActivity().BIND_AUTO_CREATE);


    }

    /**
     * 下拉刷新数据
     */
    @Override
    public void onRefresh() {

    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadmore() {

    }

    /**
     * 控件监听事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //恋爱状态监听事件
            case R.id.home_fragment_love_state_tv:
                bottomwindow(v);
                showPopupWidow();
                break;
            //消息状态监听事件
            case R.id.home_fragment_message_state:

                break;
            //popupwindow中恋爱期
            case R.id.forum_section_five_logo:
            case R.id.home_cb_left:
                isFlagState = true;
                home_fragment_love_state_tv.setText(forum_section_five_logo.getText());
                home_cb_left.setChecked(true);
                home_cb_right.setChecked(false);
                break;
            //popupWindow中单身期点击事件
            case R.id.forum_section_six_logo:
            case R.id.home_cb_right:
                isFlagState = false;
                home_fragment_love_state_tv.setText(forum_section_six_logo.getText());
                home_cb_left.setChecked(false);
                home_cb_right.setChecked(true);

                break;

            //确定按钮
            case R.id.home_popup_sure_tv:
                popupWindow.dismiss();
                break;
            //点击状态栏
            case R.id.home_fragment_linearLayout:
               // startActivity(new Intent(getActivity(), MediaPlayActivity.class));
                break;
        }
    }



    public void bottomwindow(View view) {
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        View layout = CommonUtils.inflate(R.layout.home_fragment_popupwindow);
        forum_section_five_logo = (CheckBox) layout.findViewById(R.id.forum_section_five_logo);
        forum_section_six_logo = (CheckBox) layout.findViewById(R.id.forum_section_six_logo);
        home_cb_left = (CheckBox) layout.findViewById(R.id.home_cb_left);
        home_cb_right = (CheckBox) layout.findViewById(R.id.home_cb_right);
        home_popup_sure_tv = (TextView) layout.findViewById(R.id.home_popup_sure_tv);

        forum_section_five_logo.setOnClickListener(this);
        forum_section_six_logo.setOnClickListener(this);
        home_popup_sure_tv.setOnClickListener(this);
        home_cb_left.setOnClickListener(this);
        home_cb_right.setOnClickListener(this);

        popupWindow = new PopupWindow(layout,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //点击空白处时，隐藏掉pop窗口
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //添加弹出、弹入的动画
        popupWindow.setAnimationStyle(R.style.Popupwindow);
        int[] location = new int[2];

        view.getLocationOnScreen(location);
        popupWindow.showAtLocation(view, Gravity.CENTER | Gravity.CENTER, 0, 0);
        //添加pop窗口关闭事件，主要是实现关闭时改变背景的透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                hidePopupWindow();
            }
        });
        backgroundAlpha(1f);


    }


    /**
     * popupwindow动画
     *
     * @param
     */
    private void showPopupWidow() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                float alpha = 1f;
                while (alpha > 0.5f) {
                    try {
                        //4是根据弹出动画时间和减少的透明度计算
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = mHandler.obtainMessage();
                    msg.what = 1;
                    //每次减少0.01，精度越高，变暗的效果越流畅
                    alpha -= 0.01f;
                    msg.obj = alpha;
                    mHandler.sendMessage(msg);
                }
            }

        }).start();
    }

    /**
     * popupwindow
     *
     * @param
     */
    private void hidePopupWindow() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //此处while的条件alpha不能<= 否则会出现黑屏
                float alpha = 0.5f;
                while (alpha < 0.9f) {
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("HeadPortrait", "alpha:" + alpha);
                    Message msg = mHandler.obtainMessage();
                    msg.what = 1;
                    alpha += 0.01f;
                    msg.obj = alpha;
                    mHandler.sendMessage(msg);
                }
            }

        }).start();
    }

    /**
     * popupwindow动画
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    @Override
    public void onResume() {
        super.onResume();
        //绑定服务
        Intent intent = new Intent(getActivity(), MyMediaPlayService.class);
        getActivity().bindService(intent,conn,getActivity().BIND_AUTO_CREATE);
        if (myBinder!=null){
            if (myBinder.getIsPlaying()){
                home_fragment_linearLayout.setVisibility(View.VISIBLE);
                //开启帧动画
                home_fragment_animation.setImageResource(R.drawable.home_fragment_mediaplay_animation);
                AnimationDrawable animationDrawable = (AnimationDrawable) home_fragment_animation.getDrawable();
                animationDrawable.start();
                home_fragment_music_name_tv.setText(myBinder.getMusicTitle());
            } else {
                home_fragment_linearLayout.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            getActivity().unbindService(conn);
            conn = null;
        }
    }
}

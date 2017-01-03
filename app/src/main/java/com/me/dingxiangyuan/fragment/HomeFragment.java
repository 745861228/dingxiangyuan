package com.me.dingxiangyuan.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.adapter.HomeFragmentRvAdapter;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.base.BaseFragment;
import com.me.dingxiangyuan.bean.CarouselfigureBean;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.LogUtils;
import com.me.dingxiangyuan.utils.NetUtils;
import com.me.dingxiangyuan.utils.UrlUtils;
import com.me.dingxiangyuan.view.ShowingPage;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

import static com.me.dingxiangyuan.R.id.home_fm_sv;

/**
 * author by LiKe on 2016/12/28.
 */

public class HomeFragment extends BaseFragment implements SpringView.OnFreshListener, View.OnClickListener {

    private List<CarouselfigureBean.DataEntity> data;
    private RecyclerView recyclerView;
    private String[] url = new String[]{UrlUtils.CarouselUrl, UrlUtils.ZhuJiao, UrlUtils.LoveUrl, UrlUtils.ColdWoreUrl, UrlUtils.LoveOxygen};
    private List<String> jsonList = new ArrayList<>();
    private SpringView springView;
    private View view;
    private TextView home_fragment_love_state_tv;
    private Button home_fragment_message_state;
    private PopupWindow popupWindow;

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

    @Override
    protected void onLoad() {
        int netWorkType = NetUtils.getNetWorkType(getActivity());
        HomeFragmentData data = new HomeFragmentData();
        if (netWorkType == NetUtils.NETWORKTYPE_INVALID) {
            showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);

        } else {
            for (int i = 0; i < url.length; i++) {
                data.getData(url[i], BaseData.NORMALTIME, null, 0);
            }
        }

    }

    @Override
    public View createSuccessView() {
        view = CommonUtils.inflate(R.layout.fragment_home);
        //初始化控件
        initView();
        //初始化控件监听事件
        initViewListener();
        return view;
    }

    /**
     * 初始化控件监听事件
     */
    private void initViewListener() {
        home_fragment_love_state_tv.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        springView = (SpringView) view.findViewById(home_fm_sv);
        recyclerView = (RecyclerView) view.findViewById(R.id.home_fm_rv);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(this);
        springView.setHeader(new DefaultHeader(getActivity()));

        //恋爱状态按钮
        home_fragment_love_state_tv = (TextView) view.findViewById(R.id.home_fragment_love_state_tv);
        //消息状态按钮
        home_fragment_message_state = (Button) view.findViewById(R.id.home_fragment_message_state);

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
                home_fragment_love_state_tv.setText(forum_section_five_logo.getText());
                home_cb_left.setChecked(true);
                home_cb_right.setChecked(false);
                break;
            //popupWindow中单身期点击事件
            case R.id.forum_section_six_logo:
                home_fragment_love_state_tv.setText(forum_section_six_logo.getText());
                home_cb_left.setChecked(false);
                home_cb_right.setChecked(true);
                break;
            //确定按钮
            case R.id.home_popup_sure_tv:
                popupWindow.dismiss();
                break;

        }
    }

    class HomeFragmentData extends BaseData {

        @Override
        public void setResultData(String response) {
            if (response != null) {
                jsonList.add(response);
            } else {
                return;
            }
            if (jsonList.size() == url.length) {
                showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                CommonUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        //设置recyclerView适配器和布局管理器
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(new HomeFragmentRvAdapter(getActivity(), jsonList));
                    }
                });
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        jsonList.clear();
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

}

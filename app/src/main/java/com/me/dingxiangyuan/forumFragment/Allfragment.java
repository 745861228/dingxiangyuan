package com.me.dingxiangyuan.forumFragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.adapter.AllRvAdpater;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.base.BaseFragment;
import com.me.dingxiangyuan.bean.AllJsonBean;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.view.ShowingPage;

import static com.me.dingxiangyuan.utils.UrlUtils.All;
/**
 * Created by Administrator on 2016/12/30.
 */
public class Allfragment extends BaseFragment {
    private MyHomeData myHomeData;
    public String data;
    private RecyclerView all_recycler;
    private AllJsonBean allJsonBean;
    private FloatingActionButton floatingActionButton;
    public boolean isGoneAnimation = false;
    public boolean isVisbleAnimation = false;


    @Override
    protected void onLoad() {
        myHomeData = new Allfragment.MyHomeData();
        myHomeData.getData(All, BaseData.NORMALTIME, null, 0);
    }

    @Override
    public View createSuccessView() {
        View view = initView();

        return view;
    }

    /**
     * 找控件
     *
     * @return
     */
    private View initView() {
        View inflate = CommonUtils.inflate(R.layout.all_recyclerview);
        all_recycler = (RecyclerView) inflate.findViewById(R.id.all_recyclerView);
         /**
         * 设置触摸监听
         */
        all_recycler.setOnTouchListener(new View.OnTouchListener() {
            private float downY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveY = event.getY();
                        //手指向下滑动，让Button显示（执行显示的动画）
                        Log.i("AAAA----", downY + "------" + moveY);
                        if (moveY - downY > 70 && !isVisbleAnimation && floatingActionButton.getVisibility() != View.VISIBLE) {
                            visibleAnimation();
                            isVisbleAnimation = true;
                            moveY = downY;
                            //手指向上滑动，让Button隐藏(执行隐藏的动画)
                        } else if (downY - moveY > 70 && !isGoneAnimation) {
                            moveY = downY;
                            isGoneAnimation = true;
                            goneAnimation();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        isGoneAnimation = false;
                        isVisbleAnimation = false;
                        break;
                }


                return false;
            }
        });
        floatingActionButton = (FloatingActionButton) inflate.findViewById(R.id.floatingActionButton);
        /**
         *  设置点击事件对floatingActionButton
         */
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "我被点击了", Toast.LENGTH_SHORT).show();
            }
        });
        return inflate;
    }

    //设置动画
    private void goneAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 2);
        translateAnimation.setDuration(600);
        translateAnimation.setFillBefore(true);
        translateAnimation.setFillAfter(true);
        floatingActionButton.startAnimation(translateAnimation);
        floatingActionButton.setVisibility(View.GONE);
    }

    //设置动画
    private void visibleAnimation() {
        floatingActionButton.setVisibility(View.VISIBLE);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 2, Animation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(600);
        translateAnimation.setFillBefore(true);
        translateAnimation.setFillAfter(true);
        floatingActionButton.startAnimation(translateAnimation);
    }


    /**
     * 请求网络
     */
    class MyHomeData extends BaseData {
        @Override
        public void setResultData(final String response) {
            showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            Allfragment.this.data = response;
            Gson gson = new Gson();
            allJsonBean = gson.fromJson(data, AllJsonBean.class);
            CommonUtils.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    all_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    all_recycler.setAdapter(new AllRvAdpater(getActivity(), allJsonBean));

                }
            });

        }

    }
}

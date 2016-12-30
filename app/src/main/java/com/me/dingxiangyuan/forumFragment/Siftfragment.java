package com.me.dingxiangyuan.forumFragment;

import android.view.View;

import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.base.BaseFragment;

/**
 * Created by Administrator on 2016/12/30.
 */

public class Siftfragment extends BaseFragment {

    private MyHomeData myHomeData;
    public  String data;

    @Override
    protected void onLoad() {
        myHomeData = new MyHomeData();

    }

    @Override
    public View createSuccessView() {
       View view=initView();
        return view;
    }

    /**
     * 初始化空件
     * @return
     */
    private View initView() {
        return null;
    }

    /**
     * 请求网络
     */
    class MyHomeData extends BaseData{
        @Override
        public void setResultData(String response) {
            Siftfragment.this.data=data;
        }
    }

}

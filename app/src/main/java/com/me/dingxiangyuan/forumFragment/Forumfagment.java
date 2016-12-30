package com.me.dingxiangyuan.forumFragment;

import android.view.View;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.base.BaseFragment;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.view.ShowingPage;

/**
 * Created by Administrator on 2016/12/30.
 */

public class Forumfagment extends BaseFragment{
    public  String  data;
    private MyHomeData myHomeData;
    @Override
    protected void onLoad() {
        myHomeData = new MyHomeData();
        Forumfagment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    @Override
    public View createSuccessView() {
        View view=initView();

        return view;
    }
    /**
     * 初始化控件
     * @return
     */
    private View initView() {
        View inflate = CommonUtils.inflate(R.layout.forum_layout_item);

        return inflate;
    }


    /**
     * 请求网络
     */
    class MyHomeData extends BaseData {
        @Override
        public void setResultData(String data) {
            //先设置数据
            Forumfagment.this.data = data;
        }
    }
}

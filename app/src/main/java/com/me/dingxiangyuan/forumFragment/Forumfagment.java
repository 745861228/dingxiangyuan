package com.me.dingxiangyuan.forumFragment;

import android.content.Intent;
import android.view.View;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.acitvity.BanKuaiActivity;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.base.BaseFragment;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.view.ShowingPage;
import com.zhy.autolayout.AutoLinearLayout;
/**
 * Created by Administrator on 2016/12/30.
 */
public class Forumfagment extends BaseFragment implements View.OnClickListener {
    public  String  data;
    private MyHomeData myHomeData;
    private AutoLinearLayout bankuai_layout;

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
        bankuai_layout = (AutoLinearLayout) inflate.findViewById(R.id.bankuai_layout);
        bankuai_layout.setOnClickListener(this);
        return inflate;
    }
    @Override
    public void onClick(View v) {
                   switch (v.getId())
                   {
                       case R.id.bankuai_layout:
                           Intent intent=new Intent(getActivity(), BanKuaiActivity.class);
                           startActivity(intent);
                           break;
                   }
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

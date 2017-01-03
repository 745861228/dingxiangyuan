package com.me.dingxiangyuan.forumFragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.adapter.SiftRvAdapter;
import com.me.dingxiangyuan.base.BaseData;
import com.me.dingxiangyuan.base.BaseFragment;
import com.me.dingxiangyuan.bean.SiftJsonBean;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.UrlUtils;
import com.me.dingxiangyuan.view.ShowingPage;

/**
 * Created by Administrator on 2016/12/30.
 */

public class Siftfragment extends BaseFragment {
    private MyHomeData myHomeData;
    public  String data;
    private SiftJsonBean siftJsonBean;
    private RecyclerView sift_recycler;
    @Override
    protected void onLoad() {
        myHomeData = new MyHomeData();
        myHomeData.getData(UrlUtils.Sift,BaseData.NORMALTIME,null,0);
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
        View inflate = CommonUtils.inflate(R.layout.sift_layout);
        sift_recycler = (RecyclerView) inflate.findViewById(R.id.sift_recyclerView);
        return inflate;
    }
    /**
     * 请求网络
     */
    class MyHomeData extends BaseData{
        @Override
        public void setResultData(final String response) {
            showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
            Siftfragment.this.data=response;
            Gson gson=new Gson();
            siftJsonBean = gson.fromJson(data,SiftJsonBean.class);
            CommonUtils.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    sift_recycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                    sift_recycler.setAdapter(new SiftRvAdapter(getActivity(),siftJsonBean));
                }
            });

        }

    }

}

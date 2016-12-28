package com.me.dingxiangyuan.fragment;

import android.view.View;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseFragment;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.LogUtils;
import com.me.dingxiangyuan.utils.NetUtils;
import com.me.dingxiangyuan.view.ShowingPage;

/**
 * author by LiKe on 2016/12/28.
 */

public class HomeFragment extends BaseFragment {
    @Override
    protected void onLoad() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int netWorkType = NetUtils.getNetWorkType(getActivity());
                if(netWorkType==NetUtils.NETWORKTYPE_INVALID){
                    showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
                }else {
                    showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                }

            }
        }.start();

    }

    @Override
    public View createSuccessView() {
        View view = CommonUtils.inflate(R.layout.fragment_home);
        return view;
    }
}

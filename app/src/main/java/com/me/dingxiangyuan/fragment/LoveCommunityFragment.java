package com.me.dingxiangyuan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.bean.LoveCommunityBean;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.utils.LogUtils;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * author by LiKe on 2016/12/30.
 */

public class LoveCommunityFragment extends Fragment{


    private ListView love_fm_item_lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = CommonUtils.inflate(R.layout.lovecommunityfm_item);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取数据
        final ArrayList<LoveCommunityBean.DataBean> list = (ArrayList<LoveCommunityBean.DataBean>) this.getArguments().getSerializable("list");

    }

    public static Fragment getFragment(ArrayList<LoveCommunityBean.DataBean> list){
        Fragment fragment = new LoveCommunityFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("list",list);
        fragment.setArguments(bundle);
        return fragment;

    }
}

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


    private TextView title_top_tv;
    private ImageView title_top_image;
    private TextView title_middle_tv;
    private ImageView title_middle_image;
    private TextView title_bottom_tv;
    private ImageView title_bottom_image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = CommonUtils.inflate(R.layout.lovecommunityfm_item);
        initView(view);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取数据
        final ArrayList<LoveCommunityBean.DataBean> list = (ArrayList<LoveCommunityBean.DataBean>) this.getArguments().getSerializable("list");

        for (int i = 0; i < list.size(); i++) {
            if (i == 0){
                title_top_tv.setText(list.get(i).title);
                title_top_image.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(getActivity()).load(list.get(i).headImg).into(title_top_image);
            }else if (i == 1){
                title_middle_tv.setText(list.get(i).title);
                title_middle_image.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(getActivity()).load(list.get(i).headImg).into(title_middle_image);
            }else if (i == 2){
                title_bottom_tv.setText(list.get(i).title);
                title_bottom_image.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(getActivity()).load(list.get(i).headImg).into(title_bottom_image);
            }
        }

    }

    public static Fragment getFragment(ArrayList<LoveCommunityBean.DataBean> list){
        Fragment fragment = new LoveCommunityFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("list",list);
        fragment.setArguments(bundle);
        return fragment;

    }

    /**
     * 初始化控件
     * @param view
     */

    private void initView(View view) {
        title_top_tv = (TextView) view.findViewById(R.id.title_top_tv);
        title_top_image = (ImageView) view.findViewById(R.id.title_top_image);

        title_middle_tv = (TextView) view.findViewById(R.id.title_middle_tv);
        title_middle_image = (ImageView) view.findViewById(R.id.title_middle_image);

        title_bottom_tv = (TextView) view.findViewById(R.id.title_bottom_tv);
        title_bottom_image = (ImageView) view.findViewById(R.id.title_bottom_image);
    }
}

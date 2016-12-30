package com.me.dingxiangyuan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.me.dingxiangyuan.DatumActivity;
import com.me.dingxiangyuan.InvitationActivity;
import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.SettingActivity;
import com.me.dingxiangyuan.TicklingActivity;
import com.me.dingxiangyuan.utils.CommonUtils;

/**
 * author by LiKe on 2016/12/28.
 */

public class MineFragment extends Fragment implements View.OnClickListener{
    private RelativeLayout datum;
    private RelativeLayout constellation;
    private RelativeLayout invitation;
    private RelativeLayout tickling;
    private RelativeLayout setting;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view=   CommonUtils.inflate(R.layout.fragment_mine);
     //初始化控件
        initView();
        return view;
    }
//初始化控件
    private void initView() {
        datum= (RelativeLayout) view.findViewById(R.id.mine_datum);
        constellation= (RelativeLayout) view.findViewById(R.id.mine_constellation);
        invitation= (RelativeLayout) view.findViewById(R.id.mine_invitation);
        tickling= (RelativeLayout) view.findViewById(R.id.mine_tickling);
        setting= (RelativeLayout) view.findViewById(R.id.mine_setting);
        //点击事件
        datum.setOnClickListener(this);
        constellation.setOnClickListener(this);
        invitation.setOnClickListener(this);
        tickling.setOnClickListener(this);
        setting.setOnClickListener(this);



    }
//点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.mine_datum:
                Intent intent=new Intent(getActivity(), DatumActivity.class);
                startActivity(intent);
                break;

            case R.id.mine_constellation:
                Intent intent1=new Intent(getActivity(), DatumActivity.class);
                startActivity(intent1);
                break;
            case R.id.mine_invitation:
                Intent intent2=new Intent(getActivity(), InvitationActivity.class);
                startActivity(intent2);
                break;
            case R.id.mine_tickling:
                Intent intent3=new Intent(getActivity(), TicklingActivity.class);
                startActivity(intent3);
                break;
            case R.id.mine_setting:
                Intent intent4=new Intent(getActivity(), SettingActivity.class);
                startActivity(intent4);
                break;

        }
    }
}

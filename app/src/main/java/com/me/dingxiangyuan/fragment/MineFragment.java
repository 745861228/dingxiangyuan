package com.me.dingxiangyuan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.acitvity.CameraActivity;
import com.me.dingxiangyuan.acitvity.DatumActivity;
import com.me.dingxiangyuan.acitvity.GalleryActivity;
import com.me.dingxiangyuan.acitvity.InvitationActivity;
import com.me.dingxiangyuan.acitvity.SettingActivity;
import com.me.dingxiangyuan.acitvity.TicklingActivity;
import com.me.dingxiangyuan.utils.CommonUtils;
import com.me.dingxiangyuan.view.SelfDialog;

import java.io.File;

/**
 * author by LiKe on 2016/12/28.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout datum;
    private RelativeLayout constellation;
    private RelativeLayout invitation;
    private RelativeLayout tickling;
    private RelativeLayout setting;
    private ImageView user_img;
    private View view;
    private SelfDialog selfDialog;
    //调取相机
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果

    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = CommonUtils.inflate(R.layout.fragment_mine);
        //初始化控件
        initView();
        return view;
    }

    //初始化控件
    private void initView() {
        datum = (RelativeLayout) view.findViewById(R.id.mine_datum);
        constellation = (RelativeLayout) view.findViewById(R.id.mine_constellation);
        invitation = (RelativeLayout) view.findViewById(R.id.mine_invitation);
        tickling = (RelativeLayout) view.findViewById(R.id.mine_tickling);
        setting = (RelativeLayout) view.findViewById(R.id.mine_setting);
        user_img = (ImageView) view.findViewById(R.id.user_image);
        //点击事件
        datum.setOnClickListener(this);
        constellation.setOnClickListener(this);
        invitation.setOnClickListener(this);
        tickling.setOnClickListener(this);
        setting.setOnClickListener(this);
//拍照获取相册
        user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selfDialog = new SelfDialog(getActivity());
                selfDialog.setTitle("提示");
                selfDialog.setYesOnclickListener("相册", new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        Intent intent = new Intent(getActivity(), GalleryActivity.class);
                        startActivity(intent);

                        selfDialog.dismiss();
                    }
                });
                selfDialog.setNoOnclickListener("拍照", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        Intent intent = new Intent(getActivity(), CameraActivity.class);
                        startActivity(intent);

                        selfDialog.dismiss();
                    }
                });
                selfDialog.show();
            }


        });
    }


    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_datum:
                Intent intent = new Intent(getActivity(), DatumActivity.class);
                startActivity(intent);
                break;

            case R.id.mine_constellation:
                Intent intent1 = new Intent(getActivity(), DatumActivity.class);
                startActivity(intent1);
                break;
            case R.id.mine_invitation:
                Intent intent2 = new Intent(getActivity(), InvitationActivity.class);
                startActivity(intent2);
                break;
            case R.id.mine_tickling:
                Intent intent3 = new Intent(getActivity(), TicklingActivity.class);
                startActivity(intent3);
                break;
            case R.id.mine_setting:
                Intent intent4 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent4);
                break;

        }
    }
}

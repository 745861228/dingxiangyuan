package com.me.dingxiangyuan.acitvity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseActivity;


/**
 * autour: 张亚州
 * date: 2016/12/29 0029 下午 8:48
 * update: 2016/12/29 0029
 */

//反馈
public class TicklingActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout relat1;
    private RelativeLayout relat2;
    private RelativeLayout relat3;
    private RelativeLayout relat4;
    private RelativeLayout relat5;
    private ImageView select1;
    private ImageView select2;
    private ImageView select3;
    private ImageView select4;
    private ImageView select5;
    private ImageView img_return;
    private boolean isVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickling);
        //初始化控件
        initView();
    }

    //初始化控件
    private void initView() {
        select1 = (ImageView) findViewById(R.id.select1);
        select2 = (ImageView) findViewById(R.id.select2);
        select3 = (ImageView) findViewById(R.id.select3);
        select4 = (ImageView) findViewById(R.id.select4);
        select5 = (ImageView) findViewById(R.id.select5);
        img_return = (ImageView) findViewById(R.id.imag_return);
        relat1 = (RelativeLayout) findViewById(R.id.relat1);
        relat2 = (RelativeLayout) findViewById(R.id.relat2);
        relat3 = (RelativeLayout) findViewById(R.id.relat3);
        relat4 = (RelativeLayout) findViewById(R.id.relat4);
        relat5 = (RelativeLayout) findViewById(R.id.relat5);


        //隐藏的控件
        select2.setVisibility(View.INVISIBLE);
        select3.setVisibility(View.INVISIBLE);
        select4.setVisibility(View.INVISIBLE);
        select5.setVisibility(View.INVISIBLE);

        //点击事件
        relat1.setOnClickListener(this);
        relat2.setOnClickListener(this);
        relat3.setOnClickListener(this);
        relat4.setOnClickListener(this);
        relat5.setOnClickListener(this);
        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 finish();
                overridePendingTransition(R.anim.leftin, R.anim.leftout);
            }
        });

      //  break;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.relat1:
                if (isVisible) {
                    isVisible = false;
                    select1.setVisibility(View.VISIBLE);
                    select2.setVisibility(View.INVISIBLE);
                    select3.setVisibility(View.INVISIBLE);
                    select4.setVisibility(View.INVISIBLE);
                    select5.setVisibility(View.INVISIBLE);
                } else {

                    isVisible = true;
                }
                break;

            case R.id.relat2:
                if (isVisible) {
                    isVisible = false;
                    select2.setVisibility(View.VISIBLE);
                    select1.setVisibility(View.INVISIBLE);
                    select3.setVisibility(View.INVISIBLE);
                    select4.setVisibility(View.INVISIBLE);
                    select5.setVisibility(View.INVISIBLE);

                } else {
                    //select1.setVisibility(View.INVISIBLE);
                   // select3.setVisibility(View.INVISIBLE);
                    //select4.setVisibility(View.INVISIBLE);
                   // select5.setVisibility(View.INVISIBLE);
                    isVisible = true;
                }
                break;

            case R.id.relat3:
                if (isVisible) {
                    isVisible = false;
                    select3.setVisibility(View.VISIBLE);
                    select1.setVisibility(View.INVISIBLE);
                    select2.setVisibility(View.INVISIBLE);
                    select4.setVisibility(View.INVISIBLE);
                    select5.setVisibility(View.INVISIBLE);

                } else {

                    isVisible = true;
                }
                break;

            case R.id.relat4:
                if (isVisible) {
                    isVisible = false;
                    select4.setVisibility(View.VISIBLE);
                    select1.setVisibility(View.INVISIBLE);
                    select2.setVisibility(View.INVISIBLE);
                    select3.setVisibility(View.INVISIBLE);

                    select5.setVisibility(View.INVISIBLE);
                } else {


                    isVisible = true;
                }
                break;

            case R.id.relat5:
                if (isVisible) {
                    isVisible = false;
                    select5.setVisibility(View.VISIBLE);
                    select1.setVisibility(View.INVISIBLE);
                    select2.setVisibility(View.INVISIBLE);
                    select3.setVisibility(View.INVISIBLE);
                    select4.setVisibility(View.INVISIBLE);
                } else {

                    isVisible = true;
                }
                break;

        }
    }
}

package com.me.dingxiangyuan.acitvity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.me.dingxiangyuan.R;

public class DialogueXActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView xiaoxi_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogue_x);
        xiaoxi_image = (ImageView) findViewById(R.id.xiaoxi_image);
        xiaoxi_image.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
          switch (v.getId())
          {
              case R.id.xiaoxi_image:
                  finish();
                  break;
          }
    }
}

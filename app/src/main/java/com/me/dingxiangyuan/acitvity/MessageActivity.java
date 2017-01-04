package com.me.dingxiangyuan.acitvity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.me.dingxiangyuan.R;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView weiguanzhu_image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        weiguanzhu_image1 = (ImageView) findViewById(R.id.weiguanzhu_image1);
        weiguanzhu_image1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          switch (v.getId())
          {
              case R.id.weiguanzhu_image1:
                  finish();
                  break;
          }
    }
}

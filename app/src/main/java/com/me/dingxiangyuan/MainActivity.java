package com.me.dingxiangyuan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.me.dingxiangyuan.base.BaseData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseData baseData = new BaseData() {
            @Override
            public void setResultData(String data) {
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        };

        baseData.getData("http://www.sougou.com",null,0,BaseData.NORMALTIME);
        //sahdukashdiu

    }
}

package com.me.dingxiangyuan.newsFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.acitvity.DialogueWActivity;
import com.me.dingxiangyuan.acitvity.DialogueXActivity;

/**
 * Created by Administrator on 2017/1/4.
 */

public class DialogueFragment extends Fragment implements View.OnClickListener {
    private LinearLayout weiguanzhus;
    private LinearLayout xitongs;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.dialogue_layout, null);
        weiguanzhus = (LinearLayout) view.findViewById(R.id.weiguanzhu);
        weiguanzhus.setOnClickListener(this);
        xitongs = (LinearLayout) view.findViewById(R.id.xitong);
        xitongs.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
                    switch (v.getId())
                    {
                        case  R.id.weiguanzhu:
                            Intent intent=new Intent(getActivity(), DialogueWActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.xitong:
                            Intent intent1=new Intent(getActivity(), DialogueXActivity.class);
                            startActivity(intent1);
                            break;
                    }
    }
}

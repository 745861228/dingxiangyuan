package com.me.dingxiangyuan.base;

import android.text.TextUtils;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.me.dingxiangyuan.application.MyApplication;
import com.me.dingxiangyuan.bean.NetDataBean;
import com.me.dingxiangyuan.dao.NetTabDao;
import com.me.dingxiangyuan.utils.CommonUtils;

import java.util.List;

/**
 * author by LiKe on 2016/12/28.
 */

public abstract class BaseData {

    private final NetTabDao netTabDao;
    public static final int NOTIME = 0;
    public static final int NORMALTIME = 3 * 24 * 60 * 60 * 1000;

    public BaseData() {
        //创建数据库对象
        netTabDao = new NetTabDao(CommonUtils.getContext());
    }

    //获取数据
    public void getData(String path, int time, String args, int index) {
        //如果限定时间为0，直接网络请求数据
        if (time == NOTIME) {
            getDataFromNet(path, time, args, index);
        } else {
            // 首先查询数据库 判断数据库中的path与当前path是否相等,(如果相等的话,再次进行时间判断,如果在指定
            //的时间内,获取数据的内容,不在指定时间内进行修改 ),不相等的话进行添加.
            String data = getDataFromSqlData(path, time, args, index);
            if(TextUtils.isEmpty(data)){
                getDataFromNet(path,time,args,index);
            }else{
                setResultData(data);
            }
        }
    }

    /**
     * 查询数据库中的地址及时间进行判断
     *
     * @param path
     * @param time
     * @param args
     * @param index
     */

    private String getDataFromSqlData(String path, int time, String args, int index) {
        // 查询数据库
        List<NetDataBean> netDataList = netTabDao.selectData();
        //获取当前的系统时间
        long nowTime = System.currentTimeMillis();
        for (int i = 0; i < netDataList.size(); i++) {
            // 首先判断数据库中的地址与当前的地址是否相等
            if (netDataList.get(i).getPath().equals(path)) {
                // 获取数据库中的时间
                long sqlTime = Long.parseLong(netDataList.get(i).getTime());
                // 时间进行比较如果满足条件
                if(nowTime - sqlTime < time){
                    return netDataList.get(i).getData();
                }else {
                    getDataFromNet(path,time,args,index);
                }
            }
        }
        return null;
    }



    /**
     * 进行网络请求
     *
     * @param path
     * @param time
     * @param args
     * @param index
     */
    private void getDataFromNet(final String path, final int time, final String args, final int index) {
        StringRequest stringRequest = new StringRequest(path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //将数据回传
                        setResultData(response);
                        if (time != NOTIME) {
                            //将数据保存数据库
                            setDataToLocalDb(path, time, args, index, response);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MyApplication.getRequestQueue().add(stringRequest);
    }

    /**
     * 将数据保存进数据库
     *
     * @param path
     * @param time
     * @param args
     * @param index
     * @param response
     */
    private void setDataToLocalDb(String path, int time, String args, int index, String response) {
        // 获取当前时间
        long nowTime = System.currentTimeMillis();
        // 添加到数据库
        netTabDao.insertData(path,response,nowTime+"");
    }

    public abstract void setResultData(String response);


}

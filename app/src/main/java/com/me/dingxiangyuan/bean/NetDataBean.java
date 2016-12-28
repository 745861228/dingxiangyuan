package com.me.dingxiangyuan.bean;

/**
 * author by LiKe on 2016/12/28.
 */

public class NetDataBean {
    private String path;
    private String time;
    private String data;

    public NetDataBean(String data, String path, String time) {
        this.data = data;
        this.path = path;
        this.time = time;
    }

    public NetDataBean() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

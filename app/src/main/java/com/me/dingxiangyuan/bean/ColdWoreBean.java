package com.me.dingxiangyuan.bean;

import java.util.List;

/**
 * author by LiKe on 2017/1/1.
 */

public class ColdWoreBean {


    /**
     * code : 1
     * height : 0
     * success : true
     * width : 0
     * data : [{"yuLinUser":null,"url":"http://www.yulin520.com/a2a/h/news/o/1992?a=1483253547712","reporterName":null,"indexImg":"","contentIntr":null,"click":1952,"title":"有奖征集 | 我有电影票，你有故事么？","star":144,"introduction":"在充满感恩、温暖与爱的圣诞期间，柠乐君将带你一起，踏上一段有笑有泪的《摆渡人》之旅。 ","replyTimes":2,"img":"http://img1.yulin520.com/news/RVH92M4H4F50O7XS0G4C.jpg#450_1125","startTime":null,"id":1992,"type":26}]
     * message : null
     */

    public int code;
    public int height;
    public boolean success;
    public int width;
    public Object message;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * yuLinUser : null
         * url : http://www.yulin520.com/a2a/h/news/o/1992?a=1483253547712
         * reporterName : null
         * indexImg :
         * contentIntr : null
         * click : 1952
         * title : 有奖征集 | 我有电影票，你有故事么？
         * star : 144
         * introduction : 在充满感恩、温暖与爱的圣诞期间，柠乐君将带你一起，踏上一段有笑有泪的《摆渡人》之旅。
         * replyTimes : 2
         * img : http://img1.yulin520.com/news/RVH92M4H4F50O7XS0G4C.jpg#450_1125
         * startTime : null
         * id : 1992
         * type : 26
         */

        public Object yuLinUser;
        public String url;
        public Object reporterName;
        public String indexImg;
        public Object contentIntr;
        public int click;
        public String title;
        public int star;
        public String introduction;
        public int replyTimes;
        public String img;
        public Object startTime;
        public int id;
        public int type;
    }
}

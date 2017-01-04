package com.me.dingxiangyuan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qwe on 2016/12/28.
 */

public class CarouselfigureBean implements Serializable{


    /**
     * code : 1
     * data : [{"broadcastId":1018,"click":1,"detailsUrl":"http://www.xiaoningle.com/a2a/h/broadcast/o/1018?a=1483451089227","img":"http://img1.yulin520.com/news/TV4ZQ8NA9F50O3ADXQYC.jpg#525_930","remark":"如果你的女朋友在你面前提到口红，\u201d哼哼\u201c","replyTimes":2,"star":12,"title":"当女朋友想要口红，她其实是想要.....","url":"http://yulin3.oss-cn-hangzhou.aliyuncs.com/%E9%9F%B3%E9%A2%91%E6%96%87%E4%BB%B6/%E9%9F%B3%E9%A2%91/11.mp3"},{"broadcastId":1017,"click":20,"detailsUrl":"http://www.xiaoningle.com/a2a/h/broadcast/o/1017?a=1483451089227","img":"http://img1.yulin520.com/news/3V0KBBPUUF50OIAJWRUL.jpg#525_930","remark":"双11究竟是光棍节还是购物节呢？","replyTimes":3,"star":27,"title":"分手后男女分别是这副鬼样子","url":"http://yulin3.oss-cn-hangzhou.aliyuncs.com/%E9%9F%B3%E9%A2%91%E6%96%87%E4%BB%B6/%E9%9F%B3%E9%A2%91/10.mp3"},{"broadcastId":1016,"click":0,"detailsUrl":"http://www.xiaoningle.com/a2a/h/broadcast/o/1016?a=1483451089227","img":"http://img1.yulin520.com/news/1UJ4OZO9H6Y0OY6AA539.jpg#690_1125","remark":"好马不吃回头草吗?","replyTimes":7,"star":45,"title":"我还是想跟你复合，有戏吗？","url":"http://yulin3.oss-cn-hangzhou.aliyuncs.com/%E9%9F%B3%E9%A2%91%E6%96%87%E4%BB%B6/%E9%9F%B3%E9%A2%91/9.mp3"},{"broadcastId":1015,"click":0,"detailsUrl":"http://www.xiaoningle.com/a2a/h/broadcast/o/1015?a=1483451089227","img":"http://img1.yulin520.com/news/OU6G5MQHIA80OFWF53R4.jpg#490_800","remark":"程序猿，都是这么撩妹的\u2026\u2026","replyTimes":2,"star":30,"title":"关爱程序员（下）：他们有啥撩妹技巧？","url":"http://yulin3.oss-cn-hangzhou.aliyuncs.com/%E9%9F%B3%E9%A2%91%E6%96%87%E4%BB%B6%2F%E9%9F%B3%E9%A2%91%2F8.mp3"},{"broadcastId":1014,"click":0,"detailsUrl":"http://www.xiaoningle.com/a2a/h/broadcast/o/1014?a=1483451089227","img":"http://img1.yulin520.com/news/6U1TX4EWUKX0OTZLY6TK.jpg#690_1125","remark":"走近呆萌的程序猿","replyTimes":3,"star":10,"title":"关爱程序员（上）：IT男真的毫无情趣吗？","url":"http://yulin3.oss-cn-hangzhou.aliyuncs.com/%E9%9F%B3%E9%A2%91%E6%96%87%E4%BB%B6%2F%E9%9F%B3%E9%A2%91%2F7.mp3"},{"broadcastId":1013,"click":0,"detailsUrl":"http://www.xiaoningle.com/a2a/h/broadcast/o/1013?a=1483451089227","img":"http://img1.yulin520.com/news/CTMEFZ6PLEP0O6YCK4S2.jpg#690_1125","remark":"美少女主持人陪你唠嗑","replyTimes":2,"star":4,"title":"美少女战士来啦！","url":"http://yulin3.oss-cn-hangzhou.aliyuncs.com/%E9%9F%B3%E9%A2%91%E6%96%87%E4%BB%B6/%E9%9F%B3%E9%A2%91/Vol%201%EF%BC%9A%E7%BE%8E%E5%B0%91%E5%A5%B3%E6%88%98%E5%A3%AB%E6%9D%A5%E5%95%A6%EF%BC%81.mp3"},{"broadcastId":1012,"click":0,"detailsUrl":"http://www.xiaoningle.com/a2a/h/broadcast/o/1012?a=1483451089227","img":"http://img1.yulin520.com/news/2TMRF16AMEP0OTKW6IEJ.jpg#690_1125","remark":"一天不淘宝，心里很不爽","replyTimes":1,"star":4,"title":"女朋友每月淘宝花多少钱算正常？","url":"http://yulin3.oss-cn-hangzhou.aliyuncs.com/%E9%9F%B3%E9%A2%91%E6%96%87%E4%BB%B6/%E9%9F%B3%E9%A2%91/Vol%202.mp3"},{"broadcastId":1011,"click":0,"detailsUrl":"http://www.xiaoningle.com/a2a/h/broadcast/o/1011?a=1483451089227","img":"http://img1.yulin520.com/news/ETM7FI6MNEP0OMUI5EI5.jpg#690_1125","remark":"如果女神在你面前放了个P\u2026\u2026","replyTimes":0,"star":8,"title":"说说放P这件小事儿","url":"http://yulin3.oss-cn-hangzhou.aliyuncs.com/%E9%9F%B3%E9%A2%91%E6%96%87%E4%BB%B6/%E9%9F%B3%E9%A2%91/Vol%203%EF%BC%9A%E8%AF%B4%E8%AF%B4%E6%94%BEP%E8%BF%99%E4%BB%B6%E5%B0%8F%E4%BA%8B%E5%84%BF.mp3"},{"broadcastId":1010,"click":0,"detailsUrl":"http://www.xiaoningle.com/a2a/h/broadcast/o/1010?a=1483451089227","img":"http://img1.yulin520.com/news/KTMPFS7WOEP0OXIF07UI.jpg#690_1125","remark":"可谈感情，伤钱呐","replyTimes":0,"star":14,"title":"谈钱，多伤感情！","url":"http://yulin3.oss-cn-hangzhou.aliyuncs.com/%E9%9F%B3%E9%A2%91%E6%96%87%E4%BB%B6/%E9%9F%B3%E9%A2%91/Vol%204%EF%BC%9A%E8%B0%88%E9%92%B1%EF%BC%8C%E5%A4%9A%E4%BC%A4%E6%84%9F%E6%83%85%EF%BC%81.mp3"}]
     * height : 0
     * success : true
     * width : 0
     */

    public String code;
    public String height;
    public boolean success;
    public String width;
    public List<DataBean> data;



    public static class DataBean implements Serializable{
        /**
         * broadcastId : 1018
         * click : 1
         * detailsUrl : http://www.xiaoningle.com/a2a/h/broadcast/o/1018?a=1483451089227
         * img : http://img1.yulin520.com/news/TV4ZQ8NA9F50O3ADXQYC.jpg#525_930
         * remark : 如果你的女朋友在你面前提到口红，”哼哼“
         * replyTimes : 2
         * star : 12
         * title : 当女朋友想要口红，她其实是想要.....
         * url : http://yulin3.oss-cn-hangzhou.aliyuncs.com/%E9%9F%B3%E9%A2%91%E6%96%87%E4%BB%B6/%E9%9F%B3%E9%A2%91/11.mp3
         */

        public String broadcastId;
        public String click;
        public String detailsUrl;
        public String img;
        public String remark;
        public String replyTimes;
        public String star;
        public String title;
        public String url;
    }
}

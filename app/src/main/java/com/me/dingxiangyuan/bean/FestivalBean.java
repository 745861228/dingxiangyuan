package com.me.dingxiangyuan.bean;

/**
 * author by LiKe on 2016/12/30.
 */

public class FestivalBean {


    /**
     * code : 1
     * width : 0
     * height : 0
     * success : true
     * data : {"remark":"http://www.yulin520.com/a2a/h/i/yulin/h5_festival","name":"春节-  1.28","img":"http://img1.yulin520.com/news/JULIM57SWHT0OA56ZQ2N.png#210_210","holidayDetails":"http://www.yulin520.com/a2a/h/i/app/next_festival","festivalTime":1485532800000,"festivalId":7}
     * message : null
     */

    public int code;
    public int width;
    public int height;
    public boolean success;
    public DataBean data;
    public Object message;

    public static class DataBean {
        /**
         * remark : http://www.yulin520.com/a2a/h/i/yulin/h5_festival
         * name : 春节-  1.28
         * img : http://img1.yulin520.com/news/JULIM57SWHT0OA56ZQ2N.png#210_210
         * holidayDetails : http://www.yulin520.com/a2a/h/i/app/next_festival
         * festivalTime : 1485532800000
         * festivalId : 7
         */

        public String remark;
        public String name;
        public String img;
        public String holidayDetails;
        public long festivalTime;
        public int festivalId;
    }
}

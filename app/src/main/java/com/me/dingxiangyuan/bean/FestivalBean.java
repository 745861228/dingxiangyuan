package com.me.dingxiangyuan.bean;

/**
 * author by LiKe on 2016/12/30.
 */

public class FestivalBean {

    /**
     * code : 1
     * data : {"festivalId":6,"festivalTime":1483200000000,"holidayDetails":"http://www.yulin520.com/a2a/h/i/app/next_festival","img":"http://img1.yulin520.com/news/JULIM57SWHT0OA56ZQ2N.png#210_210","name":"元旦节-  1.1","remark":"http://www.yulin520.com/a2a/h/i/yulin/h5_festival"}
     * height : 0
     * success : true
     * width : 0
     */

    public int code;
    public DataBean data;
    public int height;
    public boolean success;
    public int width;

    public static class DataBean {
        /**
         * festivalId : 6
         * festivalTime : 1483200000000
         * holidayDetails : http://www.yulin520.com/a2a/h/i/app/next_festival
         * img : http://img1.yulin520.com/news/JULIM57SWHT0OA56ZQ2N.png#210_210
         * name : 元旦节-  1.1
         * remark : http://www.yulin520.com/a2a/h/i/yulin/h5_festival
         */

        public int festivalId;
        public long festivalTime;
        public String holidayDetails;
        public String img;
        public String name;
        public String remark;
    }
}

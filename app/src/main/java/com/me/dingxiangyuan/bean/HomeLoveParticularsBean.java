package com.me.dingxiangyuan.bean;

import java.util.List;

/**
 * author by LiKe on 2017/1/7.
 */

public class HomeLoveParticularsBean {


    /**
     * code : 1
     * width : 0
     * height : 0
     * success : true
     * data : [{"frmList":[],"imgs":[],"floor":"1楼","floorReplyTimes":0,"userId":7828,"phone":"","forumId":10459,"createTime":1482896623000,"yulin":"93595","headImg":"http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E6%99%93%E4%B8%BD%E5%A4%B4%E5%83%8F3/70.png","userRole":null,"userName":"奥特蛋@","id":26346,"host":0,"content":"长篇大论的我是看了。\n\n不做点评。\n\n我就直接告诉你，\n\n妄图控制男人思想的女人，都是大傻X。"},{"frmList":[],"imgs":[],"floor":"2楼","floorReplyTimes":0,"userId":7742,"phone":"","forumId":10459,"createTime":1482902786000,"yulin":"93337","headImg":"http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E5%B0%8F%E5%AE%87%E7%9A%84%E5%A4%B4%E5%83%8F%E7%AC%AC%E4%BA%8C%E6%B3%A2/109.jpg","userRole":null,"userName":"九泉家的小熊","id":26347,"host":0,"content":"心里有朵放不下的白莲花的男人千万别碰！\n男人这么多，还有好多纯真的小处男呢，干嘛要在这种注定得不到爱的人身上期待爱呢？\n\n同遇到过这种硬件无可挑剔但是心里还有白莲花的完美王老五，感觉是一块蛋糕上面沾了一坨呕吐物。再诱人也没法吃。"},{"frmList":[],"imgs":[],"floor":"3楼","floorReplyTimes":0,"userId":8178,"phone":"","forumId":10459,"createTime":1482917430000,"yulin":"94645","headImg":"http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E5%B0%8F%E5%AE%87%E7%9A%84%E5%A4%B4%E5%83%8F%E7%AC%AC%E4%BA%8C%E6%B3%A2/113.jpg","userRole":null,"userName":"-Ethereally","id":26348,"host":0,"content":"我是男生,如果我真喜欢你,注意力就只会放在你身上,以前的恋人只会回忆.\n\n1.如果我只是偶尔想想,没有和前任聊,没有撩,无出格举动你却介意的话,你有病.\n\n2.我和前任有联系,有聊有撩,尽早分.男生出轨迟早的事."},{"frmList":[],"imgs":[],"floor":"4楼","floorReplyTimes":0,"userId":8508,"phone":"","forumId":10459,"createTime":1482920577000,"yulin":"95815","headImg":"http://photo-60481.oss-cn-shenzhen.aliyuncs.com/photo/42.gif","userRole":null,"userName":"julietevening","id":26349,"host":0,"content":"他没那么喜欢你。按照这样的趋势发展下去很难有好结果。"}]
     * message : null
     */

    public int code;
    public int width;
    public int height;
    public boolean success;
    public Object message;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * frmList : []
         * imgs : []
         * floor : 1楼
         * floorReplyTimes : 0
         * userId : 7828
         * phone :
         * forumId : 10459
         * createTime : 1482896623000
         * yulin : 93595
         * headImg : http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E6%99%93%E4%B8%BD%E5%A4%B4%E5%83%8F3/70.png
         * userRole : null
         * userName : 奥特蛋@
         * id : 26346
         * host : 0
         * content : 长篇大论的我是看了。

         不做点评。

         我就直接告诉你，

         妄图控制男人思想的女人，都是大傻X。
         */

        public String floor;
        public int floorReplyTimes;
        public int userId;
        public String phone;
        public int forumId;
        public long createTime;
        public String yulin;
        public String headImg;
        public Object userRole;
        public String userName;
        public int id;
        public int host;
        public String content;
        public List<?> frmList;
        public List<?> imgs;
    }
}

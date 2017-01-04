package com.me.dingxiangyuan.bean;

/**
 * Created by qwe on 2017/1/4.
 * 登录Bean
 */

public class LoginBean {

    /**
     * status : ok
     * data : {"userId":"10721","name":"godboy","telNum":"18301391517","password":"","image_url":"http://114.112.104.151:8203/pictures/headimg/userhead/head_w.png"}
     */

    private String status;
    private DataEntity data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * userId : 10721
         * name : godboy
         * telNum : 18301391517
         * password :
         * image_url : http://114.112.104.151:8203/pictures/headimg/userhead/head_w.png
         */

        private String userId;
        private String name;
        private String telNum;
        private String password;
        private String image_url;
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTelNum() {
            return telNum;
        }

        public void setTelNum(String telNum) {
            this.telNum = telNum;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}

package com.me.dingxiangyuan.bean;

/**
 * Created by qwe on 2017/1/3.
 * 注册封装
 */

public class RegisterBean {

    /**
     * status : error
     * data : {"code":"603","message":"无效验证码请重新发送"}
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
         * code : 603
         * message : 无效验证码请重新发送
         */

        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

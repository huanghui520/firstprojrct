package com.fajuary.xiyishop_big_android;

/**
 * created by huanghui at 2016/11/22
 */

public class LogInBean {

    /**
     * code : 0
     * msg : 查询成功！
     * datas : {"id":"55aa16410657bd42a94b9eb9995943ed","ip":"ic.168.0.32.","phone":"15575205994","time":1479808180,"token":"","uid":1}
     */

    private String code;
    private String msg;
    /**
     * id : 55aa16410657bd42a94b9eb9995943ed
     * ip : ic.168.0.32.
     * phone : 15575205994
     * time : 1479808180
     * token :   {"code":"3","msg":"查询成功！","datas":输入的密码有误！}
     * uid : 1
     */

    private DatasBean datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private String id;
        private String ip;
        private String phone;
        private int time;
        private String token;
        private int uid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}

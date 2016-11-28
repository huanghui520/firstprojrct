package com.fajuary.xiyishop_android.module.bean;

/**
 * created by huanghui at 2016/10/24
 * 版本更新
 */

public class VersionBean {

    /**
     * code : 1
     * msg : 查询成功！
     * datas : {"describe":"","id":54,"identify":1,"path":"http://60.205.149.58:8082/files/2016/10/24/1177aaa30a-6ab0-4fcc-a915-e492153108db.apk","version":"2.0"}
     */

    private String code;
    private String msg;
    /**
     * describe :
     * id : 54
     * identify : 1
     * path : http://60.205.149.58:8082/files/2016/10/24/1177aaa30a-6ab0-4fcc-a915-e492153108db.apk
     * version : 2.0
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
        private String describe;
        private int id;
        private int identify;
        private String path;
        private String version;

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIdentify() {
            return identify;
        }

        public void setIdentify(int identify) {
            this.identify = identify;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}

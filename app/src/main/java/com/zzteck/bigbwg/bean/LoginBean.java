package com.zzteck.bigbwg.bean;

import java.io.Serializable;

/**
 * Created by Jianwei on 2018/4/1.
 * 文物，文创列表
 */

public class LoginBean implements Serializable {

    private int errcode;

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    private String errmsg;


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private Data data ;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public static class Data {

        private String token;

        private UserInfo userInfo ;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public String getRongyuantoken() {
            return rongyuantoken;
        }

        public void setRongyuantoken(String rongyuantoken) {
            this.rongyuantoken = rongyuantoken;
        }

        private String rongyuantoken ;
    }

    public static class UserInfo {
        private int id;
        private String account;
        private String name;
        private String id_card;
        private int guide_machine_id;
        private String code;
        private int museum_id;
        private int status;
        private long created;
        private long modified;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId_card() {
            return id_card;
        }

        public void setId_card(String id_card) {
            this.id_card = id_card;
        }

        public int getGuide_machine_id() {
            return guide_machine_id;
        }

        public void setGuide_machine_id(int guide_machine_id) {
            this.guide_machine_id = guide_machine_id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getMuseum_id() {
            return museum_id;
        }

        public void setMuseum_id(int museum_id) {
            this.museum_id = museum_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCreated() {
            return created;
        }

        public void setCreated(long created) {
            this.created = created;
        }

        public long getModified() {
            return modified;
        }

        public void setModified(long modified) {
            this.modified = modified;
        }
    }


}

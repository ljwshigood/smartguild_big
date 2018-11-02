package com.zzteck.bigbwg.bean;

import java.io.Serializable;

/**
 * Created by Jianwei on 2018/4/1.
 *
 */

public class ActDetailBean implements Serializable {


    /**
     * errcode : 200
     * errmsg : ok
     * data : {"id":16,"museum_id":1,"day_use":1540656000,"end_date":1540656000,"start_time":"10:00:00","end_time":"11:00:00","price":0,"sales":2,"quota":48,"sort":1,"name":"普公宝藏精品讲解（第一场）","desc":"<div class=\"detail\">\n<div id=\"search2\" class=\" column\">\n<div id=\"81bb325f3ef34ecfb79b4b2486348886\" class=\"portlet\">\n<div class=\"article\">\n<div class=\"eventwrap\">\n<div id=\"event-info\">\n<h1 class=\"event-detail\"><span class=\"pr\">普公宝藏精品讲解（第一场）<\/span><\/h1>\n<div class=\"info_2\">&nbsp;<\/div>\n<div class=\"event-detail\"><span class=\"pl \">主办方: <\/span><span class=\"pr\">广州市普公古陶瓷博物馆<\/span><\/div>\n<p class=\"event-detail\"><span class=\"pl\">开始时间:&nbsp;&nbsp;<\/span> <span class=\"start_time pr\">2018-10-28 10:00:00<\/span><\/p>\n<p class=\"event-detail\"><span class=\"pl\">结束时间:&nbsp;&nbsp;<\/span> <span class=\"end_time pr\">2018-10-28 11:00:00<\/span><\/p>\n<p id=\"yuyue_time2\" class=\"event-detail\"><span class=\"pl\">预约时间: <\/span><span class=\"yuyue_time pr\"><strong>2018-09-29 15:00:00<\/strong> 至 <strong>2018-10-27 24:00:00<\/strong><\/span><\/p>\n<p class=\"event-detail\"><span class=\"pl\">活动人数: <\/span><span class=\"pr\">20<\/span><\/p>\n<p class=\"event-detail\"><span class=\"pl\">主讲人: <\/span><span class=\"pr\">蒲亭利<\/span><\/p>\n<p class=\"event-detail\"><span class=\"pl\">适用人群: <\/span><span class=\"pr\">18岁以上<\/span><\/p>\n<p class=\"event-detail\"><span class=\"pl\">备注: <\/span><\/p>\n<p class=\"event-detail\"><span class=\"pr\">【签到时间】2018-10-28 09:30；<\/span><\/p>\n<p class=\"event-detail\"><span class=\"pr\">【签到地点】普公古陶瓷博物馆前台。请凭身份证前往签到处，请准时到达，逾期不候，谢谢。<\/span><\/p>\n<p class=\"event-detail\"><span class=\"pl\">地点:&nbsp;<\/span> <span class=\"micro-address pr\">二楼展厅<\/span><\/p>\n<p class=\"event-detail\"><span class=\"pl\">费用:免费<\/span><\/p>\n<p class=\"event-detail\"><span class=\"pl\">类型: <\/span><span class=\"pr\">其他教育活动<\/span><\/p>\n<div class=\"event-detail\">&nbsp;<\/div>\n<div class=\"event-detail\">\n<div class=\"detail\">\n<div id=\"search2\" class=\" column\">\n<div id=\"81bb325f3ef34ecfb79b4b2486348886\" class=\"portlet\">\n<div class=\"article\">\n<div class=\"main\">\n<dl>\n<dd>\n<h2 class=\"list_title\"><strong>简介<\/strong><\/h2>\n<div class=\"main_cont\">\n<p>　　有人说&ldquo;普公的宝贝，藏在深巷无人识&rdquo;<\/p>\n<p>　　面对200余件套宝<\/p>\n<p>　　您心中是否有万千疑问与好奇<\/p>\n<p>　　这一次，蒲亭利副馆长带您领略千古陶瓷！<\/p>\n<\/div>\n<\/dd>\n<dd><\/dd>\n<dd>\n<p>&nbsp;<\/p>\n<\/dd>\n<\/dl>\n<\/div>\n<\/div>\n<\/div>\n<\/div>\n<\/div>\n<\/div>\n<!-- <div class=\"event-detail yuyue\">\n                <a name=\"/gdmuseum/_300882/_300910/_300926/index.html\" class=\"yybtn\" target=\"_blank\"><span>预约<\/span><\/a>\n                <span class=\"pr\">111<\/span>\n             <\/div>--><\/div>\n<\/div>\n<\/div>\n<\/div>\n<\/div>\n<\/div>","imgs":"/uploads/img/2018-05-15/拓片.png","zan":1,"view":8,"created":1538898953,"modified":1539763233}
     */

    private int errcode;
    private String errmsg;
    private DataBean data;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 16
         * museum_id : 1
         * day_use : 1540656000
         * end_date : 1540656000
         * start_time : 10:00:00
         * end_time : 11:00:00
         * price : 0
         * sales : 2
         * quota : 48
         * sort : 1
         * name : 普公宝藏精品讲解（第一场）
         * desc : <div class="detail">
         <div id="search2" class=" column">
         <div id="81bb325f3ef34ecfb79b4b2486348886" class="portlet">
         <div class="article">
         <div class="eventwrap">
         <div id="event-info">
         <h1 class="event-detail"><span class="pr">普公宝藏精品讲解（第一场）</span></h1>
         <div class="info_2">&nbsp;</div>
         <div class="event-detail"><span class="pl ">主办方: </span><span class="pr">广州市普公古陶瓷博物馆</span></div>
         <p class="event-detail"><span class="pl">开始时间:&nbsp;&nbsp;</span> <span class="start_time pr">2018-10-28 10:00:00</span></p>
         <p class="event-detail"><span class="pl">结束时间:&nbsp;&nbsp;</span> <span class="end_time pr">2018-10-28 11:00:00</span></p>
         <p id="yuyue_time2" class="event-detail"><span class="pl">预约时间: </span><span class="yuyue_time pr"><strong>2018-09-29 15:00:00</strong> 至 <strong>2018-10-27 24:00:00</strong></span></p>
         <p class="event-detail"><span class="pl">活动人数: </span><span class="pr">20</span></p>
         <p class="event-detail"><span class="pl">主讲人: </span><span class="pr">蒲亭利</span></p>
         <p class="event-detail"><span class="pl">适用人群: </span><span class="pr">18岁以上</span></p>
         <p class="event-detail"><span class="pl">备注: </span></p>
         <p class="event-detail"><span class="pr">【签到时间】2018-10-28 09:30；</span></p>
         <p class="event-detail"><span class="pr">【签到地点】普公古陶瓷博物馆前台。请凭身份证前往签到处，请准时到达，逾期不候，谢谢。</span></p>
         <p class="event-detail"><span class="pl">地点:&nbsp;</span> <span class="micro-address pr">二楼展厅</span></p>
         <p class="event-detail"><span class="pl">费用:免费</span></p>
         <p class="event-detail"><span class="pl">类型: </span><span class="pr">其他教育活动</span></p>
         <div class="event-detail">&nbsp;</div>
         <div class="event-detail">
         <div class="detail">
         <div id="search2" class=" column">
         <div id="81bb325f3ef34ecfb79b4b2486348886" class="portlet">
         <div class="article">
         <div class="main">
         <dl>
         <dd>
         <h2 class="list_title"><strong>简介</strong></h2>
         <div class="main_cont">
         <p>　　有人说&ldquo;普公的宝贝，藏在深巷无人识&rdquo;</p>
         <p>　　面对200余件套宝</p>
         <p>　　您心中是否有万千疑问与好奇</p>
         <p>　　这一次，蒲亭利副馆长带您领略千古陶瓷！</p>
         </div>
         </dd>
         <dd></dd>
         <dd>
         <p>&nbsp;</p>
         </dd>
         </dl>
         </div>
         </div>
         </div>
         </div>
         </div>
         </div>
         <!-- <div class="event-detail yuyue">
         <a name="/gdmuseum/_300882/_300910/_300926/index.html" class="yybtn" target="_blank"><span>预约</span></a>
         <span class="pr">111</span>
         </div>--></div>
         </div>
         </div>
         </div>
         </div>
         </div>
         * imgs : /uploads/img/2018-05-15/拓片.png
         * zan : 1
         * view : 8
         * created : 1538898953
         * modified : 1539763233
         */

        private int id;
        private int museum_id;
        private int day_use;
        private int end_date;
        private String start_time;
        private String end_time;
        private int price;
        private int sales;
        private int quota;
        private int sort;
        private String name;
        private String desc;
        private String imgs;
        private int zan;
        private int view;
        private int created;
        private int modified;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMuseum_id() {
            return museum_id;
        }

        public void setMuseum_id(int museum_id) {
            this.museum_id = museum_id;
        }

        public int getDay_use() {
            return day_use;
        }

        public void setDay_use(int day_use) {
            this.day_use = day_use;
        }

        public int getEnd_date() {
            return end_date;
        }

        public void setEnd_date(int end_date) {
            this.end_date = end_date;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public int getQuota() {
            return quota;
        }

        public void setQuota(int quota) {
            this.quota = quota;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public int getView() {
            return view;
        }

        public void setView(int view) {
            this.view = view;
        }

        public int getCreated() {
            return created;
        }

        public void setCreated(int created) {
            this.created = created;
        }

        public int getModified() {
            return modified;
        }

        public void setModified(int modified) {
            this.modified = modified;
        }
    }
}

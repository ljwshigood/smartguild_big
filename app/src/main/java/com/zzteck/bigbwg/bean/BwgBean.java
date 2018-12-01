package com.zzteck.bigbwg.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/11/14 0014.
 */

public class BwgBean implements Serializable{


    /**
     * errcode : 200
     * errmsg : ok
     * data : {"id":"1","bind_host":"http://bwg.jianjiesz.com","name":"广州市普公古陶瓷博物馆",
     * "logo":"/uploads/img/2018-09-13/博物馆logo.png","imgs":"[\"/uploads/img/2018-09-13/首页外景图.jpg\",
     * \"/uploads/1/2018-10-17/外观_副本.jpg\",\"/uploads/1/2018-10-17/重阳.png\"]",
     * "desc":"<h3><span style=\"color: #333333;\">&nbsp; &nbsp; &nbsp; 广州市普公古陶瓷博物馆（原广州普公汉代陶瓷博物馆）2013年11月8日正式开馆免费向公众开放，
     * 是一家以古代陶瓷为主题的专题博物馆，坐落在越秀区德政南路53号。
     * 瓷器是我国的伟大发明，英文名&ldquo;china&rdquo;
     * 与我国国名相同，足以体现瓷器在我国历史上对世界各国的深远影响。
     * 在今天&ldquo;一带一路&rdquo;倡议的背景下，
     * 瓷器更是中华文明中最绕不开的文化符号。因此我馆以古陶瓷蕴含的深厚文化底蕴为依托，
     * 挖掘出中华民族千年积淀的文化和智慧，让观众切实感受到中国陶瓷文化的魅力，自愿承担起传播优秀中华历史文化的责任。馆内展出藏品200余件，时间跨度从新石器时代一直到清代，从不同视角展示古代陶瓷文化的辉煌。馆内展品丰富而珍稀，大都为国家珍贵文物，是国有博物馆的很好补充。90多岁高龄的古陶瓷界泰斗耿宝昌老先生在参观完博物馆后为我馆题下馆名。
     * <\/span><\/h3>","notice":"1213","website":null,"addr":"广州市珠光街德政南路51～55号","tel":"020-23556556","custome_tel":"020-23556556","qrcode":"http://bwg.jianjiesz.com/uploads/1/2018-10-26/7777777777777.png","lng":"113.27563","lat":"23.11765","map_addr":"广州市珠光街德政南路51～55号","boss_skin_id":null,"traffic":"<p class=\"title\"><strong>交通指引<\/strong><\/p>\n<p class=\"title\"><strong>普公古陶瓷博物馆附近的公交站:<\/strong><\/p>\n<p class=\"text\">越秀南客运站、文德南、团一大广场站、东堤、珠光路总站、越秀南路、万福东、万福路、越秀南路、珠光路、文德路总站、文德路总站、南关、万福西、天字码头、白云路。<\/p>\n<p class=\"title\"><strong>普公古陶瓷博物馆附近的公交车:<\/strong><\/p>\n<p class=\"text\">广从10路快、广增17路、广从10路、128路、131A路环线、4路、57路、旅游公交2线、208路、地铁6号线、131B路环线、91路、11路、125路、35路、61路、广12路、101路、104路、184路、194路、222路、264路、36路、519路、541路、65路、80路、182路、24路、54路、7路、1路、219路、42路、13路、544路、10路、183路、S2路航线、S7路航线、192路等。<\/p>","attention":"<p class=\"text\">1、酗酒者、衣冠不整者以及无行为能力或限制行为能力者无监护人陪伴的，谢绝入馆。<\/p>\n<p class=\"text\">2、自觉接受安检，严禁将易燃易爆、管制械具等危险品带入馆内。<\/p>\n<p class=\"text\">3、参观前请将随身包裹寄存，贵重物品自行保管。<\/p>\n<p class=\"text\">4、展厅内请勿摄像;拍照时请勿使用闪光灯及三角架。<\/p>\n<p class=\"text\">5、爱护并正确使用公共设施，请勿触摸文物及展品。<\/p>\n<p class=\"text\">6、自觉维护环境卫生，请勿丢弃杂物，请勿在馆内吸烟。<\/p>\n<p class=\"text\">7、自觉遵守参观秩序，请勿大声喧哗、追跑打闹。<\/p>\n<p class=\"text\">8、如遇各类突发事件，请服从工作人员指挥。<\/p>","app_version":"1.0","deleted":"1","created":null,"modified":"1540523717"}
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
         * id : 1
         * bind_host : http://bwg.jianjiesz.com
         * name : 广州市普公古陶瓷博物馆
         * logo : /uploads/img/2018-09-13/博物馆logo.png
         * imgs : ["/uploads/img/2018-09-13/首页外景图.jpg","/uploads/1/2018-10-17/外观_副本.jpg","/uploads/1/2018-10-17/重阳.png"]
         * desc : <h3><span style="color: #333333;">&nbsp; &nbsp; &nbsp; 广州市普公古陶瓷博物馆（原广州普公汉代陶瓷博物馆）2013年11月8日正式开馆免费向公众开放，是一家以古代陶瓷为主题的专题博物馆，坐落在越秀区德政南路53号。瓷器是我国的伟大发明，英文名&ldquo;china&rdquo;与我国国名相同，足以体现瓷器在我国历史上对世界各国的深远影响。在今天&ldquo;一带一路&rdquo;倡议的背景下，瓷器更是中华文明中最绕不开的文化符号。因此我馆以古陶瓷蕴含的深厚文化底蕴为依托，挖掘出中华民族千年积淀的文化和智慧，让观众切实感受到中国陶瓷文化的魅力，自愿承担起传播优秀中华历史文化的责任。馆内展出藏品200余件，时间跨度从新石器时代一直到清代，从不同视角展示古代陶瓷文化的辉煌。馆内展品丰富而珍稀，大都为国家珍贵文物，是国有博物馆的很好补充。90多岁高龄的古陶瓷界泰斗耿宝昌老先生在参观完博物馆后为我馆题下馆名。</span></h3>
         * notice : 1213
         * website : null
         * addr : 广州市珠光街德政南路51～55号
         * tel : 020-23556556
         * custome_tel : 020-23556556
         * qrcode : http://bwg.jianjiesz.com/uploads/1/2018-10-26/7777777777777.png
         * lng : 113.27563
         * lat : 23.11765
         * map_addr : 广州市珠光街德政南路51～55号
         * boss_skin_id : null
         * traffic : <p class="title"><strong>交通指引</strong></p>
         <p class="title"><strong>普公古陶瓷博物馆附近的公交站:</strong></p>
         <p class="text">越秀南客运站、文德南、团一大广场站、东堤、珠光路总站、越秀南路、万福东、万福路、越秀南路、珠光路、文德路总站、文德路总站、南关、万福西、天字码头、白云路。</p>
         <p class="title"><strong>普公古陶瓷博物馆附近的公交车:</strong></p>
         <p class="text">广从10路快、广增17路、广从10路、128路、131A路环线、4路、57路、旅游公交2线、208路、地铁6号线、131B路环线、91路、11路、125路、35路、61路、广12路、101路、104路、184路、194路、222路、264路、36路、519路、541路、65路、80路、182路、24路、54路、7路、1路、219路、42路、13路、544路、10路、183路、S2路航线、S7路航线、192路等。</p>
         * attention : <p class="text">1、酗酒者、衣冠不整者以及无行为能力或限制行为能力者无监护人陪伴的，谢绝入馆。</p>
         <p class="text">2、自觉接受安检，严禁将易燃易爆、管制械具等危险品带入馆内。</p>
         <p class="text">3、参观前请将随身包裹寄存，贵重物品自行保管。</p>
         <p class="text">4、展厅内请勿摄像;拍照时请勿使用闪光灯及三角架。</p>
         <p class="text">5、爱护并正确使用公共设施，请勿触摸文物及展品。</p>
         <p class="text">6、自觉维护环境卫生，请勿丢弃杂物，请勿在馆内吸烟。</p>
         <p class="text">7、自觉遵守参观秩序，请勿大声喧哗、追跑打闹。</p>
         <p class="text">8、如遇各类突发事件，请服从工作人员指挥。</p>
         * app_version : 1.0
         * deleted : 1
         * created : null
         * modified : 1540523717
         */

        private String id;
        private String bind_host;
        private String name;
        private String logo;
        private String imgs;
        private String desc;
        private String notice;
        private Object website;
        private String addr;
        private String tel;
        private String custome_tel;
        private String qrcode;
        private String lng;
        private String lat;
        private String map_addr;
        private Object boss_skin_id;
        private String traffic;
        private String attention;
        private String app_version;
        private String deleted;
        private Object created;
        private String modified;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBind_host() {
            return bind_host;
        }

        public void setBind_host(String bind_host) {
            this.bind_host = bind_host;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public Object getWebsite() {
            return website;
        }

        public void setWebsite(Object website) {
            this.website = website;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getCustome_tel() {
            return custome_tel;
        }

        public void setCustome_tel(String custome_tel) {
            this.custome_tel = custome_tel;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getMap_addr() {
            return map_addr;
        }

        public void setMap_addr(String map_addr) {
            this.map_addr = map_addr;
        }

        public Object getBoss_skin_id() {
            return boss_skin_id;
        }

        public void setBoss_skin_id(Object boss_skin_id) {
            this.boss_skin_id = boss_skin_id;
        }

        public String getTraffic() {
            return traffic;
        }

        public void setTraffic(String traffic) {
            this.traffic = traffic;
        }

        public String getAttention() {
            return attention;
        }

        public void setAttention(String attention) {
            this.attention = attention;
        }

        public String getApp_version() {
            return app_version;
        }

        public void setApp_version(String app_version) {
            this.app_version = app_version;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public Object getCreated() {
            return created;
        }

        public void setCreated(Object created) {
            this.created = created;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }
    }
}

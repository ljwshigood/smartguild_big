package com.zzteck.bigbwg.impl;

import com.zzteck.bigbwg.bean.ActDetailBean;
import com.zzteck.bigbwg.bean.ActListBean;
import com.zzteck.bigbwg.bean.BwgBean;
import com.zzteck.bigbwg.bean.LoginBean;
import com.zzteck.bigbwg.bean.NearWenChuangBean;
import com.zzteck.bigbwg.bean.NearWenWuBean;

public interface IActManager extends IWebAbstractManager {

    public void IRelicLists(NearWenWuBean bean) ;

    public void IRelicWenChuangLists(NearWenChuangBean bean) ;

    public void ILogin(LoginBean bean) ;

    public void IActivityList(ActListBean bean) ;

    public void IActivityDetail(ActDetailBean bean) ;

    public void IBwgDetail(BwgBean bean) ;

}

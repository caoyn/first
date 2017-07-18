package com.icss.bean;

import java.util.Date;

public class TbPromocodeUse {
    private String promuseid;

    private String promocode;

    private String orderid;

    private Date createtime;

    private String userid;

    public String getPromuseid() {
        return promuseid;
    }

    public void setPromuseid(String promuseid) {
        this.promuseid = promuseid == null ? null : promuseid.trim();
    }

    public String getPromocode() {
        return promocode;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode == null ? null : promocode.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

    
}
package com.icss.bean;

import java.util.Date;

public class TbCustomerRemark {
    private Integer remarkid;

    private String customerid;

    private String servicetype;

    private String remarktype;

    private String attachment;

    private Date remarktime;

    private String remarkuserid;
    
    private String username;

    private String memo;

    public Integer getRemarkid() {
        return remarkid;
    }

    public void setRemarkid(Integer remarkid) {
        this.remarkid = remarkid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype == null ? null : servicetype.trim();
    }

    public String getRemarktype() {
        return remarktype;
    }

    public void setRemarktype(String remarktype) {
        this.remarktype = remarktype == null ? null : remarktype.trim();
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public Date getRemarktime() {
        return remarktime;
    }

    public void setRemarktime(Date remarktime) {
        this.remarktime = remarktime;
    }

    public String getRemarkuserid() {
        return remarkuserid;
    }

    public void setRemarkuserid(String remarkuserid) {
        this.remarkuserid = remarkuserid == null ? null : remarkuserid.trim();
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}
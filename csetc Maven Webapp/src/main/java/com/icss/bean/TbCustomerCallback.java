package com.icss.bean;

import java.util.Date;

public class TbCustomerCallback {
    private Integer callbackid;

    private String customerid;

    private String reseaon;
    
    private String reseaontext;

    private String calltime;

    private String reportuserid;
    
    private String reropttext;

    private String intention;

    private String testing;

    private String tips;

    private Date tipdate;

    private String memo;

    private String resourceid;

    private String callbacktype;

    private String status;
    
    private TbCustomer customer;

    public Integer getCallbackid() {
        return callbackid;
    }

    public void setCallbackid(Integer callbackid) {
        this.callbackid = callbackid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    public String getReseaon() {
        return reseaon;
    }

    public void setReseaon(String reseaon) {
        this.reseaon = reseaon == null ? null : reseaon.trim();
    }

    public String getCalltime() {
        return calltime;
    }

    public void setCalltime(String calltime) {
        this.calltime = calltime == null ? null : calltime.trim();
    }

    public String getReportuserid() {
		return reportuserid;
	}

	public void setReportuserid(String reportuserid) {
		this.reportuserid = reportuserid;
	}

	public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention == null ? null : intention.trim();
    }

    public String getTesting() {
        return testing;
    }

    public void setTesting(String testing) {
        this.testing = testing == null ? null : testing.trim();
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }

    public Date getTipdate() {
        return tipdate;
    }

    public void setTipdate(Date tipdate) {
        this.tipdate = tipdate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid == null ? null : resourceid.trim();
    }

    public String getCallbacktype() {
        return callbacktype;
    }

    public void setCallbacktype(String callbacktype) {
        this.callbacktype = callbacktype == null ? null : callbacktype.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public String getReseaontext() {
		return reseaontext;
	}

	public void setReseaontext(String reseaontext) {
		this.reseaontext = reseaontext;
	}

	public String getReropttext() {
		return reropttext;
	}

	public void setReropttext(String reropttext) {
		this.reropttext = reropttext;
	}

	public TbCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(TbCustomer customer) {
		this.customer = customer;
	}
	
    
    
}
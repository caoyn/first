package com.icss.bean;

import java.util.Date;
/**
 * 客户分配
 * @author miemie
 * 2017年4月28日
 */
public class TbCustomerAssign {
    private String  assignid;

    private String customerid;

    private Date assigntime;

    private Date processtime;

    private String assignuserid;
    
    private String assigntext;

    private String userid;
    
    private String usertext;

    private String status;
    
    private TbCustomer customer;
    
    public TbCustomerAssign() {
	}
    
	public TbCustomerAssign(String customerid, String status) {
		this.customerid = customerid;
		this.status = status;
	}



	public TbCustomerAssign(String customerid,
			Date assigntime, String assignuserid,
			String userid) {
		this.assignid = "";
		this.customerid = customerid;
		this.assigntime = assigntime;
		this.assignuserid = assignuserid;
		this.userid = userid;
		this.status = "0";
	}

	public String getAssignid() {
        return assignid;
    }

    public void setAssignid(String  assignid) {
        this.assignid = assignid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    public Date getAssigntime() {
        return assigntime;
    }

    public void setAssigntime(Date assigntime) {
        this.assigntime = assigntime;
    }

    public Date getProcesstime() {
        return processtime;
    }

    public void setProcesstime(Date processtime) {
        this.processtime = processtime;
    }

    public String getAssignuserid() {
        return assignuserid;
    }

    public void setAssignuserid(String assignuserid) {
        this.assignuserid = assignuserid == null ? null : assignuserid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public String getAssigntext() {
		return assigntext;
	}

	public void setAssigntext(String assigntext) {
		this.assigntext = assigntext;
	}

	public String getUsertext() {
		return usertext;
	}

	public void setUsertext(String usertext) {
		this.usertext = usertext;
	}

	public TbCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(TbCustomer customer) {
		this.customer = customer;
	}
    
    
}
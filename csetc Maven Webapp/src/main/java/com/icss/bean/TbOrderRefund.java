package com.icss.bean;

import java.util.Date;

public class TbOrderRefund {
    private String id;

    private String orderid;
    
    private String customerid;
    
    private String customername;

    private String prodid;

    private String prodname;
    
    private String price;
    
    private String amount;
    
    private String amountcount;

    private Float refund;

    private Date refundtime;

    private String refundtype;

    private String memo;

    private String userid;
    
    private String useridtext;
    
    private String curruserid;
    
    private String curruseridtext;
    
    private String nextuserid;
    
    private String nextuseridtext;

    private String deptid;

    private String status;
    
    private String astatus;
    
    private String approveid;
    
    private String mapproveid;

    private Date createtime;

    public TbOrderRefund() {
	}
    
	public TbOrderRefund(String orderid, String status) {
		this.orderid = orderid;
		this.status = status;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname == null ? null : prodname.trim();
    }

    public Float getRefund() {
        return refund;
    }

    public void setRefund(Float refund) {
        this.refund = refund;
    }

    public Date getRefundtime() {
        return refundtime;
    }

    public void setRefundtime(Date refundtime) {
        this.refundtime = refundtime;
    }

    public String getRefundtype() {
        return refundtype;
    }

    public void setRefundtype(String refundtype) {
        this.refundtype = refundtype == null ? null : refundtype.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid == null ? null : deptid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmountcount() {
		return amountcount;
	}

	public void setAmountcount(String amountcount) {
		this.amountcount = amountcount;
	}

	public String getUseridtext() {
		return useridtext;
	}

	public void setUseridtext(String useridtext) {
		this.useridtext = useridtext;
	}

	public String getCurruserid() {
		return curruserid;
	}

	public void setCurruserid(String curruserid) {
		this.curruserid = curruserid;
	}

	public String getCurruseridtext() {
		return curruseridtext;
	}

	public void setCurruseridtext(String curruseridtext) {
		this.curruseridtext = curruseridtext;
	}

	public String getNextuserid() {
		return nextuserid;
	}

	public void setNextuserid(String nextuserid) {
		this.nextuserid = nextuserid;
	}

	public String getNextuseridtext() {
		return nextuseridtext;
	}

	public void setNextuseridtext(String nextuseridtext) {
		this.nextuseridtext = nextuseridtext;
	}

	public String getApproveid() {
		return approveid;
	}

	public void setApproveid(String approveid) {
		this.approveid = approveid;
	}

	public String getMapproveid() {
		return mapproveid;
	}

	public void setMapproveid(String mapproveid) {
		this.mapproveid = mapproveid;
	}

	public String getAstatus() {
		return astatus;
	}

	public void setAstatus(String astatus) {
		this.astatus = astatus;
	}
    
}
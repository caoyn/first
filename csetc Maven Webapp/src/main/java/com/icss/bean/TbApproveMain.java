package com.icss.bean;

public class TbApproveMain {
    private String approveid;

    private String orderid;

    private String approvetype;

    private String curruserid;

    private String nextuserid;

    private String status;
    
    public TbApproveMain() {
	}
    
	public TbApproveMain(String approveid, String status) {
		this.approveid = approveid;
		this.status = status;
	}

	public String getApproveid() {
        return approveid;
    }

    public void setApproveid(String approveid) {
        this.approveid = approveid == null ? null : approveid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getApprovetype() {
        return approvetype;
    }

    public void setApprovetype(String approvetype) {
        this.approvetype = approvetype == null ? null : approvetype.trim();
    }

    public String getCurruserid() {
        return curruserid;
    }

    public void setCurruserid(String curruserid) {
        this.curruserid = curruserid == null ? null : curruserid.trim();
    }

    public String getNextuserid() {
        return nextuserid;
    }

    public void setNextuserid(String nextuserid) {
        this.nextuserid = nextuserid == null ? null : nextuserid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}
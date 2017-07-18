package com.icss.bean;

import java.util.Date;

public class TbOrderChanged {
    private String id;

    private String oederid;

    private String userid;

    private String deptid;

    private String changeuserid;

    private String reason;

    private String memo;

    private Date createtime;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOederid() {
        return oederid;
    }

    public void setOederid(String oederid) {
        this.oederid = oederid == null ? null : oederid.trim();
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

    public String getChangeuserid() {
        return changeuserid;
    }

    public void setChangeuserid(String changeuserid) {
        this.changeuserid = changeuserid == null ? null : changeuserid.trim();
    }

    public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}
package com.icss.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TbSysApprove {
    private String approveid;

    private String flowname;

    private String flowtype;

    private Integer flowversion;

    private String userid;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date createtime;

    private String status;

    public String getApproveid() {
        return approveid;
    }

    public void setApproveid(String approveid) {
        this.approveid = approveid == null ? null : approveid.trim();
    }

    public String getFlowname() {
        return flowname;
    }

    public void setFlowname(String flowname) {
        this.flowname = flowname == null ? null : flowname.trim();
    }

    public String getFlowtype() {
        return flowtype;
    }

    public void setFlowtype(String flowtype) {
        this.flowtype = flowtype == null ? null : flowtype.trim();
    }

    public Integer getFlowversion() {
        return flowversion;
    }

    public void setFlowversion(Integer flowversion) {
        this.flowversion = flowversion;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
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
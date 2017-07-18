package com.icss.bean;

import java.util.Date;

public class TbClassbaseinfo {
    private Integer id;

    private String classid;

    private String classdesc;

    private Date opendate;

    private Integer opennumber;

    private String userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }

    public String getClassdesc() {
        return classdesc;
    }

    public void setClassdesc(String classdesc) {
        this.classdesc = classdesc == null ? null : classdesc.trim();
    }

    public Date getOpendate() {
        return opendate;
    }

    public void setOpendate(Date opendate) {
        this.opendate = opendate;
    }

    public Integer getOpennumber() {
        return opennumber;
    }

    public void setOpennumber(Integer opennumber) {
        this.opennumber = opennumber;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}
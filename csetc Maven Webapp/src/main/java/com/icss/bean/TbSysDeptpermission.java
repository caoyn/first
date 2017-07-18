package com.icss.bean;

import java.util.Date;

public class TbSysDeptpermission {
    private String id;

    private String deptid;

    private String prodenable;

    private Date createtime;

    private Date updatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid == null ? null : deptid.trim();
    }

    public String getProdenable() {
        return prodenable;
    }

    public void setProdenable(String prodenable) {
        this.prodenable = prodenable == null ? null : prodenable.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
package com.icss.bean;

public class TbSysUserroledetail {
    private String userroleid;

    private String userid;

    private String roleid;

    private String permissionid;

    private String operid;
    
    public TbSysUserroledetail() {
	}

	public TbSysUserroledetail(String userroleid, String userid, String roleid,
			String permissionid, String operid) {
		this.userroleid = userroleid;
		this.userid = userid;
		this.roleid = roleid;
		this.permissionid = permissionid;
		this.operid = operid;
	}

	public String getUserroleid() {
        return userroleid;
    }

    public void setUserroleid(String userroleid) {
        this.userroleid = userroleid == null ? null : userroleid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(String permissionid) {
        this.permissionid = permissionid == null ? null : permissionid.trim();
    }

    public String getOperid() {
        return operid;
    }

    public void setOperid(String operid) {
        this.operid = operid == null ? null : operid.trim();
    }
}
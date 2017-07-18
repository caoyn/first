package com.icss.bean;

public class TbSysRolepermission {
    private String id;

    private String roleid;

    private String permissionid;

    private String operid;

    public TbSysRolepermission() {
	
    }

	public TbSysRolepermission(String id, String roleid, String permissionid,
			String operid) {
		this.id = id;
		this.roleid = roleid;
		this.permissionid = permissionid;
		this.operid = operid;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
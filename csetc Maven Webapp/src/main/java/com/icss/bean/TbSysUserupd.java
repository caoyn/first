package com.icss.bean;

import java.util.Date;

public class TbSysUserupd {
    private String id;

    private String userid;
    
    private String username;

    private Date createtime;

    private String updtype;

    private String memo;
    
    public TbSysUserupd() {
	}

	public TbSysUserupd(String userid, String updtype, String memo) {
		this.userid = userid;
		this.updtype = updtype;
		this.memo = memo;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdtype() {
        return updtype;
    }

    public void setUpdtype(String updtype) {
        this.updtype = updtype == null ? null : updtype.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}
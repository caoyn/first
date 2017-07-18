package com.icss.bean;

import java.util.Date;

public class TbSysUser {
    private String id;

    private String userid;

    private String loginname;

    private String userpassword;

    private String passtype;

    private Date passexpire;

    private String username;

    private String cardid;

    private String mobile;

    private String telephone;

    private String address;

    private String email;

    private String description;

    private Date createtime;

    private Date joinustime;

    private Date leavetime;

    private Date lastlogintime;

    private Integer logincount;

    private Integer loginfailedcount;

    private String deptid;

    private String usertype;
    
    private String roleid;
    
    private String rolename;
    
    private String roledesc;

    private Integer status;
    
    private String deptname;

    public TbSysUser() {
	}

	public TbSysUser(String id, String deptid, String usertype) {
		this.id = id;
		this.deptid = deptid;
		this.usertype = usertype;
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

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public String getPasstype() {
        return passtype;
    }

    public void setPasstype(String passtype) {
        this.passtype = passtype == null ? null : passtype.trim();
    }

    public Date getPassexpire() {
        return passexpire;
    }

    public void setPassexpire(Date passexpire) {
        this.passexpire = passexpire;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getJoinustime() {
        return joinustime;
    }

    public void setJoinustime(Date joinustime) {
        this.joinustime = joinustime;
    }

    public Date getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(Date leavetime) {
        this.leavetime = leavetime;
    }

    public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}


    public Integer getLogincount() {
        return logincount;
    }

    public void setLogincount(Integer logincount) {
        this.logincount = logincount;
    }

    public Integer getLoginfailedcount() {
        return loginfailedcount;
    }

    public void setLoginfailedcount(Integer loginfailedcount) {
        this.loginfailedcount = loginfailedcount;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid == null ? null : deptid.trim();
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype == null ? null : usertype.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoledesc() {
		return roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
    
}
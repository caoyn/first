package com.icss.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class TbProdBase {
    private String id;

    private String prodid;

    private String prodname;

    private String prodtype;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date createtime;

    private Integer prodver;

    private String status;
    
    private List<TbProdDetail> proddetail = new ArrayList<TbProdDetail>();

    public TbProdBase() {
	}

	public TbProdBase(String prodid, String prodname, String prodtype, Integer prodver, String status) {
		this.prodid = prodid;
		this.prodname = prodname;
		this.prodtype = prodtype;
		this.prodver = prodver;
		this.status = status;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getProdtype() {
        return prodtype;
    }

    public void setProdtype(String prodtype) {
        this.prodtype = prodtype == null ? null : prodtype.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getProdver() {
        return prodver;
    }

    public void setProdver(Integer prodver) {
        this.prodver = prodver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public List<TbProdDetail> getProddetail() {
		return proddetail;
	}

	public void setProddetail(List<TbProdDetail> proddetail) {
		this.proddetail = proddetail;
	}

	/*public Set<TbProdDetail> getProddetail() {
		return proddetail;
	}

	public void setProddetail(Set<TbProdDetail> proddetail) {
		this.proddetail = proddetail;
	}*/
    
    
}
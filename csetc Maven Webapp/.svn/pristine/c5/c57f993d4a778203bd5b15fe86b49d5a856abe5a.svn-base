package com.icss.bean;

import java.util.Date;

public class TbOrderPayment implements Comparable<TbOrderPayment> {
    private String id;

    private String orderid;

    private String prodid;

    private String prodname;

    private Float amount;

    private String userid;

    private String userdept;

    private Date paymenttime;

    private String paymethod;

    private String bankingbook;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
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

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUserdept() {
        return userdept;
    }

    public void setUserdept(String userdept) {
        this.userdept = userdept == null ? null : userdept.trim();
    }

    public Date getPaymenttime() {
        return paymenttime;
    }

    public void setPaymenttime(Date paymenttime) {
        this.paymenttime = paymenttime;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod == null ? null : paymethod.trim();
    }

	public String getBankingbook() {
		return bankingbook;
	}

	public void setBankingbook(String bankingbook) {
		this.bankingbook = bankingbook;
	}

	@Override  
    public int compareTo(TbOrderPayment o) {  
        int i = Integer.parseInt(this.getId()) - Integer.parseInt(o.getId());//先按照id(月份)排序
        return i;  
    }  
}
package com.icss.bean;

import java.util.Date;

public class TbCustomerReservation {
    private Integer reservationid;

    private String customerid;

    private String reportuserid;
    
    private String reporttext;

    private String purpose;
    
    private String purposetext;

    private String zone;
    
    private String zonetext;

    private String receiver;
    
    private String receivertext;

    private Date reservationtime;

    private Date expecttime;

    private Date arrivetime;

    private String memo;

    private String status;

    private TbCustomer customer;
    
    public TbCustomerReservation() {
	}
    
	public TbCustomerReservation(String customerid, String status) {
		this.customerid = customerid;
		this.status = status;
	}



	public Integer getReservationid() {
        return reservationid;
    }

    public void setReservationid(Integer reservationid) {
        this.reservationid = reservationid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    public String getReportuserid() {
        return reportuserid;
    }

    public void setReportuserid(String reportuserid) {
        this.reportuserid = reportuserid == null ? null : reportuserid.trim();
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone == null ? null : zone.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public Date getReservationtime() {
        return reservationtime;
    }

    public void setReservationtime(Date reservationtime) {
        this.reservationtime = reservationtime;
    }

    public Date getExpecttime() {
        return expecttime;
    }

    public void setExpecttime(Date expecttime) {
        this.expecttime = expecttime;
    }

    public Date getArrivetime() {
        return arrivetime;
    }

    public void setArrivetime(Date arrivetime) {
        this.arrivetime = arrivetime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public TbCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(TbCustomer customer) {
		this.customer = customer;
	}

	public String getReporttext() {
		return reporttext;
	}

	public void setReporttext(String reporttext) {
		this.reporttext = reporttext;
	}

	public String getReceivertext() {
		return receivertext;
	}

	public void setReceivertext(String receivertext) {
		this.receivertext = receivertext;
	}

	public String getPurposetext() {
		return purposetext;
	}

	public void setPurposetext(String purposetext) {
		this.purposetext = purposetext;
	}

	public String getZonetext() {
		return zonetext;
	}

	public void setZonetext(String zonetext) {
		this.zonetext = zonetext;
	}
    
    
}
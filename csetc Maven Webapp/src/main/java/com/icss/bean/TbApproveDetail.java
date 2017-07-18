package com.icss.bean;

import java.util.Date;

public class TbApproveDetail {
    private String approvedetailid;

    private String approveid;

    private String userid;
    
    private String useridtext;

    private Date approvetime;

    private String approveresult;

    private String memo;
    
    public TbApproveDetail() {
	}

    public TbApproveDetail(String approveid, String userid,
			String approveresult, String memo) {
		this.approveid = approveid;
		this.userid = userid;
		this.approveresult = approveresult;
		this.memo = memo;
	}

	public String getApprovedetailid() {
        return approvedetailid;
    }

    public void setApprovedetailid(String approvedetailid) {
        this.approvedetailid = approvedetailid == null ? null : approvedetailid.trim();
    }

    public String getApproveid() {
        return approveid;
    }

    public void setApproveid(String approveid) {
        this.approveid = approveid == null ? null : approveid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Date getApprovetime() {
        return approvetime;
    }

    public void setApprovetime(Date approvetime) {
        this.approvetime = approvetime;
    }

    public String getApproveresult() {
        return approveresult;
    }

    public void setApproveresult(String approveresult) {
        this.approveresult = approveresult == null ? null : approveresult.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public String getUseridtext() {
		return useridtext;
	}

	public void setUseridtext(String useridtext) {
		this.useridtext = useridtext;
	}
    
    
}
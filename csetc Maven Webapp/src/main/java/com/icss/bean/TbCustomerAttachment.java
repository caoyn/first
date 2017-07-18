package com.icss.bean;

import java.util.Date;

/**
 * 申请入班学员提交图片实体类描述
 * @author caoyanan
 * @time 2017年7月13日下午3:45:24
 * @description
 */
public class TbCustomerAttachment {
	
	// 学员附件表主键id
	private String id;

	// 学员编号
	private String customerid;

	// 附件图片类型(应该存储在TB_SYS_BASECODE中,页面用下拉框)
	private String atype;

	// 附件图片的说明
	private String amemo;

	// 图片所存储的服务器路径
	private String aurl;

	// 创建人
	private String creator;

	// 创建时间
	private String createtime;

	// 状态(0无效, 1有效)
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid == null ? null : customerid.trim();
	}

	public String getAtype() {
		return atype;
	}

	public void setAtype(String atype) {
		this.atype = atype == null ? null : atype.trim();
	}

	public String getAmemo() {
		return amemo;
	}

	public void setAmemo(String amemo) {
		this.amemo = amemo == null ? null : amemo.trim();
	}

	public String getAurl() {
		return aurl;
	}

	public void setAurl(String aurl) {
		this.aurl = aurl == null ? null : aurl.trim();
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator == null ? null : creator.trim();
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}
}

package com.icss.bean;

/**
 * 客户资质pojo实体类
 * @author caoyanan
 * @time 2017年7月18日上午11:37:45
 * @description
 * 订单信息：订单号orderId
 * 客户资质附件信息：资质主键id,客户编号customerId,资质类型qualificationTypeId,
 * 资质说明qualificationMemo,资质地址 qualificationUrl
 * 创建人 creator,创建时间createTime,状态status
 */
public class CustomerQualification {

	//资质主键
	public String id;
	//客户编号
	public String customerId;
	//资质类型id
	public String qualificationTypeId;
	//资质说明
	public String qualificationMemo;
	//资质地址
	public String qualificationUrl;
	//状态
	public String status;
	
	//创建人
	public String creator;
	//创建时间
	public String createTime;
	
	//订单号
	public String orderId;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getQualificationTypeId() {
		return qualificationTypeId;
	}

	public void setQualificationTypeId(String qualificationTypeId) {
		this.qualificationTypeId = qualificationTypeId;
	}

	public String getQualificationMemo() {
		return qualificationMemo;
	}

	public void setQualificationMemo(String qualificationMemo) {
		this.qualificationMemo = qualificationMemo;
	}

	public String getQualificationUrl() {
		return qualificationUrl;
	}

	public void setQualificationUrl(String qualificationUrl) {
		this.qualificationUrl = qualificationUrl;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerQualification [id=" + id + ", customerId=" + customerId + ", qualificationTypeId="
				+ qualificationTypeId + ", qualificationMemo=" + qualificationMemo + ", qualificationUrl="
				+ qualificationUrl + ", status=" + status + ", creator=" + creator + ", createTime=" + createTime
				+ ", orderId=" + orderId + "]";
	}

	
	
}

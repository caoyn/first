package com.icss.bean;

/**
 * 得到入班审核许可的学员pojo实体类
 * @author caoyanan
 * @time 2017年7月14日上午9:49:26
 * @description
 * 学员描述：
 * 学员信息：学员主键id，学员编号，学员姓名
 * 订单信息：订单号，所属销售姓名，订单状态(1012 已收款，未全款 1013 已全款)
 */
public class QualifiedStudent {

	/*
	 * 学员信息
	 */
	//主键id
	public String stuId;
	//学员编号
	public String stuNo;
	//学员姓名
	public String stuName;
	
	/*
	 * 订单信息
	 */
	//订单号
	public String orderId;
	//所属销售姓名
	public String salesName;
	//订单状态
	public String orderStatus;
	
	
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSalesName() {
		return salesName;
	}
	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "QualifiedStudent [stuId=" + stuId + ", stuNo=" + stuNo + ", stuName=" + stuName + ", orderId=" + orderId
				+ ", salesName=" + salesName + ", orderStatus=" + orderStatus + "]";
	}


	
}
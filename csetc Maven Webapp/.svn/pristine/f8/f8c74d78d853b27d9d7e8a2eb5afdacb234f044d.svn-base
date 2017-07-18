package com.icss.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.bean.TbProdBase;
import com.icss.bean.TbProdDetail;
import com.icss.bean.TbSysDeptpermission;


public interface SysProdBaseService {
	int addProductBaseInfo(HttpServletRequest request,List<TbProdDetail> list);
	
	List<TbProdBase> getAllProductData();
	
	int updProdBase(TbProdDetail prodDetail, TbProdBase prodBase);
	
	int delProdBase(HttpServletRequest request);
	
	TbProdBase getOneProdBaseData(String id);
	
	List<TbProdBase> judgeExistProduct(HttpServletRequest request);
	
	List<TbProdDetail> getProductByProductid(HttpServletRequest request);
	
	List<TbProdDetail> getAllViableProduct();
	
	TbSysDeptpermission getDeptProductPermission(String deptid);
	
	int addOrUpdProdPermission(HttpServletRequest request);
	
	List<TbProdBase> getAllProdData();
	
	TbProdBase getProductBySubid(String subprodid);
	
	List<TbProdDetail> getProdDetailById(HttpServletRequest request);
	
	int addSubProduct(TbProdDetail prodDetail);
	
	String tb_prod_base_id();
	
	TbProdBase getProdByPid(HttpServletRequest request);
	
	int updProdByPid(TbProdBase prodBase);
	
	TbProdDetail getSubProdBySid(HttpServletRequest request);
	
	int updateByProdid(TbProdDetail prodDetail);
	
	List<TbProdBase> getAllEnableProd();
	
	String selectAllProdsData();
 }

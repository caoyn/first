/**
 * 文件名: SysDeptImpl.java
 * 描述:部门接口的实现类
 * 所属:湖南中软计算机系统服务有限公司
 * 开发人员：李敏 
 * 创建时间：2017-03-21 
 */
package com.icss.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.icss.bean.TbProdBase;
import com.icss.bean.TbProdDetail;
import com.icss.bean.TbSysDeptpermission;
import com.icss.dao.TbProdBaseMapper;
import com.icss.dao.TbProdDetailMapper;
import com.icss.dao.TbSysDeptpermissionMapper;
import com.icss.service.SysProdBaseService;

@Service("SysProdBaseImpl")
public  class SysProdBaseImpl implements SysProdBaseService {
	
	@Autowired
	public TbProdBaseMapper ProdBaseMapper;
	
	@Autowired
	public TbProdDetailMapper ProdDetailMapper;
	
	@Autowired
	public TbSysDeptpermissionMapper DeptpermissionMapper;

	@Override
	public int addProductBaseInfo(HttpServletRequest request,List<TbProdDetail> list) {
		// 获得一条产品的基本信息
		int result = 0 ;
		
		/*try {
			String prodname = new String (request.getParameter("prodname").getBytes("ISO-8859-1"),"UTF-8");
			TbProdBase pb = new TbProdBase(request.getParameter("prodid"),prodname,request.getParameter("prodtype"),Integer.parseInt(request.getParameter("prodver")),request.getParameter("status"));
			result = ProdBaseMapper.insert(pb);//新增一条产品基础信息
			TbProdDetail d = new TbProdDetail(pb.getProdid(), pb.getProdid(), pb.getProdname(), 0.0f, 0.0f, 0.0f, "0");
			ProdDetailMapper.insert(d);//新增与之对应的详情表
			String pd_roleid = pb.getProdid();//获得上级父产品的编号
			if(list.size()>0){
				for(int i = 0; i < list.size(); i++){//新增与之对应的子产品信息
					TbProdDetail pd = list.get(i);
					if(pd.getSubprodid() == null){
						continue;
					}
				
					pb.setProdid(pd.getSubprodid());//设置编号
					pb.setProdname(pd.getSubprodname());//设置名称
					ProdBaseMapper.insert(pb);//新增一条产品基础信息
					pd.setProdid(pd_roleid); //给父级编号赋值
					result = ProdDetailMapper.insert(pd);//新增与之对应的详情表
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//新增产品基础信息
		try {
			String prodname = new String (request.getParameter("prodname").getBytes("ISO-8859-1"),"UTF-8");
			TbProdBase pb = new TbProdBase("",prodname,request.getParameter("prodtype"),1,request.getParameter("status"));
			result = ProdBaseMapper.insert(pb);//新增一条产品基础信息
			String prodid = ProdBaseMapper.tb_prod_base_id();
			//新增子产品信息
			if(list.size()>0){
				for(int i = 0; i < list.size(); i++){
					TbProdDetail pd = list.get(i);
					pd.setProdid(prodid); //给父级编号赋值
					result = ProdDetailMapper.insert(pd);//新增与之对应的详情表
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public List<TbProdBase> getAllProductData() {
		// 查看所有的产品信息
		return ProdBaseMapper.getAllProductData();
	}

	@Override
	public int updProdBase(TbProdDetail detail,  TbProdBase prodBase) {
		// 更改产品的基础信息
		int result = 0;
		if(prodBase.getProdname() == null || "null".equals(prodBase.getProdname())){
			prodBase.setProdname(detail.getSubprodname());
			prodBase.setProdid(detail.getSubprodid());
			ProdBaseMapper.updateByProdid(prodBase);
			result = ProdDetailMapper.updateByProdid(detail);//更新细节表
		}else{
			ProdBaseMapper.updateByProdid(prodBase) ;//更新基础表
			detail.setSubprodname(prodBase.getProdname());
			detail.setSubprodid(prodBase.getProdid());
			result = ProdDetailMapper.updateByProdid(detail);//更新细节表
		}
		return result;
	}

	@Override
	public int delProdBase(HttpServletRequest request) {
		// 根据编号删除某产品
		// 1.查看是否有子产品 2 。没有就进行删除
		/*String prodid = request.getParameter("prodid");
		String did = request.getParameter("did");
		if(ProdDetailMapper.getProdDetailById(prodid).size() == 0){
			System.out.println(prodid);
			ProdDetailMapper.deleteByPrimaryKey(did);//先删除详情表数据
			return ProdBaseMapper.delProdByPid(prodid);//删除基础表数据
		}*/
		//先删除详情表数据
		String prodid = request.getParameter("prodid");
		ProdDetailMapper.udlProdDetailByPid(prodid);
		//删除主表数据
		return ProdBaseMapper.delProdByPid(prodid);
	}

	@Override
	public TbProdBase getOneProdBaseData(String id) {
		// 获得一个产品的基本信息
		return ProdBaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TbProdBase> judgeExistProduct(HttpServletRequest request) {
		// 判断是否存在该产品
		return ProdBaseMapper.judgeExistProduct(request.getParameter("str"));
	}

	@Override
	public List<TbProdDetail> getProductByProductid(HttpServletRequest request) {
		// 根据产品编号获得子产品信息
//		return ProdBaseMapper.getProductByProductid(request.getParameter("prodid"));
		return ProdDetailMapper.getProdDetailByPid(request.getParameter("prodid"));
	}

	@Override
	public List<TbProdDetail> getAllViableProduct() {
		// 查看可以使用的产品信息
		return ProdDetailMapper.getAllViableProduct();
	}
	
	@Override
	public TbSysDeptpermission getDeptProductPermission(String deptid) {
		// 根据部门编号查看对应的可卖的产品
		return DeptpermissionMapper.getDeptProductPermission(deptid);
	}

	@Override
	public int addOrUpdProdPermission(HttpServletRequest request) {
		//如果有该部门的授权产品信息就更新。没有就新增
		String deptid = request.getParameter("deptid");
		TbSysDeptpermission dp = new TbSysDeptpermission();
		dp.setDeptid(deptid);
		dp.setProdenable(request.getParameter("prodenable"));
		int result = 0;
		TbSysDeptpermission d = DeptpermissionMapper.getDeptProductPermission(deptid);
		if("null".equals(d) || d == null){
			//新增
			result = DeptpermissionMapper.insert(dp);
		}else{
			//更新
			dp.setId(d.getId());
			result =DeptpermissionMapper.updateByPrimaryKeySelective(dp);
		}
		return result;
	}

	@Override
	public List<TbProdBase> getAllProdData() {
		// 查看所有产品表格信息用于生成表格数据
		return ProdBaseMapper.getAllProdData();
	}

	@Override
	public TbProdBase getProductBySubid(String subprodid) {
		// 根据产品子编号查看产品数据
		return ProdBaseMapper.getProductBySubid(subprodid);
	}

	@Override
	public List<TbProdDetail> getProdDetailById(HttpServletRequest request) {
		// 根据产品编号查看子产品
		String id = request.getParameter("prodid");
		return ProdDetailMapper.getProdDetailById(id);
	}

	
	@Override
	public int addSubProduct(TbProdDetail prodDetail) {
		//根据产品编号查看产品基本表数据
		/*List<TbProdBase> list = ProdBaseMapper.getProductByProductid(prodDetail.getProdid());
		if(list.size() == 1){
			TbProdBase tb = new TbProdBase();
			tb = list.get(0);
			tb.setProdid(prodDetail.getSubprodid());
			tb.setProdname(prodDetail.getSubprodname());
			tb.setCreatetime(null);
			ProdBaseMapper.insert(tb);//新增产品基础信息
			return ProdDetailMapper.insert(prodDetail);//新增产品详情
		}else{
			return 0;
		}*/
		
		return ProdDetailMapper.insert(prodDetail);
		
	}

	@Override
	public String tb_prod_base_id() {
		// TODO Auto-generated method stub
		return ProdBaseMapper.tb_prod_base_id();
	}

	@Override
	public TbProdBase getProdByPid(HttpServletRequest request) {
		// 根据产品编号查看产品数据
		return ProdBaseMapper.getProdByPid(request.getParameter("prodid"));
	}
	
	@Override
	public int updProdByPid(TbProdBase prodBase) {
		// 根据产品编号查看产品数据
		return ProdBaseMapper.updProdByPid(prodBase);
	}

	@Override
	public TbProdDetail getSubProdBySid(HttpServletRequest request) {
		// 获得子产品详情信息
		return ProdDetailMapper.getSubProdBySid(request.getParameter("subprodid"));
	}

	@Override
	public int updateByProdid(TbProdDetail prodDetail) {
		// 修改子产品详情
		return ProdDetailMapper.updateByProdid(prodDetail);
	}

	@Override
	public List<TbProdBase> getAllEnableProd() {
		// 可用的产品数据（下拉框）
		return ProdBaseMapper.getAllEnableProd();
	}

	@Override
	public String selectAllProdsData() {
		// 产品详情（是否允许折扣）
		return JSON.toJSONString(ProdDetailMapper.selectAllProdsData());
	}
	

}

package com.icss.util;

import java.util.Calendar;
import java.util.Date;

public class HandleId {
	
	    //创建编号的方法
		public static String createid(String id,String strle,int cutle){
			int subid;
			if(id == null || id.length() == 0 || id.equals("0")){
				subid = 1;
			}else{
				subid = Integer.parseInt(id.substring(cutle)) + 1;
				//System.out.println();
			}		 
			return String.format(strle, subid);
		}
		
		//创建部门编号的方法
		public static String createdeptid(String id,String strle,int cutle){		
			String ie = id.substring(0,id.length()-2) ;//去掉最后的两位数字
			String rn = id.substring(id.length()-2,id.length()) ;//取最后两位数字		
			//System.out.println(rn + " ********** " + ie);
			return ie + createid(rn,strle,cutle);
		}
		
		//创建基础代码小类的方法
		public static String createbasecodeid(String id){
			if(id == null || id.length() == 0 || id.equals("0")){
				return "1";
			}
			return (Integer.parseInt(id) + 1) + "";
		}
		
		//获得今日今后90天的日期
		public static Date getdate(int i) {// //获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
			Date dat = null;
			Calendar cd = Calendar.getInstance();
			cd.add(Calendar.DATE, i);
			dat = cd.getTime();
			return dat;
		}

}

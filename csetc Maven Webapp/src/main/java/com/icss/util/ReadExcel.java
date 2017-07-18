package com.icss.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.icss.bean.TbCustomer;
import com.icss.dao.TbSysBasecodeMapper;

public class ReadExcel {
	 private static String EXCEL_2003 = ".xls";
	 private static String EXCEL_2007 = ".xlsx";
	 
	 @Autowired
	 public TbSysBasecodeMapper SysBasecodeMapper;//基础代码数据
		

	public ReadExcel() {
		// TODO Auto-generated constructor stub
	}

	/**
     * 通过POI方式读取Excel(导入客户信息)
     * @param excelFile
	 * @throws Exception 
     */
	public List<TbCustomer> ReadInExcel(String path,HttpSession session) throws Exception{
		TbCustomer TbCustomer=null;
		List<TbCustomer> list = new ArrayList<TbCustomer>();//读取表格的数据
		List<TbCustomer> errorlist = new ArrayList<TbCustomer>();//表格中错误的数据集合
		
		File excelFile = new File(path);
        String fileName = excelFile.getName();
		InputStream is = new FileInputStream(path);	
		
		
		String repeatCount = ""; //出现重复数据的行数计数
		String errorCount = "" ; //出现错误数据的行数
		String ageCount = ""; //不符合年龄
		String educationCount = ""; //不符合学历  
       //  Workbook  	XssFworkbook = new XSSFWorkbook(is);
        
		//EXCEL_2003
		if(fileName.toLowerCase().endsWith(EXCEL_2003)){
		 Workbook   Hssfworkbook = new HSSFWorkbook(is);   
         Integer sheetNum = Hssfworkbook.getNumberOfSheets();
         System.out.println(sheetNum+2003);
         
         
         for (int i= 0; i < sheetNum; i++) {// 循环工作表Sheet
	            HSSFSheet hssfSheet = (HSSFSheet) Hssfworkbook.getSheetAt(i);
	            if (hssfSheet == null) {
	                continue;
	            }// 循环行Row	   
	          //获得表头数据
	            HSSFRow hssfHead = hssfSheet.getRow(0);
	            boolean flag = false;
	            for(int j = 0; j< 26; j++){
	            	String head = getValue(hssfHead.getCell(j));
	            	switch (head) {
					case "姓名": flag = j == 0?true:false;break;
					case "姓别": flag = j == 1?true:false;break;
					case "电话": flag = j == 2?true:false;break;
					case "qq/邮箱": flag = j == 3?true:false;break;
					case "学校": flag = j == 4?true:false;break;
					case "专业": flag = j == 5?true:false;break;
					case "学历": flag = j == 6?true:false;break;
					case "采集人": flag = j == 7?true:false;break;
					case "来源渠道": flag = j == 8?true:false;break;
					case "客户类型": flag = j == 9?true:false;break;
					case "意向课程": flag = j == 10?true:false;break;
					case "求职意向": flag = j == 11?true:false;break;
					case "年龄": flag = j == 12?true:false;break;
					case "户口": flag = j == 13?true:false;break;
					case "现居地": flag = j == 14?true:false;break;
					case "政治面貌": flag = j == 15?true:false;break;
					case "民族": flag = j == 16?true:false;break;
					case "婚育状况": flag = j == 17?true:false;break;
					case "工作年限": flag = j == 18?true:false;break;
					case "工作经历": flag = j == 19?true:false;break;
					case "期望工作地点": flag = j == 20?true:false;break;
					case "期望薪资": flag = j == 21?true:false;break;
					case "紧急联系方式": flag = j == 22?true:false;break;
					case "开户行": flag = j == 23?true:false;break;
					case "银行卡号": flag = j == 24?true:false;break;
					case "备注": flag = j == 25?true:false;break;
					case "": flag = false;break;
					default: break;
					}
	            	if(!flag){
	        			break;
	            	}
	            }
	            
	            if(!flag){//判断表头是否正确
	            	TbCustomer cus = new TbCustomer(); //错误数据对象
        			cus.setCustomerid("导入的文件数据错误。请仔细核对表头和与之对应的数据后再导入");
        			errorlist.add(cus);
            		return errorlist;
	            }
	            for(int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++){
	            	HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	            	if (hssfRow!= null) {
	            		TbCustomer= new TbCustomer();
	            		HSSFCell cuatomername=hssfRow.getCell(0);
	            		HSSFCell sex=hssfRow.getCell(1);
	            		HSSFCell telephone=hssfRow.getCell(2);
	            		HSSFCell email=hssfRow.getCell(3);
	            		HSSFCell school=hssfRow.getCell(4);
	            		HSSFCell major=hssfRow.getCell(5);
	            		HSSFCell education=hssfRow.getCell(6);
	            		HSSFCell collector=hssfRow.getCell(7);
	            		HSSFCell source=hssfRow.getCell(8);
	            		HSSFCell customertype=hssfRow.getCell(9);
	            		HSSFCell course=hssfRow.getCell(10);
	            		HSSFCell jobobjective=hssfRow.getCell(11);
	            		HSSFCell birthday=hssfRow.getCell(12);
	            		HSSFCell placeofbirth=hssfRow.getCell(13);
	            		HSSFCell address=hssfRow.getCell(14);
	            		HSSFCell politicalstatus=hssfRow.getCell(15);
	            		HSSFCell nayionality=hssfRow.getCell(16);
	            		HSSFCell maritalstatus=hssfRow.getCell(17);
	            		HSSFCell workservice=hssfRow.getCell(18);
	            		HSSFCell workexp=hssfRow.getCell(19);
	            		HSSFCell workplace=hssfRow.getCell(20);
	            		HSSFCell salary=hssfRow.getCell(21);
	            		HSSFCell ecp=hssfRow.getCell(22);
	            		HSSFCell bank=hssfRow.getCell(23);
	            		HSSFCell bankno=hssfRow.getCell(24);
	            		HSSFCell memo=hssfRow.getCell(25);
	            		
	            		
	            		TbCustomer.setCustomername(getValue(cuatomername));
	            		TbCustomer.setSex(getValue(sex));
	            		TbCustomer.setTelephone(getValue(telephone));
	            		TbCustomer.setEmail(getValue(email));
	            		TbCustomer.setSchool(getValue(school));
	            		TbCustomer.setMajor(getValue(major));
	            	
	            		TbCustomer.setEducation(getValue(education));
	            		TbCustomer.setSource(getValue(source));            		
	            		TbCustomer.setCustomertype(getValue(customertype));
	            		TbCustomer.setCollector(getValue(collector));
	            		TbCustomer.setCourse(getValue(course));
	            	
	            		TbCustomer.setJobobjective(getValue(jobobjective));
//	            	
	            	
	            		TbCustomer.setPlaceofbirth(getValue(placeofbirth));
	            		TbCustomer.setAddress(getValue(address));
	            		
	            		TbCustomer.setPoliticalstatus(getValue(politicalstatus));
	            		TbCustomer.setNationality(getValue(nayionality));
	            		TbCustomer.setMaritalstatus(getValue(maritalstatus));
	            		TbCustomer.setWorkservice(getValue(workservice));
	            		TbCustomer.setWorkexp(getValue(workexp));
	            		TbCustomer.setWorkplace(getValue(workplace));
	            		TbCustomer.setSalary(getValue(salary));
	            		TbCustomer.setEcp(getValue(ecp));
	            		TbCustomer.setWorkplace(getValue(workplace));
	            		TbCustomer.setBank(getValue(bank));
	            		TbCustomer.setBankno(getValue(bankno));
	            		TbCustomer.setMemo(getValue(memo));
	            		
	            		TbCustomer.setOperator((String) session.getAttribute("userid"));
	            		TbCustomer.setStatus("1");
            			TbCustomer.setAssigntype("0");
            			if(getValue(birthday)!= null && !"".equals(getValue(birthday))){
            				int age = Integer.parseInt(getValue(birthday).trim());
            				if(age > 28 || age < 18){
            					ageCount += (rowNum + 1) + "行:" + age +",";
            				}
            				TbCustomer.setBirthday(getBirthday(age,0,1));
            			}
            			//判断文件中电话号码是否正确
	            		for (TbCustomer c : list) {
							if(TbCustomer.getTelephone().equals(c.getTelephone())){
								repeatCount += (rowNum + 1) + ":" + c.getTelephone() +",";
							}
						}
	            		//判断文件中电话号码的格式是否哦正确
	            		if(!TbCustomer.getTelephone().matches("[0-9]{1,}") || TbCustomer.getTelephone().length() != 11){//判断一个字符串(电话号码)是否为纯数字和长度是否为11
	            			//	System.out.println( c.getTel().length());
	            				errorCount += (rowNum + 1) + ":" + TbCustomer.getTelephone() +",";
	            		}
	            		
	            		//性别处理
	            		if("女".equals(TbCustomer.getSex()) || "0".equals(TbCustomer.getSex())){
	            			TbCustomer.setSex("0");
	            		}else{
	            			TbCustomer.setSex("1");
	            		}
	            		
	            		//学历
	            		if("大专，本科，博士，硕士".indexOf(TbCustomer.getEducation())<0){
	            			educationCount +=  (rowNum + 1) + ":" + TbCustomer.getEducation() +",";
	            		}
	            		list.add(TbCustomer);
	            	}
	            	
	            }
         	}
         }	
		//EXCEL_2007
		if(fileName.toLowerCase().endsWith(EXCEL_2007)){
			 Workbook Xssfworkbook = new XSSFWorkbook(is);   
	         Integer sheetNum = Xssfworkbook.getNumberOfSheets();
	         System.out.println(sheetNum+"--2007");
	          
	         for (int i= 0; i < sheetNum -1; i++) {// 循环工作表Sheet
	        	 System.out.println("进入循环");
	        	 XSSFSheet hssfSheet =(XSSFSheet) Xssfworkbook.getSheetAt(i);
		            if (hssfSheet == null) {
		                continue;
		            }// 循环行Row
		            //获得表头数据
		            XSSFRow hssfHead = hssfSheet.getRow(0);
		            boolean flag = false;
		            for(int j = 0; j< 26; j++){
		            	String head = getValue(hssfHead.getCell(j));
		            	switch (head) {
						case "姓名": flag = j == 0?true:false;break;
						case "姓别": flag = j == 1?true:false;break;
						case "电话": flag = j == 2?true:false;break;
						case "qq/邮箱": flag = j == 3?true:false;break;
						case "学校": flag = j == 4?true:false;break;
						case "专业": flag = j == 5?true:false;break;
						case "学历": flag = j == 6?true:false;break;
						case "采集人": flag = j == 7?true:false;break;
						case "来源渠道": flag = j == 8?true:false;break;
						case "客户类型": flag = j == 9?true:false;break;
						case "意向课程": flag = j == 10?true:false;break;
						case "求职意向": flag = j == 11?true:false;break;
						case "年龄": flag = j == 12?true:false;break;
						case "户口": flag = j == 13?true:false;break;
						case "现居地": flag = j == 14?true:false;break;
						case "政治面貌": flag = j == 15?true:false;break;
						case "民族": flag = j == 16?true:false;break;
						case "婚育状况": flag = j == 17?true:false;break;
						case "工作年限": flag = j == 18?true:false;break;
						case "工作经历": flag = j == 19?true:false;break;
						case "期望工作地点": flag = j == 20?true:false;break;
						case "期望薪资": flag = j == 21?true:false;break;
						case "紧急联系方式": flag = j == 22?true:false;break;
						case "开户行": flag = j == 23?true:false;break;
						case "银行卡号": flag = j == 24?true:false;break;
						case "备注": flag = j == 25?true:false;break;
						case "": flag = false;break;
						default:break;
						}
		            	if(!flag){
		        			break;
		            	}
		            }
		            
		           // System.out.println("表头");
		            if(!flag){//判断表头是否正确
		            	TbCustomer cus = new TbCustomer(); //错误数据对象
	        			cus.setCustomerid("导入的文件数据错误。请仔细核对表头和与之对应的数据后再导入");
	        			errorlist.add(cus);
	            		return errorlist;
		            }
		            System.out.println("表头"+hssfSheet.getLastRowNum());
		            for(int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++){
		            	XSSFRow hssfRow = hssfSheet.getRow(rowNum);
		            	if (hssfRow!= null) {
		            		TbCustomer= new TbCustomer();
		            		
		            		XSSFCell cuatomername=hssfRow.getCell(0);//客户姓名
		            		XSSFCell sex=hssfRow.getCell(1);//性别
		            		XSSFCell telephone=hssfRow.getCell(2);//电话
		            		XSSFCell email=hssfRow.getCell(3);//邮箱
		            		XSSFCell school=hssfRow.getCell(4);//学校
		            		XSSFCell major=hssfRow.getCell(5);//专业
		            		XSSFCell education=hssfRow.getCell(6);//学历
		            		XSSFCell collector=hssfRow.getCell(7);//采集人
		            		XSSFCell source=hssfRow.getCell(8);//来源
		            		XSSFCell customertype=hssfRow.getCell(9);//客户类型
		            		XSSFCell course=hssfRow.getCell(10);//意向课程
		            		XSSFCell jobobjective=hssfRow.getCell(11);//求职意向
		            		XSSFCell birthday=hssfRow.getCell(12);//生日
		            		XSSFCell placeofbirth=hssfRow.getCell(13);//户口
		            		XSSFCell address=hssfRow.getCell(14);//现居地
		            		XSSFCell politicalstatus=hssfRow.getCell(15);//政治面貌 
		            		XSSFCell nayionality=hssfRow.getCell(16);//民族
		            		XSSFCell maritalstatus=hssfRow.getCell(17);//婚育状况
		            		XSSFCell workservice=hssfRow.getCell(18);//工作年限
		            		XSSFCell workexp=hssfRow.getCell(19);//工作经历
		            		XSSFCell workplace=hssfRow.getCell(20);//期望工作地点
		            		XSSFCell salary=hssfRow.getCell(21);//期望薪水
		            		XSSFCell ecp=hssfRow.getCell(22);//紧急联系人
		            		XSSFCell bank=hssfRow.getCell(23);//开户行
		            		XSSFCell bankno=hssfRow.getCell(24);//银行卡号
		            		XSSFCell memo=hssfRow.getCell(25);//备注
		            		
		            		TbCustomer.setCustomername(getValue(cuatomername));
		            		TbCustomer.setSex(getValue(sex));
		            		TbCustomer.setTelephone(getValue(telephone));
		            		TbCustomer.setEmail(getValue(email));
		            		TbCustomer.setSchool(getValue(school));
		            		TbCustomer.setMajor(getValue(major));
		            	
		            		TbCustomer.setEducation(getValue(education));
		            		TbCustomer.setSource(getValue(source));            		
		            		TbCustomer.setCustomertype(getValue(customertype));
		            		TbCustomer.setCollector(getValue(collector));
		            		TbCustomer.setCourse(getValue(course));
		            	
		            		TbCustomer.setJobobjective(getValue(jobobjective));
//		            	
		            	
		            		TbCustomer.setPlaceofbirth(getValue(placeofbirth));
		            		TbCustomer.setAddress(getValue(address));
		            		
		            		TbCustomer.setPoliticalstatus(getValue(politicalstatus));
		            		TbCustomer.setNationality(getValue(nayionality));
		            		TbCustomer.setMaritalstatus(getValue(maritalstatus));
		            		TbCustomer.setWorkservice(getValue(workservice));
		            		TbCustomer.setWorkexp(getValue(workexp));
		            		TbCustomer.setWorkplace(getValue(workplace));
		            		TbCustomer.setSalary(getValue(salary));
		            		TbCustomer.setEcp(getValue(ecp));
		            		TbCustomer.setWorkplace(getValue(workplace));
		            		TbCustomer.setBank(getValue(bank));
		            		TbCustomer.setBankno(getValue(bankno));
		            		TbCustomer.setMemo(getValue(memo));
		            		
		            		TbCustomer.setOperator((String) session.getAttribute("userid"));
		            		TbCustomer.setStatus("1");
	            			TbCustomer.setAssigntype("0");
	            			if(getValue(birthday)!= null && !"".equals(getValue(birthday))){
	            				int age = Integer.parseInt(getValue(birthday).trim());
	            				if(age > 28 || age < 18){
	            					ageCount += (rowNum + 1) + "行:" + age +",";
	            				}
	            				TbCustomer.setBirthday(getBirthday(age,0,1));
	            			}
		            		//判断文件中电话号码是否重复
		            		for (TbCustomer c : list) {
								if(TbCustomer.getTelephone().equals(c.getTelephone())){
									repeatCount += (rowNum + 1) + ":" + c.getTelephone() +",";
								}
							}
		            		//判断文件中电话号码的格式是否哦正确
		            		if(!TbCustomer.getTelephone().matches("[0-9]{1,}") || TbCustomer.getTelephone().length() != 11){//判断一个字符串(电话号码)是否为纯数字和长度是否为11
		            			//	System.out.println( c.getTel().length());
		            				errorCount += (rowNum + 1) + ":" + TbCustomer.getTelephone() +",";
		            		}
		            		
		            		//性别处理
		            		if("女".equals(TbCustomer.getSex()) || "0".equals(TbCustomer.getSex())){
		            			TbCustomer.setSex("0");
		            		}else{
		            			TbCustomer.setSex("1");
		            		}
		            		//学历
		            		if("大专，本科，博士，硕士".indexOf(TbCustomer.getEducation())<0){
		            			educationCount +=  (rowNum + 1) + "行:" + TbCustomer.getEducation() +",";
		            		}
		            		list.add(TbCustomer);
		            	}
		            	
		            }
	         	}
	         }	
		
//		System.out.println("snahdahdkjhakdjh ");
		if(educationCount.length()>0){
			TbCustomer cus = new TbCustomer(); //错误数据对象
			cus.setCustomerid(educationCount +"学历不符合条件");
			errorlist.add(cus);
		}
		if(ageCount.length()>0){
			TbCustomer cus = new TbCustomer(); //错误数据对象
			cus.setCustomerid(ageCount +"年龄不符合条件");
			errorlist.add(cus);
		}
		if(repeatCount.length()>0){
			TbCustomer cus = new TbCustomer(); //错误数据对象
			cus.setCustomerid("在第"+ repeatCount +"行有电话数据重复");
			errorlist.add(cus);
		}
		if(errorCount.length()>0){
			TbCustomer cus = new TbCustomer(); //错误数据对象
			cus.setCustomerid("在第"+ errorCount +"行有电话数据格式错误");
			errorlist.add(cus);
		}
		if(errorlist.size()>0){
			return errorlist;
		}else{
			System.out.println("表格数据正确！");
			return list; 
		}
		
	}
	
	/***
	 * 
	 * @param path
	 * @return 导入客户简历信息
	 * @throws Exception
	 */
	/*public List<Customerinfo> ReadCoustomerExcel(String path,HttpSession session) throws Exception{
		Customerinfo cus=null;
		List<Customerinfo> list = new ArrayList<Customerinfo>();
		File excelFile = new File(path);
        String fileName = excelFile.getName();
		InputStream is = new FileInputStream(path);	
		//EXCEL_2003
		if(fileName.toLowerCase().endsWith(EXCEL_2003)){
		 Workbook   Hssfworkbook = new HSSFWorkbook(is);   
         Integer sheetNum = Hssfworkbook.getNumberOfSheets();
         System.out.println(sheetNum);
         
         for (int i= 0; i < sheetNum; i++) {// 循环工作表Sheet
	            HSSFSheet hssfSheet = (HSSFSheet) Hssfworkbook.getSheetAt(i);
	            if (hssfSheet == null) {
	                continue;
	            }// 循环行Row
	            for(int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++){
	            	System.out.println(hssfSheet.getLastRowNum());
	            	HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	            	System.out.println(getValue(hssfRow.getCell(2)));
	            	if (hssfRow != null && getValue(hssfRow.getCell(2)) != "") {
	            		try {
	            			cus= new Customerinfo();
	            			if(getValue(hssfRow.getCell(11)).length() > 0 && getValue(hssfRow.getCell(11)) != null){
	            				cus.setWorklife(Integer.parseInt(getValue(hssfRow.getCell(11)).trim()));
	            			}
	            			if(getValue(hssfRow.getCell(20)).length() > 0 && getValue(hssfRow.getCell(20)) != null){
	            				cus.setSalary(Integer.parseInt(getValue(hssfRow.getCell(20)).trim()));
	            			}
		            		cus.setName(getValue(hssfRow.getCell(0)));
		            		cus.setSex(getValue(hssfRow.getCell(1)));
		            		cus.setTel(getValue(hssfRow.getCell(2)));
		            		cus.setEmail(getValue(hssfRow.getCell(3)));
		            		cus.setSchool(getValue(hssfRow.getCell(4)));
		            		cus.setMajor(getValue(hssfRow.getCell(5)));
//		            		cus.setMarket(getValue(hssfRow.getCell(6)));
		            		cus.setEducation(getValue(hssfRow.getCell(6)));
		            		cus.setSource(getValue(hssfRow.getCell(7)));
		            		cus.setChannel(getValue(hssfRow.getCell(8)));           		
		            		cus.setIntentionjob(getValue(hssfRow.getCell(9)));
		            		
		            		cus.setWorkexperience(getValue(hssfRow.getCell(10)));
		            		cus.setRemark(getValue(hssfRow.getCell(12)));
		            		cus.setBirthdate(getValue(hssfRow.getCell(13)));
		            		cus.setResidence(getValue(hssfRow.getCell(14)));
		            		cus.setDomicile(getValue(hssfRow.getCell(15)));
		            		cus.setPolitics(getValue(hssfRow.getCell(16)));
		            		cus.setNation(getValue(hssfRow.getCell(17)));
		            		cus.setMarriage(getValue(hssfRow.getCell(18)));
		            		cus.setWorkplace(getValue(hssfRow.getCell(19)));
		            		
		            		//DecimalFormat df = new DecimalFormat("#");
		            		System.out.println(hssfRow.getCell(3));
		            		//System.out.println(df.format(hssfRow.getCell(3))); 
		            		
		            		String people = ((User)session.getAttribute("tempuser")).getUsername();
		            		cus.setRegistrant(people);
		            		cus.setClabelid(((User)session.getAttribute("tempuser")).getEid());
		            		Date date=new Date();
		            		cus.setEntrydate(date);//登记时间
						} catch (Exception e) {
							System.out.println(e);
						}           		
	            		list.add(cus);
	            		
	            	}
	            	
	            }
         	}
         }	
		//EXCEL_2007
		if(fileName.toLowerCase().endsWith(EXCEL_2007)){
			 Workbook Xssfworkbook = new XSSFWorkbook(is);   
	         Integer sheetNum = Xssfworkbook.getNumberOfSheets();
	         
	         for (int i= 0; i < sheetNum; i++) {// 循环工作表Sheet
	        	 XSSFSheet hssfSheet =(XSSFSheet) Xssfworkbook.getSheetAt(i);
		            if (hssfSheet == null) {
		                continue;
		            }// 循环行Row
		            for(int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++){
		            //	System.out.println(hssfSheet.getLastRowNum());
		            	XSSFRow hssfRow = hssfSheet.getRow(rowNum);
		            	System.out.println(getValue(hssfRow.getCell(2)));
		            	if (hssfRow != null && getValue(hssfRow.getCell(2)) != "") {
		            		try {
		            			cus= new Customerinfo();
		            			if(getValue(hssfRow.getCell(11)).length() > 0 && getValue(hssfRow.getCell(11)) != null){
		            				cus.setWorklife(Integer.parseInt(getValue(hssfRow.getCell(11)).trim()));
		            			}
		            			if(getValue(hssfRow.getCell(20)).length() > 0 && getValue(hssfRow.getCell(20)) != null){
		            				cus.setSalary(Integer.parseInt(getValue(hssfRow.getCell(20)).trim()));
		            			}
		            			
			            		cus.setName(getValue(hssfRow.getCell(0)));
			            		cus.setSex(getValue(hssfRow.getCell(1)));
			            		cus.setTel(getValue(hssfRow.getCell(2)));
			            	//	System.out.println("电话号码为：" +cus.getTel());
			            		cus.setEmail(getValue(hssfRow.getCell(3)));
			            		cus.setSchool(getValue(hssfRow.getCell(4)));
			            		cus.setMajor(getValue(hssfRow.getCell(5)));
//			            		cus.setMarket(getValue(hssfRow.getCell(6)));
			            		cus.setEducation(getValue(hssfRow.getCell(6)));
			            		cus.setSource(getValue(hssfRow.getCell(7)));
			            		cus.setChannel(getValue(hssfRow.getCell(8)));           		
			            		cus.setIntentionjob(getValue(hssfRow.getCell(9)));
			            		
			            		cus.setWorkexperience(getValue(hssfRow.getCell(10)));
			            		cus.setRemark(getValue(hssfRow.getCell(12)));
			            		cus.setBirthdate(getValue(hssfRow.getCell(13)));
			            		cus.setResidence(getValue(hssfRow.getCell(14)));
			            		cus.setDomicile(getValue(hssfRow.getCell(15)));
			            		cus.setPolitics(getValue(hssfRow.getCell(16)));
			            		cus.setNation(getValue(hssfRow.getCell(17)));
			            		cus.setMarriage(getValue(hssfRow.getCell(18)));
			            		cus.setWorkplace(getValue(hssfRow.getCell(19)));
			            		
			            		
			            		String people = ((User)session.getAttribute("tempuser")).getUsername();
			            		cus.setRegistrant(people);
			            		cus.setClabelid(((User)session.getAttribute("tempuser")).getEid());
			            		Date date=new Date();
			            		cus.setEntrydate(date);//登记时间
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println(e);
							}           		
		            		list.add(cus);
		            	}
		            	
		            }
	         	}
	         }	
		return list;
	}*/
	
	
	
	
	
	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell 2007
	 *  Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getValue(XSSFCell cell) {
		// TODO Auto-generated method stub
		String strCell = "";
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			strCell = getDate(cell);
			break;
	//	case HSSFCell.
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}
	
	 public String getDate(XSSFCell cell) {
			if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("m/d/yy")) {
				String sDate = cell.getNumericCellValue() + "";
				if (sDate != null || !sDate.equals("")) {
					Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
					return new SimpleDateFormat("yyyy-MM-dd").format(date);
				}
				return "";
			}

			// return String.valueOf(cell.getCellStyle().getDataFormat());
			return getInt(cell);
		}
	 public String getInt(XSSFCell cell) {
			String str_value = cell.getNumericCellValue() + "";
			int dotPosition = str_value.indexOf(".");
			String str_dot = str_value.substring(dotPosition + 1);
			String ret_value = null;
			if (str_dot.length() == 1 && str_dot.equals("0")) {
				ret_value = str_value.substring(0, dotPosition);
			} else {
				ret_value = str_value;
			}

			return ret_value;
		}
	 /**
		 * 获取单元格数据内容为日期类型的数据
		 * 
		 * @param cell
		 *  Excel单元格
		 * @return String 单元格数据内容
		 */
	 private String getDateCellValue(XSSFCell cell) {
			String result = "";
			try {
				int cellType = cell.getCellType();
				if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
					Date date = cell.getDateCellValue();
					result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate();
				} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
					String date = getValue(cell);
					result = date.replaceAll("[年月]", "-").replace("日", "").trim();
				} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
					result = "";
				}
			} catch (Exception e) {
				System.out.println("日期格式不正确!");
				e.printStackTrace();
			}
			return result;
	 }

	 
	 
	 
	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell 2003
	 *  Excel单元格
	 * @return String 单元格数据内容
	 */
	
	 @SuppressWarnings("static-access")
		private String getValue(HSSFCell cell) {
		 String strCell = "";
			if (cell == null)
				return "";
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				strCell = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				strCell = String.valueOf(cell.getNumericCellValue());
				strCell = getDate(cell);
				break;
		//	case HSSFCell.
			case HSSFCell.CELL_TYPE_BOOLEAN:
				strCell = String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				strCell = "";
				break;
			default:
				strCell = "";
				break;
			}
			if (strCell.equals("") || strCell == null) {
				return "";
			}
			if (cell == null) {
				return "";
			}
			return strCell;
	 }
	 public String getDate(HSSFCell cell) {
			if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("m/d/yy")) {
				String sDate = cell.getNumericCellValue() + "";
				if (sDate != null || !sDate.equals("")) {
					Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
					return new SimpleDateFormat("yyyy-MM-dd").format(date);
				}
				return "";
			}

			// return String.valueOf(cell.getCellStyle().getDataFormat());
			return getInt(cell);
		}
	 public String getInt(HSSFCell cell) {
			String str_value = cell.getNumericCellValue() + "";
			int dotPosition = str_value.indexOf(".");
			String str_dot = str_value.substring(dotPosition + 1);
			String ret_value = null;
			if (str_dot.length() == 1 && str_dot.equals("0")) {
				ret_value = str_value.substring(0, dotPosition);
			} else {
				ret_value = str_value;
			}

			return ret_value;
		}
	 /**
		 * 获取单元格数据内容为日期类型的数据
		 * 
		 * @param cell
		 *  Excel单元格
		 * @return String 单元格数据内容
		 */
	 private String getDateCellValue(HSSFCell cell) {
			String result = "";
			try {
				int cellType = cell.getCellType();
				if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
					Date date = cell.getDateCellValue();
					result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate();
				} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
					String date = getValue(cell);
					result = date.replaceAll("[年月]", "-").replace("日", "").trim();
				} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
					result = "";
				}
			} catch (Exception e) {
				System.out.println("日期格式不正确!");
				e.printStackTrace();
			}
			return result;
	 }
	 
	 public Date getBirthday(int ageYear, int ageMonth, int ageDay) {
	    Calendar c = Calendar.getInstance();
	    c.add(Calendar.YEAR, -ageYear);
	    c.add(Calendar.MONTH, -ageMonth);
	    c.add(Calendar.DAY_OF_MONTH, -ageDay);
	   // System.out.println(c.getTime());
	    return c.getTime();
	    
	}
	 
    
}
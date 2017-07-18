package com.icss.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUpload {
	
	//上传Excel文件（客户数据）
	@SuppressWarnings("finally")
	public static String uploadFile(HttpServletRequest request, MultipartFile mFile){
		   System.out.println("读取文件");
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			//MultipartFile 
	    	mFile = multipartRequest.getFile("mFile");
	    	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/customerinfo");//获取文件名 的路径
	    	File file =new File(path);    
			if (!file.exists() && !file.isDirectory()) {
				System.out.println("//目录不存在");
				file.mkdirs();//创建该目录
			}
	    	
	    	System.out.println("文件路径："+path);
	    	String reslut = 0 + ";" +path;
	    	try {
	    		String name = mFile.getOriginalFilename();   //获取文件名     
	        	System.out.println("文件名称"+name);
	        	InputStream inputStream = mFile.getInputStream();
	        	 
	    		byte[] b = new byte[1048576];
	    		int length = inputStream.read(b);
	    		path += "\\" + name;
	    		FileOutputStream outputStream = new FileOutputStream(path);
	    		outputStream.write(b, 0, length);
	    		inputStream.close();
	    		outputStream.close();
				reslut = "1";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				reslut = "-1";
				e.printStackTrace();
			}finally{
				return reslut + ";" + path;
			}
	 }
	
	//上传图片文件、普通文件（不需要再次处理）
	public static String uploadCommon(HttpServletRequest request, MultipartFile mFile){
		String reslut = "";
		String path = request.getSession().getServletContext().getRealPath("/upload/order_attachment/"+ new SimpleDateFormat("yyyyMMdd").format(new Date())) ;//获取文件名 的路径
		File file = new File(path);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//目录不存在");
			file.mkdirs();//创建该目录
		}
		try {
    		String name = mFile.getOriginalFilename();   //获取文件名     
    		//System.currentTimeMillis();
        	System.out.println("文件名称"+name);
        	
        	InputStream inputStream = mFile.getInputStream();
        	 
    		byte[] b = new byte[1048576];
    		int length = inputStream.read(b);
    		path += "\\" + System.currentTimeMillis() + new Random().nextInt(100) + name.substring(name.lastIndexOf("."));
    		FileOutputStream outputStream = new FileOutputStream(path);
    		outputStream.write(b, 0, length);//将文件写入服务器
    		inputStream.close();
    		outputStream.close();
			reslut = path;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		}
		return reslut;
	}
	//上传图片文件、普通文件（不需要再次处理简历照片）
	public static String uploadResume(HttpServletRequest request, MultipartFile mFile){
		String reslut = "";
		//upload/resume/2017/07/2017070512345678_XXX.jpg格式
		//System.out.println( new SimpleDateFormat("yyyyMMdd").format(new Date()));
		String year =  new SimpleDateFormat("yyyy").format(new Date());
		String month =  new SimpleDateFormat("MM").format(new Date());
		reslut = "/upload/resume/"+ year+ "/"+ month;
		String path = request.getSession().getServletContext().getRealPath(reslut) ;//获取文件名 的路径
		File file = new File(path);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//目录不存在");
			file.mkdirs();//创建该目录
		}
		try {
			String name = mFile.getOriginalFilename();   //获取文件名     
			//System.currentTimeMillis();
			System.out.println("文件名称"+name);
			
			InputStream inputStream = mFile.getInputStream();
			
			byte[] b = new byte[1048576];
			int length = inputStream.read(b);
			path += "\\" + name;
			FileOutputStream outputStream = new FileOutputStream(path);
			outputStream.write(b, 0, length);//将文件写入服务器
			inputStream.close();
			outputStream.close();
			reslut += "/" + name;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		}
		return reslut;
	}
}

package com.icss.util;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.icss.bean.TbSysLog;
import com.icss.impl.SysLogImpl;
@Aspect
@Component
public class SystemLogController {
	 //本地异常日志记录对象  
	private  static  final Logger  logger = LoggerFactory.getLogger(SystemLogController. class);
	
   @Autowired
    private SysLogImpl SysLogImpl;   //注入Service用于把日志保存数据库  

   /**
    * controller层切点 注解拦截
    */
  @Pointcut("@annotation(com.icss.util.SysControllerLog)")  //定义一个切入点
   public void controllerAspect(){	   
  }
    
  /** 
   * 用于拦截Controller层记录用户的操作 
   * @param joinPoint 切点 
   * @throws Exception 
   */ 
  @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws Exception {  
	   TbSysLog log=new TbSysLog();//日志实体对象
	   //获取登录用户账户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
        HttpSession session = request.getSession();  
        String userid=(String) session.getAttribute("userid");
    //    System.out.println(userid);   
        if(userid!=null){
        	log.setUserid(userid);
        }else{
        	  log.setUserid("00000000");
        }    
        //请求的IP  
        String ip = request.getRemoteAddr();  
        log.setRemoteip(ip);
        //请求的时间
        Date date = new Date(System.currentTimeMillis());
        log.setCreatetime(date);
        //URL
        String requesturl =request.getRequestURI();
        log.setRequesturl(requesturl);
	   //*========控制台输出=========*//  
	    System.out.println("=====前置通知开始=====");  
	    System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
	    System.out.println("请求IP:" + ip);  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class targetClass = Class.forName(targetName);  
        Method[] methods = targetClass.getMethods();  
        String requestoper = "";  
        String permid="";
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
                Class[] clazzs = method.getParameterTypes();  
                if (clazzs.length == arguments.length) {  
              	  requestoper = method.getAnnotation(SysControllerLog.class).requestoper();  
              	  permid=method.getAnnotation(SysControllerLog.class).permid();  
                    break;  
                }  
            }  
        }  
          log.setPermid(permid);
          log.setRequestoper(requestoper);
          log.setId("");        
          SysLogImpl.add(log);
  	}  	  
} 
  

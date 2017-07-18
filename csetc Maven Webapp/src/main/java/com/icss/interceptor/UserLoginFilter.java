package com.icss.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginFilter extends HttpServlet implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;  
        HttpServletResponse resp = (HttpServletResponse) response;  
        String path = req.getContextPath();  
        String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path;  
        HttpSession session = req.getSession(true);  
        String username = (String) session.getAttribute("userid");  
        String url=req.getRequestURI();
                System.out.println(url);
                if(!url.equals("/csetc")&&!url.equals("/csetc/login.jsp") && !url.equals("/csetc/SysUser/Login.do")){
                     if(session.getAttribute("userid")==null||session.getAttribute("userid")==""){
                      // resp.sendRedirect("login.jsp");
                       resp.sendRedirect(basePath+"/login.jsp");  
                         return ;
                     }
                 }
                
               
            chain.doFilter(req, resp);
       /* if (username == null || "".equals(username)) {  
            resp.setHeader("Cache-Control", "no-store");  
            resp.setDateHeader("Expires", 0);  
            resp.setHeader("Prama", "no-cache");  
            resp.sendRedirect(basePath+"/login.jsp");  
        } else {  
            chain.doFilter(req, resp);  
        }  */
	}

}

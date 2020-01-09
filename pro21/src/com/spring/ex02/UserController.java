package com.spring.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

// 설정 파일의 userMethodNameResolver 프로퍼티를 사용하려면 반드시 MultiActionController를 상속 받아야 합니다.
public class UserController extends MultiActionController{
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
			String userID ="";
			String passwd ="";
			String viewName=getViewName(request);
			ModelAndView mav = new ModelAndView();
			request.setCharacterEncoding("utf-8");
			userID=request.getParameter("userID");
			passwd=request.getParameter("passwd");
			
			//ModelAndView에 로그인 정보를 바인딩합니다.
			mav.addObject("userID", userID);
			mav.addObject("passwd", passwd);
			//ModelAndView 객체에 포워딩할 JSP 이름을 설정합니다.
			//mav.setViewName("result");
			mav.setViewName(viewName);
			return mav;
		}
	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		String pwd= request.getParameter("pwd");
		String name= request.getParameter("name");
		String email=request.getParameter("email");
		
		mav.addObject("id", id);
		mav.addObject("pwd", pwd);
		mav.addObject("name", name);
		mav.addObject("email", email);
		
		mav.setViewName("memberInfo");
		return mav;
	}
	
	private  String getViewName(HttpServletRequest request) throws Exception {
	      String contextPath = request.getContextPath();
	      String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
	      if(uri == null || uri.trim().equals("")) {
	         uri = request.getRequestURI();
	      }

	      int begin = 0;
	      if(!((contextPath==null)||("".equals(contextPath)))){
	         begin = contextPath.length();
	      }

	      int end;
	      if(uri.indexOf(";")!=-1){
	         end=uri.indexOf(";");
	      }else if(uri.indexOf("?")!=-1){
	         end=uri.indexOf("?");
	      }else{
	         end=uri.length();
	      }

	      String fileName=uri.substring(begin,end);
	      if(fileName.indexOf(".")!=-1){
	         fileName=fileName.substring(0,fileName.lastIndexOf("."));
	      }
	      if(fileName.lastIndexOf("/")!=-1){
	         fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
	      }
	      return fileName;
	   }
		
	}


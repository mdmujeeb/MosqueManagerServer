package com.mujeeb.mosquemanager.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mujeeb.mosquemanager.controller.MosqueManagerController;

public class SecurityFilter implements Filter {

	public static final String CONTEXT_NAME = "/";
//	public static final String CONTEXT_NAME = "/mm/";
	private String contextPath = "";
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		
		String requestString = ((HttpServletRequest)request).getRequestURI().trim();
		if(!requestString.startsWith(CONTEXT_NAME)) {
			return;
		}
		requestString = requestString.substring(CONTEXT_NAME.length(), requestString.length());		// Take out String after Context Name
		
		boolean isLoggedIn = false;
		Object masjidId = ((HttpServletRequest)request).getSession().getAttribute(MosqueManagerController.KEY_MASJID_ID);
		try {
			Integer.parseInt("" + masjidId);
			isLoggedIn = true;
			
		} catch(Exception ex) {
			
			/*Do Nothing*/
		}
		
		if(!isLoggedIn
					&& !requestString.isEmpty() 
					&& !requestString.startsWith("index.jsp") 
					&& !requestString.startsWith("login")
					&& !requestString.startsWith("loginPage")
					&& !requestString.startsWith("getNamazTimes")
					&& !requestString.startsWith("getHijriDate")
					&& !requestString.startsWith("getCurrentTempreature")
					&& !requestString.startsWith("getQuranAyats")
					&& !requestString.startsWith("getNotices")
					&& !requestString.startsWith("getOccasions")
					&& !requestString.startsWith("updateRefreshRequired")
					&& !requestString.startsWith("images")
					&& !requestString.startsWith("moonphases")
					&& !requestString.startsWith("css")
					&& !requestString.startsWith("index_files")
					&& !requestString.startsWith("fonts")
					&& !requestString.startsWith("js")
					&& !requestString.startsWith("images")
					&& !requestString.startsWith("api")
					) {
			
			((HttpServletResponse)response).sendRedirect(contextPath + "/loginPage");
			return;
		}
		
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		contextPath = config.getServletContext().getContextPath();
	}
}

//Author: Hai Long Do
package com.finneze.libnet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.senecacollege.prg556.limara.bean.AccountOwner;
import ca.senecacollege.prg556.limara.dao.AccountOwnerDAO;

import com.finneze.libnet.data.AccountOwnerDAOFactory;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

/**
 * Servlet Filter implementation class LibraryLoginFilter
 */
//@WebFilter(dispatcherTypes = {
//				DispatcherType.REQUEST, 
//				DispatcherType.FORWARD
//		}
//					, urlPatterns = { "/librarylogin.jspx" })
public class LibraryLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LibraryLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		
		
		try {
			HttpSession session = req.getSession();
			AccountOwner accountOwnerSession = (AccountOwner) session.getAttribute("accountCheck");
			if(null != accountOwnerSession){
				
				res.sendRedirect(req.getContextPath() + "/");
				return;
			}
			else {
				String username = req.getParameter("userLogin");
				String password = req.getParameter("passLogin");
				
				if ("POST".equals(req.getMethod()) && !("".equals(username)) && username != null && !("".equals(password)) && password != null){
					
					
					AccountOwner account = AccountOwnerDAOFactory.getAccountOwnerDAO().validateAccountOwner(username, password);
					if(account != null){
						session.setAttribute("accountCheck", account);
						res.sendRedirect(req.getContextPath() + "/");
						return;
						
					}
					else {
						
						req.setAttribute("username" ,username);
						req.setAttribute("test", Boolean.TRUE);
					
					}
				}
				chain.doFilter(request, response);
			
				
			}
			
			

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException();
		}
		

		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

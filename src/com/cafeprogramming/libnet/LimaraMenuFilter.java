//Author: Juan Rojas
package com.cafeprogramming.libnet;

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
import javax.servlet.http.HttpSession;

import ca.senecacollege.prg556.limara.dao.AccountOwnerDAO;
import ca.senecacollege.prg556.limara.dao.MaterialDAO;
import ca.senecacollege.prg556.limara.bean.AccountOwner;

import com.cafeprogramming.libnet.data.MaterialReservationDAOFactory;
import com.finneze.libnet.data.AccountOwnerDAOFactory;
import com.finneze.libnet.data.MaterialDAOFactory;

/**
 * Servlet Filter implementation class LimaraMenuFilter
 */
public class LimaraMenuFilter implements Filter {

	/**
	 * Default constructor. 
	 */
	public LimaraMenuFilter() {
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
		
		try{
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session = req.getSession();
			AccountOwnerDAO accountDAO = AccountOwnerDAOFactory.getAccountOwnerDAO();
			AccountOwner account = (AccountOwner) session.getAttribute("accountCheck");
			
			session.setAttribute("accountOwner", accountDAO.getAccountOwner(account.getId()));
			req.setAttribute("numberOfReservations", MaterialReservationDAOFactory.getMaterialReservationDAO().getMaterialReservations(account.getId()).size());
			chain.doFilter(request, response);
		}
		catch(SQLException e){
			throw new ServletException();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

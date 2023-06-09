//Author: Hai Long Do
package com.finneze.libnet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import ca.on.senecac.prg556.common.StringHelper;
import ca.senecacollege.prg556.limara.bean.AccountOwner;
import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.dao.MaterialDAO;
import ca.senecacollege.prg556.limara.dao.MaterialReservationDAO;

import com.cafeprogramming.libnet.data.MaterialReservationDAOFactory;
import com.finneze.libnet.data.AccountOwnerDAOFactory;
import com.finneze.libnet.BadRequestException;
import com.finneze.libnet.data.MaterialDAOFactory;
import com.sun.xml.wss.impl.misc.HANonceManager.nonceCleanupTask;

import java.util.ArrayList;
/**
 * Servlet Filter implementation class ShowMaterialReservationsFilter
 */
//@WebFilter(dispatcherTypes = {
//				DispatcherType.REQUEST, 
//				DispatcherType.FORWARD
//		}
//					, urlPatterns = { "/showmaterialreservations.jspx" })
public class ShowMaterialReservationsFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ShowMaterialReservationsFilter() {
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
		MaterialReservationDAO materials = MaterialReservationDAOFactory.getMaterialReservationDAO();
		HttpSession session = req.getSession();
		AccountOwner owner =(AccountOwner)session.getAttribute("accountCheck");
		
		
		
		
	
		try {	
			if ("POST".equals(req.getMethod()) && req.getParameter("cancelBtn") != null){
				String id = req.getParameter("custId");
				materials.cancelMaterialReservation(owner.getId(), id);			
			}

			List<Material> materialList = materials.getMaterialReservations(owner.getId());
			req.setAttribute("list", materialList);
			chain.doFilter(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
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

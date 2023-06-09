//Author: Juan Rojas
package com.cafeprogramming.libnet;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafeprogramming.libnet.data.MaterialReservationDAOFactory;
import com.finneze.libnet.BadRequestException;
import com.finneze.libnet.data.MaterialDAOFactory;

import ca.senecacollege.prg556.limara.bean.AccountOwner;
import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.dao.MaterialDAO;
import ca.on.senecac.prg556.common.StringHelper;

/**
 * Servlet Filter implementation class ReserveMaterialsFilter
 */
public class ReserveMaterialsFilter implements Filter {

	/**
	 * Default constructor. 
	 */
	public ReserveMaterialsFilter() {
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
		HttpSession session = req.getSession();

		if ("POST".equals(req.getMethod()))
		{
			req.setAttribute("isPost", Boolean.TRUE);
			String materialType = req.getParameter("materialType");
			String materialTitle = req.getParameter("materialTitle");
			List<Material> materialsList;
			MaterialDAO materialDAO = MaterialDAOFactory.getMaterialDAO();
			
			try
			{
				req.setAttribute("invalidTitle", Boolean.FALSE);
				if(StringHelper.isNotNullOrEmpty(materialType) && StringHelper.isNotNullOrEmpty(materialTitle)){
					req.setAttribute("materialType", StringHelper.xmlEscape(materialType));
					req.setAttribute("materialTitle", StringHelper.xmlEscape(materialTitle));
					materialsList = materialDAO.getAvailableMaterials(materialTitle, materialType);
					req.setAttribute("materialsList", materialsList);
					if("All".equals(materialType)){
						materialsList = materialDAO.getAvailableMaterials(materialTitle, null);
						req.setAttribute("materialsList", materialsList);
					}
				}
				else if("All".equals(materialType)){
					req.setAttribute("invalidTitle", Boolean.TRUE);
				}
				else if(!"All".equals(materialType)){
					materialsList = materialDAO.getAvailableMaterials(null, materialType);
					req.setAttribute("materialType", StringHelper.xmlEscape(materialType));
					req.setAttribute("materialTitle", StringHelper.xmlEscape(materialTitle));
					req.setAttribute("materialsList", materialsList);
				}

				if(req.getParameter("reserve") != null){
					String materialId = req.getParameter("id_number");
					if(StringHelper.isNotNullOrEmpty(materialId)){
						if(materialDAO.getMaterial(materialId) != null){
							AccountOwner account = (AccountOwner) session.getAttribute("accountCheck");
							if(MaterialReservationDAOFactory.getMaterialReservationDAO().reserveMaterial(account.getId(), materialId) != null){
								res.sendRedirect(req.getContextPath() + "/");
								return;
							}
						}
						else{
							throw new BadRequestException();
						}

					}
					else {
						throw new BadRequestException();
					}
				}
			}
			catch (Exception e){
				throw new ServletException();
			}


		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

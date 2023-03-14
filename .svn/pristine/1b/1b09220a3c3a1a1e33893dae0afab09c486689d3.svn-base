//Author: Hai Long Do
package com.finneze.libnet.data;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.sql.DataSource;

import org.eclipse.persistence.internal.sessions.DirectCollectionChangeRecord.NULL;

import sun.util.logging.resources.logging;

import com.sun.org.apache.regexp.internal.recompile;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

import ca.on.senecac.prg556.common.StringHelper;
import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.dao.MaterialDAO;

class MaterialData implements MaterialDAO {
	
	private DataSource ds;
	public MaterialData(DataSource ds) {
		// TODO Auto-generated constructor stub
		setDs(ds);
	}
	
	@Override
	public  Material getMaterial(String arg0) throws SQLException {
		// TODO Auto-generated method stub
//		if(arg0.equals("3487") ){
//			 return new Material(arg0, "The Lying Life of Adults", "Book");
//			 	
//		}
		try(Connection conn = getDs().getConnection()){
			try(PreparedStatement statement = conn.prepareStatement("SELECT material_id, title, type FROM material WHERE material_id = ?")){
				statement.setString(1, arg0);
				try(ResultSet rslt = statement.executeQuery()){
					if(rslt.next()){
						String id = rslt.getString("material_id");
						String title = rslt.getString("title");
						String type = rslt.getString("type");
						return new Material(id, title, type);
					}
					else {
						return null;
					}
					
					
				}
			}
			
		}
		
		
	}
	
	

	@Override
	public List<Material> getAvailableMaterials(String title, String type) throws SQLException {
		// TODO Auto-generated method stub
		
		try(Connection conn = getDs().getConnection()){
			
			try(Statement stmt = conn.createStatement()){
				
				try(ResultSet rslt = stmt.executeQuery("SELECT material_id, title, type, account_owner_id FROM material ORDER BY type, title")){
					List<Material> newList = new ArrayList<Material>();
					while(rslt.next()){
						String ownerStringID = rslt.getString("account_owner_id");
						String typeString = rslt.getString("type");
						if(ownerStringID == null){
							String findTitle  = rslt.getString("title");
							if(StringHelper.isNotNullOrEmpty(title) && "All".equals(type) || StringHelper.isNullOrEmpty(type) ){
								
								if((findTitle.toLowerCase()).contains(title.toLowerCase())){
									newList.add(getMaterial(rslt.getString("material_id")));
								}
							}
							else if(StringHelper.isNullOrEmpty(title) && (!("All".equals(type)) || StringHelper.isNotNullOrEmpty(type))) {
								if(typeString.equals(type)){
									newList.add(getMaterial(rslt.getString("material_id")));
								}
							}
							else if(StringHelper.isNotNullOrEmpty(title) && (!("All".equals(type)) || StringHelper.isNotNullOrEmpty(type))){
								if((findTitle.toLowerCase()).contains(title.toLowerCase()) && typeString.equals(type)){
									
									newList.add(getMaterial(rslt.getString("material_id")));
								}
							}
							else if(StringHelper.isNullOrEmpty(title) && ("All".equals(type) || StringHelper.isNullOrEmpty(type))) {
								
								newList.add(getMaterial(rslt.getString("material_id")));
							}
							
							
						}
					}	
					return newList;
					
				}	
			}
		}
		
		
		
	}

	private DataSource getDs()
	{
		return ds;
	}
	private void setDs(DataSource ds) {
		this.ds = ds;
	}
}

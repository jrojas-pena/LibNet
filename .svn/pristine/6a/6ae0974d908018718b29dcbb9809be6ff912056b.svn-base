//Author: Hai Long Do 
package com.finneze.libnet.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.sun.org.apache.regexp.internal.REUtil;

import ca.senecacollege.prg556.limara.bean.AccountOwner;
import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.dao.AccountOwnerDAO;
import ca.senecacollege.prg556.limara.dao.MaterialDAO;

class AccountOwnerData implements AccountOwnerDAO {
	private DataSource ds;

	public AccountOwnerData(DataSource ds) {
		// TODO Auto-generated constructor stub
		setDs(ds);
	}
	
	@Override
	public AccountOwner validateAccountOwner(String username, String password)
			throws SQLException {
//		else if("test3".equals(username) && "123456".equals(password)){
//			return getAccountOwner(789);
//			
//		}
//		else {
//			return null;
//		}
		try(Connection conn = getDs().getConnection()){
			try(PreparedStatement statement = conn.prepareStatement("SELECT id FROM accountowner WHERE username = ? AND password = ? ")){
				statement.setString(1, username);
				statement.setString(2, password);
				try(ResultSet rslt = statement.executeQuery()){
					
					if(rslt.next()){
						int id = rslt.getInt("id");
						return getAccountOwner(id);
					}
					else {
						return null;
					}
					
					
				}
				
			}
			
		}
		
	}
	
	@Override
	public AccountOwner getAccountOwner(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		try(Connection conn = getDs().getConnection()){
			try(PreparedStatement statement = conn.prepareStatement("SELECT id, first_name, last_name FROM accountowner WHERE id = ?")){
				statement.setInt(1, arg0);
				try(ResultSet rslt = statement.executeQuery()){
					if(rslt.next()){
						int id = rslt.getInt("id");
						String firstName = rslt.getString("first_name");
						String lastName = rslt.getString("last_name");
						return new AccountOwner(id, firstName, lastName);
					}
					else {
						return null;
					}
					
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

//Author: Juan Rojas
package com.cafeprogramming.libnet.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.dao.MaterialDAO;
import ca.senecacollege.prg556.limara.dao.MaterialReservationDAO;

class MaterialReservationData implements MaterialReservationDAO{
	
	private DataSource ds;
	
	public MaterialReservationData(DataSource ds) {
		// TODO Auto-generated constructor stub
		setDs(ds);
	}
	private DataSource getDs(){
		return ds;
	}
	private void setDs(DataSource ds){
		this.ds = ds;
	}

	@Override
	public void cancelMaterialReservation(int accountOwnerId, String materialId)
			throws SQLException {
		try(Connection conn = getDs().getConnection())
		{
			try(PreparedStatement statement = conn.prepareStatement("SELECT material_id, title, type, account_owner_id FROM material WHERE material_id = ? AND account_owner_id = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE))
			{				
				statement.setString(1, materialId);
				statement.setInt(2, accountOwnerId);
				try(ResultSet rs = statement.executeQuery())
				{
					if(rs.next())
					{					
						rs.updateNull("account_owner_id");
						rs.updateRow();						
					}
				}
				
			}
		}
		
	}

	@Override
	public Material getMaterialReservation(int accountOwnerId, String materialId)
			throws SQLException {
		// TODO Auto-generated method stub

		try(Connection conn = getDs().getConnection())
		{
			try(PreparedStatement statement = conn.prepareStatement("SELECT material_id, title, type FROM material WHERE material_id = ? AND account_owner_id = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE))
			{				
				statement.setString(1, materialId);
				statement.setInt(2, accountOwnerId);
				try(ResultSet rs = statement.executeQuery())
				{
					if(rs.next())
					{
						String title = rs.getString("title");
						String type = rs.getString("type");
						
						return new Material(materialId, title, type);
						
					}
				}
				
			}
		}
		return null;
	}

	@Override
	public List<Material> getMaterialReservations(int accountOwnerId) throws SQLException {
		// TODO Auto-generated method stub
		List<Material> materialList = new ArrayList<>();
		try(Connection conn = getDs().getConnection())
		{
			try(PreparedStatement statement = conn.prepareStatement("SELECT material_id, title, type FROM material WHERE account_owner_id = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE))
			{				
				statement.setInt(1, accountOwnerId);
				try(ResultSet rs = statement.executeQuery())
				{
					while(rs.next())
					{
						String materialId = rs.getString("material_id");
						String title = rs.getString("title");
						String type = rs.getString("type");
						
						materialList.add(new Material(materialId, title, type));
						
					}
				}
				
			}
		}
		return materialList;
	}

	@Override
	public Material reserveMaterial(int accountOwnerId, String materialId) throws SQLException {
		
		try(Connection conn = getDs().getConnection())
		{
			try(PreparedStatement statement = conn.prepareStatement("SELECT material_id, title, type, account_owner_id FROM material WHERE material_id = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE))
			{				
				statement.setString(1, materialId);
				try(ResultSet rs = statement.executeQuery())
				{
					if(rs.next())
					{
						String title = rs.getString("title");
						String type = rs.getString("type"); 
						int accountId = rs.getInt("account_owner_id");
						if(0 == accountId){
							rs.updateInt("account_owner_id", accountOwnerId);
							rs.updateRow();
							return new Material(materialId, title, type);
						}
					}
				}
				
			}
		}
		return null;
	}

}

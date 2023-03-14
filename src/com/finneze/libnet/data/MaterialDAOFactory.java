//Author: Hai Long Do
package com.finneze.libnet.data;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.cafeprogramming.libnet.data.DataSourceFactory;

import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.dao.MaterialDAO;



public class MaterialDAOFactory {

	public MaterialDAOFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static MaterialDAO getMaterialDAO(){
			return new MaterialData(DataSourceFactory.getDataSource());
	}
		
	
}

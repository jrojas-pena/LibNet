//Author: Juan Rojas
package com.cafeprogramming.libnet.data;

import java.sql.SQLException;
import java.util.List;

import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.dao.MaterialReservationDAO;

public class MaterialReservationDAOFactory {

	public MaterialReservationDAOFactory() {
		// TODO Auto-generated constructor stub
	}

	public static MaterialReservationDAO getMaterialReservationDAO(){
		return new MaterialReservationData(DataSourceFactory.getDataSource());
	}

}

//Author: Hai Long Do
package com.finneze.libnet.data;

import java.sql.SQLException;

import com.cafeprogramming.libnet.data.DataSourceFactory;

import ca.senecacollege.prg556.limara.bean.AccountOwner;
import ca.senecacollege.prg556.limara.dao.AccountOwnerDAO;

public class AccountOwnerDAOFactory {

	public AccountOwnerDAOFactory() {
		// TODO Auto-generated constructor stub
	}
	public static AccountOwnerDAO getAccountOwnerDAO(){
		return new AccountOwnerData(DataSourceFactory.getDataSource());
	}

}

package model.dao;

import db.DB;
import model.dao.impl.FuncionarioDaoJDBC;

public class FabricaDao {
	
	public static FuncionarioDao criarFuncionarioDao() {
		return new FuncionarioDaoJDBC(DB.getConnection());
	}

}

package model.dao;

import model.dao.impl.FuncionarioDaoJDBC;

public class FabricaDao {
	
	public static FuncionarioDao criarFuncionarioDao() {
		return new FuncionarioDaoJDBC();
	}

}

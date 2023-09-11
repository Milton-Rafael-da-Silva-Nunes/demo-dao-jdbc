package application;


import model.dao.FabricaDao;
import model.dao.FuncionarioDao;

public class Program {

	public static void main(String[] args) {
		
		FuncionarioDao funcionariodao = FabricaDao.criarFuncionarioDao();
		
		System.out.println("=== TESTE 1: Funcionario findById ===");
		funcionariodao.findById(1);
		System.out.println(funcionariodao.findById(1));

	}

}

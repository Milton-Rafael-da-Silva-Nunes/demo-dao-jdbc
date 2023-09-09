package application;


import model.dao.FabricaDao;
import model.dao.FuncionarioDao;

public class Program {

	public static void main(String[] args) {
		
		/*Departamento dep = new Departamento(1, "Books");
		System.out.println(dep);
		
		System.out.println();
		
		Funcionario fun = new Funcionario(11, "Rafael", "rafa.nunc17@gamil.com", new Date(), 1480.0, dep);
		System.out.println(fun);*/
		
		FuncionarioDao funcionariodao = FabricaDao.criarFuncionarioDao();
		funcionariodao.findById(1);
		System.out.println(funcionariodao.findById(1));

	}

}

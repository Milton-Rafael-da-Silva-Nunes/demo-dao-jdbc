package application;


import java.util.List;

import model.dao.FabricaDao;
import model.dao.FuncionarioDao;
import model.entities.Departamento;
import model.entities.Funcionario;

public class Program {

	public static void main(String[] args) {
		
		FuncionarioDao funcionariodao = FabricaDao.criarFuncionarioDao();
		
		System.out.println("=== TESTE 1: Funcionario findById ===");
		funcionariodao.findById(1);
		System.out.println(funcionariodao.findById(1));
		
		System.out.println();
		
		System.out.println("=== TESTE 2: Funcionario findByDepartamento ===");
		Departamento departamento = new Departamento(2, null);
		List<Funcionario> list = funcionariodao.findByDepartamento(departamento);
		for(Funcionario obj : list) {
			System.out.println(obj);
		}
		
		System.out.println();
		
		System.out.println("=== TESTE 3: Funcionario findAll ===");
		list = funcionariodao.findAll();
		for(Funcionario obj : list) {
			System.out.println(obj);
		}
	}

}

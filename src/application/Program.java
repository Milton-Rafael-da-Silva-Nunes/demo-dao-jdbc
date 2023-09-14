package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.FabricaDao;
import model.dao.FuncionarioDao;
import model.entities.Departamento;
import model.entities.Funcionario;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		FuncionarioDao funcionariodao = FabricaDao.criarFuncionarioDao();

		System.out.println("=== TESTE 1: Funcionario findById ===");
		funcionariodao.findById(1);
		System.out.println(funcionariodao.findById(1));

		System.out.println();

		System.out.println("=== TESTE 2: Funcionario findByDepartamento ===");
		Departamento departamento = new Departamento(2, null);
		List<Funcionario> list = funcionariodao.findByDepartamento(departamento);
		for (Funcionario obj : list) {
			System.out.println(obj);
		}

		System.out.println();

		System.out.println("=== TESTE 3: Funcionario findAll ===");
		list = funcionariodao.findAll();
		for (Funcionario obj : list) {
			System.out.println(obj);
		}

		System.out.println();

		System.out.println("=== TESTE 4: Funcionario insert ===");
		Funcionario funcionario = new Funcionario(null, "Greg", "greg@gmail.com", new Date(), 3000.0, departamento);
		funcionariodao.insert(funcionario);
		System.out.println("Nova id funcionario = " + funcionario.getId());

		System.out.println();

		System.out.println("=== TESTE 5: Funcionario update ===");
		funcionario = funcionariodao.findById(1);
		funcionario.setNome("Milton Nunes");
		funcionariodao.update(funcionario);
		System.out.println("Update completado!");
		
		System.out.println();

		System.out.println("=== TESTE 6: Funcionario delete ===");
		System.out.print("Digite a ID para ser deletada: ");
		int id = sc.nextInt();
		funcionariodao.deleteById(id);
		System.out.println("Delete completado!");
		
		sc.close();
	}

}

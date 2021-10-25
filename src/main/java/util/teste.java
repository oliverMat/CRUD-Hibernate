package util;

import java.util.Date;

import dao.Funcionario_DAO;
import model.Funcionario;

public class teste {

	public static void main(String[] args) {

		// testar();
		//inseri();
		
		//buscar();
	}

	public static void testar() {

		Conexao conexao;
		try {
			conexao = new Conexao();
			conexao.getConexao();

			System.out.println("S");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			System.out.println("n" + e.getMessage());
		}

	}
	
	public static void inseri() {

		
		try {
			
			Funcionario p = new Funcionario();
			
			p.setNome("mamaqeqeasd");
			p.setCpf("44112533669");
			p.setDataNascimento(new Date());
			p.setSalario(56);
			
			
			Funcionario_DAO dao = new Funcionario_DAO();
			
			dao.inserir(p);
			

			System.out.println("s");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			System.out.println("n" + e.getMessage());
		}

	}
	
	/*public static void buscar() {
		
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		Funcionario_DAO dao = new Funcionario_DAO();
		
		funcionarios = dao.buscarTodos();
		
		for (int i = 0; i < funcionarios.size(); i++) {
			
			
			System.out.println("id" + funcionarios.get(i).getId());
			
			System.out.println("Nome" + funcionarios.get(i).getNome());
			
			System.out.println("Data" + funcionarios.get(i).getDataNascimento());
			
			System.out.println("CPF" + funcionarios.get(i).getCpf());
			
			System.out.println("Salario" + funcionarios.get(i).getSalario());
			
		}
		
	}*/
	
	

}

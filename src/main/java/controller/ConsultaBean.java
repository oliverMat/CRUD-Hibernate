package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.omnifaces.util.Messages;
import org.primefaces.event.RowEditEvent;

import dao.Funcionario_DAO;
import model.Funcionario;

@ManagedBean(name = "consultaBean")
@ViewScoped
public class ConsultaBean {

	@Inject
	private Funcionario funcionario;

	private List<Funcionario> todos = new ArrayList<Funcionario>();

	@Inject
	private Funcionario_DAO dao;

	private Funcionario funcionarioSelecionado;

	public ConsultaBean() {
		funcionario = new Funcionario();
	}

	@PostConstruct
	public void inicializar() {// carrega a lista quando inicia a class

		try {

			this.todos = dao.buscarTodos();

		} catch (Exception e) {
			// TODO Auto-generated

		}

	}

	public void excluir() {

		try {

			this.dao.excluir(funcionarioSelecionado.getIdFuncionario());
			this.todos.remove(funcionarioSelecionado);

			Messages.addGlobalInfo("Funcionario " + funcionarioSelecionado.getNome() + " excluido!");

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao excluir " + e.getMessage());

			e.printStackTrace();
		}

	}

	public void pesquisar() {

		if (!this.funcionario.getNome().isEmpty() && this.funcionario.getNome() != null) {
			this.todos = dao.listarFintrados(funcionario);
		} else {

			Messages.addGlobalInfo("Campo vazio");

		}

	}

	public void exibirTodos() {

		try {

			this.todos = dao.buscarTodos();

		} catch (Exception e) {
			// TODO Auto-generated

		}

	}

	public List<Funcionario> getTodos() {// lista todos os itens
		return todos;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void onRowEdit(RowEditEvent editEvent) { // evento de edicao

		try {

			 funcionario = (Funcionario) editEvent.getObject();

			if (!funcionario.getNome().matches(".*\\d.*") && !funcionario.getNome().isEmpty()
					&& funcionario.getNome() != null) {

				if (!funcionario.getCpf().isEmpty() && funcionario.getCpf() != null && funcionario.getCpf().length() == 14) {

					if (funcionario.getSalario() >= 0 && funcionario.getSalario() != 0) {

						this.dao.inserir(funcionario);

						Messages.addGlobalInfo("Alteraçao feita com sucesso");

					} else {

						Messages.addGlobalError("Salario nao pode ser negativo o ser: 0,00");
						
						exibirTodos();

					}

				} else {
					//System.out.print(funcionario.getCpf().length());
					Messages.addGlobalError("Erro, CPF invalido !!!\n");
					
					exibirTodos();
				}

			} else {

				Messages.addGlobalError("Nome nao pode ter numeros");
				
				exibirTodos();
			}

		} catch (Exception e) {
			// TODO: handle exception

			Messages.addGlobalInfo("Nao alterado " + e.getMessage());
			
			exibirTodos();

			e.printStackTrace();
		}

	}

	public void onRowCancel(RowEditEvent event) {// evento de cancelamento de edicao
		Messages.addGlobalInfo("Alteraçao cancelada");
	}

	public Funcionario getFuncionario() {

		if (this.funcionario == null) {

			this.funcionario = new Funcionario();

		}

		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}

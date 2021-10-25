package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import dao.Funcionario_DAO;
import model.Funcionario;

@ManagedBean(name = "validacaoBean")
@ViewScoped
public class ValidacaoBean {

	@Inject
	private Funcionario funcionario;

	@Inject
	private Funcionario_DAO dao;

	public void salvar() {

		// int a = (int) (dt / 86400000L);
		// System.out.println(dt / 86400000L); // passaram-se 67111 dias

		try {

			if (!funcionario.getNome().matches(".*\\d.*") && !funcionario.getNome().isEmpty()
					&& funcionario.getNome() != null) {

				if (funcionario.getDataNascimento() != null && calculoData() <= -6570) {// -6570 equivale ha 18 anos em
																						// dias

					if (!funcionario.getCpf().isEmpty() && funcionario.getCpf() != null) {

						if (funcionario.getSalario() >= 0 && funcionario.getSalario() != 0) {

							dao.inserir(funcionario);

							Messages.addGlobalInfo("Funcionario Salva");

							System.out.println("salva");

							this.limpar();

							// System.out.printf("%s\n", ValidaCPF.imprimeCPF(CPF));

						} else {

							Messages.addGlobalError("Salario nao pode ser negativo o ser: 0,00");

							this.limpar();
						}

					} else {
						System.out.print(funcionario.getCpf().length());
						Messages.addGlobalError("Erro, CPF invalido !!!\n");
					}

				} else {
					System.out.println("vc e de menor");
					Messages.addGlobalError("Voce nao tem +18 anos de idade!!");
				}

			} else {

				Messages.addGlobalError("Nome nao pode ter numeros");
			}

		} catch (Exception e) { // TODO: handle exception

			Messages.addGlobalError("Erro ao salvar" + e.getMessage());

			System.out.println("Erro" + e.getMessage());
			this.limpar();
		}

	}

	public int calculoData() {// Para saber a diferença de dias entre duas datas, transforme-as para
								// milissegundos, obtenha a diferença, some 1 hora (devido a problemas de
								// horário de verão etc.) e divida por 86400000.

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		Date d1 = new Date();
		System.out.println(d1);
		Date d2 = funcionario.getDataNascimento();
		System.out.println(d2);
		long dt = (d2.getTime() - d1.getTime()) + 3600000; // 1 hora para compensar horário de verão

		return (int) (dt / 86400000L);
	}

	public void limpar() {// limpa o campo quando salva itens
		this.funcionario = new Funcionario();
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
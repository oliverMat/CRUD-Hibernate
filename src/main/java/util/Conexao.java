package util;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexao {

	private final String SERVIDOR = "localhost"; // Local do Banco de Dados.
	private final String PORTA = "3306"; // Classe Java do Framework que foi baixado na web.
	private final String BANCO_DE_DADOS = "crud"; // Login do usuário no SGBD.
	private final String USUARIO = "root";
	private final String SENHA = "1234";
	private final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA + "/" + BANCO_DE_DADOS;

	private Connection conexao;
	
	public Conexao() throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conexao = DriverManager.getConnection(URL,USUARIO,SENHA);
		conexao.setAutoCommit(true);
		
	}
	
	public Connection getConexao() {
		return conexao;
	}
}

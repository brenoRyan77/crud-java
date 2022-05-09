package br.com.contatos.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	//Nome do usu�rio mysql
	private static final String USERNAME = "root";
	
	//Senha do banco
	private static final String PASSWORD = "";
	
	//Caminho do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
	
	
	/*
	 * Conex�o com o banco
	 */
	public static Connection criandoConexaoBanco() throws Exception {
		//Faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.jdbc.Driver");
		
		//Cria conex�o com o banco
		Connection conx = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return conx;
	}
	
	public static void main(String[] args) throws Exception {
		
		//Recuperar uma conex�o com o banco
		Connection conx = criandoConexaoBanco();
		
		//Testar se conex�o � null
		if(conx != null) {
			System.out.println("Conex�o obtida com sucesso!");
			conx.close();
		}
	}
	
}

package br.com.contatos.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import br.com.contatos.Exceptions.DbExceptions;

public class ConnectionFactory {

	//Nome do usuário mysql
	private static final String USERNAME = "root";
	
	//Senha do banco
	private static final String PASSWORD = "";
	
	//Caminho do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
	
	private static Connection conx = null;
	
	
	/*
	 * Conexão com o banco
	 */
	public static Connection criandoConexaoBanco() throws Exception {
		//Faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.jdbc.Driver");
		
		//Cria conexão com o banco
		conx = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return conx;
	}
	
	public static void main(String[] args) throws Exception {
		
		//Recuperar uma conexão com o banco
		conx = criandoConexaoBanco();
		
		//Testar se conexão é null
		if(conx != null) {
			System.out.println("Conexão obtida com sucesso!");
			conx.close();
		}
	}
	
	public static void fecharConexao(Connection conx) {
		
		if(conx != null) {
			try {
				conx.close();
			}catch(SQLException e) {
				throw new DbExceptions(e.getMessage());
			}
		}
	}
	
	public static void fecherResultSet(ResultSet rs) {
		
		if(rs != null) {
			try{
				rs.close();
			}catch(SQLException e) {
				throw new DbExceptions(e.getMessage());
			}
		}
	}
	
	public static void fecharStatement(PreparedStatement st) {
		
		if(st != null) {
			try {
				st.close();
			}catch(SQLException e) {
				throw new DbExceptions(e.getMessage());
			}
		}
	}
}

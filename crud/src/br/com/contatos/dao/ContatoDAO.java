package br.com.contatos.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.contatos.factory.ConnectionFactory;
import br.com.contatos.model.Contato;

public class ContatoDAO {

	public void salvarContato(Contato contato) {
		
		String sql = "INSERT INTO contatos("
				+ "	nome,"
				+ " idade,"
				+ " datacadastro)"
				+ " VALUES(?, ?, ?)";
		
		Connection conx = null;
		PreparedStatement pstm = null;
		
		try {
			
			//Criar conexão com o banco
			conx = ConnectionFactory.criandoConexaoBanco();
			
			//Cria uma PreparedStatement para executar uma query
			pstm = (PreparedStatement) conx.prepareStatement(sql);
			
			//Adiciona os valores esperado pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			//Executa a query
			pstm.execute();
			
			System.out.println("Contato salvo com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}//try-catch
		finally {
			
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conx != null) {
					conx.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}//try-catch
		}
	}
	
	public List<Contato> listarContatos(){
		
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conx = null;
		PreparedStatement pstm = null;
		
		//Classe que recupera os dados do banco
		ResultSet rset = null;
		
		try {
			conx = ConnectionFactory.criandoConexaoBanco();
			pstm = (PreparedStatement) conx.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				Contato contato = new Contato();
				
				contato.setId(rset.getInt("id"));
				contato.setNome(rset.getNString("nome"));
				contato.setIdade(rset.getInt("idade"));
				contato.setDataCadastro(rset.getDate("datacadastro"));
				
				contatos.add(contato);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}//try-catch
		finally {
			
			try {
				
				if(rset != null) {
					rset.close();
				}
				if(pstm != null) {
					pstm.close();
				}
				if(conx != null) {
					conx.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}//try-catch
		}
		return contatos;
		
	}

	public void editarContato(Contato contato) {
		
		String sql = "UPDATE contatos"
				+ " SET"
				+ " nome = ?,"
				+ " idade = ?,"
				+ " datacadastro = ? " +
				  " where id = ?";
		
		Connection conx = null;
		PreparedStatement pstm = null;
		
		try {
			conx = ConnectionFactory.criandoConexaoBanco();
			pstm = (PreparedStatement) conx.prepareStatement(sql);
			
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			pstm.setInt(4, contato.getId());
			
			pstm.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}//try-catch
		
		finally {
			try {
				if(conx != null) {
					conx.close();
				}
				if(pstm != null) {
					pstm.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}//try-catch
			
		}//finally
	}

	public void deletarContatoPorId(Integer id) {
		
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conx = null;
		PreparedStatement pstm = null;
		
		try {
			conx = ConnectionFactory.criandoConexaoBanco();
			pstm = (PreparedStatement) conx.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}//try-catch
		finally {
			try {
				if(conx != null) {
					conx.close();
				}
				if(pstm != null) {
					pstm.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}//try-catch
		}//finally
	}
}

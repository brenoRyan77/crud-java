package br.com.contatos.application;

import java.util.Date;

import br.com.contatos.dao.ContatoDAO;
import br.com.contatos.model.Contato;

public class Main {

	public static void main(String[] args) {
		
		ContatoDAO contatoDao = new ContatoDAO();
		
		Contato contato = new Contato();
		
		  contato.setNome("Gabriel Nogueira");
		  contato.setIdade(13);
		  contato.setDataCadastro(new Date());
		  
		  contatoDao.salvarContato(contato);
		 
		
		/*Editar contatos
		 * Contato c1 = new Contato(); c1.setNome("João Gomes"); c1.setIdade(25);
		 * c1.setDataCadastro(new Date()); c1.setId(4);
		contatoDao.editarContato(c1);
		 */
		
		
		//Deletar contatos
		//contatoDao.deletarContatoPorId(4);
		
		
		for(Contato c : contatoDao.listarContatos()) {
		System.out.println("Contatos: " + c.getNome() + ", " + c.getIdade() + " anos");
		}

	}

}

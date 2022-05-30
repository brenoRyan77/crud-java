package br.com.contatos.Exceptions;

public class DbExceptions extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public  DbExceptions(String msg) {
		super(msg);
	}

}

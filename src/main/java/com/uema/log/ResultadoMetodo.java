package com.uema.log;

/**
 * Representa o resultado da execução de métodos.
 * <p>
 * Guardamos o estado da execução, se ocorreu tudo bem ou não e
 * guardamos o valor de retorno da execução do método.
 * 
 * @author Antonio Iago Lemos Gaspar
 * 
 * @version %I%, %G%
 * 
 * */
public class ResultadoMetodo {
	/**
	 * O estado da execução, se ocorreu tudo bem ou ocorreu falha
	 * */
	private boolean executou;
	/**
	 * O valor de retorno da execução
	 * */
	private Object valor;
	
	/**
	 * Método construtor
	 * 
	 * @param executou O estado da execução de um método
	 * @param valor    O valor retornado caso a execução ocorreu bem
	 * */
	public ResultadoMetodo(boolean executou, Object valor) {
		this.executou = executou;
		this.valor = valor;
	}

	public Object getResultado() {
		return valor;
	}

	public boolean executou() {
		return executou;
	}
	
	/**
	 * Retorna um ResultadoMetodo apropriado para
	 * casos de não execução do método.
	 * 
	 * @return Um ResultadoMetodo com o estado de não execução
	 * */
	public static ResultadoMetodo naoPodeExecutar() {
		
		return new ResultadoMetodo(false, null);
	}

}

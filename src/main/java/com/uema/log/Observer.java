package com.uema.log;

/**
 * Interface observer
 * <p>
 * Define os métodos necessários para salvar o log de auditaveis
 * 
 * @author Antonio Iago Lemos Gaspar
 * @version %I%, %G%
 * */
public interface Observer<T> {

	/**
	 * Verifica se o Observer monitora a classe passada como parâmetro.
	 * 
	 * @param clazz A classe a ser avaliada
	 * 
	 * @return <code>true</code> se o Observer monitora objetos da classe
	 * */
	public boolean handles(Class<?> clazz);

	/**
	 * Salva os dados do log na base de dados.
	 * 
	 * @param auditavel   Um objeto que deve ser registrado no log caso haja alguma alteração em seus atributos
	 * @param acao        A ação que ocorreu no objeto
	 * @param campo       O campo do objeto que houve uma modificação
	 * @param valorAntigo O valor atual do objeto para esse campo
	 * @param novoValor   O novo valor a ser atribuído no campo
	 *  
	 * */
	public void notificarMudanca(T auditavel, Action acao, String campo, Object valorAntigo, Object novoValor);

}

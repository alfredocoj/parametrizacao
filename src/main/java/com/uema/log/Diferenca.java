package com.uema.log;

import java.util.Map.Entry;

/**
 * Classe responsável por representar uma diferença.
 * <p>
 * Cada entidade possui diversos campos, e cada campo pode ser modificado.
 * Cada modificação gere uma diferença, entra o valor antigo e o novo valor.
 * Portanto, para cada modificação na entidade, mapeamos a diferença e salvamos
 * na base de log.
 * 
 * @author Antonio Iago Lemos Gaspar
 * @version %I%, %G%
 * */
public class Diferenca {
	
	/**
	 * O valor antigo do campo
	 * */
	private Object valorAntigo;
	/**
	 * O novo valor do campo
	 * */
	private Object novoValor;
	/**
	 * O nome do campo que sofreu uma modificação
	 * */
	private String campo;
	
	/**
	 * Método construtor
	 * 
	 * @param antigo O valor antigo
	 * @param novo   Um {@link Entry} contendo o campo e o novo valor do campo
	 * */
	public Diferenca(Object antigo, Entry<String, Object> novo) {
		this.valorAntigo = antigo;
		this.novoValor = novo.getValue();
		this.campo = novo.getKey();
	}
	
	
	public Object getValorAntigo() {
		return valorAntigo;
	}

	public Object getNovoValor() {
		return novoValor;
	}

	public String getCampo() {
		return campo;
	}
	
	/**
	 * Cria um id único para a Diferença com base no campo,
	 * no valor antigo e no novo valor.
	 * <p>
	 * O hashcode é definido da seguinte forma:
	 * <p>
	 * <code>(this.campo == null) ? 0 : this.campo.hashCode() ^</code>
	 * <p>
	 * <code>(this.novoValor == null) ? 0 : this.novoValor.hashCode()) ^</code>
	 * <p>
	 * <code>(this.valorAntigo == null) ? 0 : this.valorAntigo.hashCode()</code>
	 * 
	 * @return O hashcode do objeto da classe Diferença
	 * */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.campo == null) ? 0 : this.campo.hashCode());
		result = prime * result + ((this.novoValor == null) ? 0 : this.novoValor.hashCode());
		result = prime * result + ((this.valorAntigo == null) ? 0 : this.valorAntigo.hashCode());
		return result;
	}
	
	/**
	 * Compara se a diferença <code>e1</code> é igual à diferença <code>e2</code>.
	 * <p>
	 * Basicamente um objeto <code>e1</code> será diferente de <code>e2</code> se alguma das seguintes condições não forem satisfeitas:
	 * <ul>
	 * <li> <code>e2 == null</code>
	 * <li> <code>e1.getClass() != e2.getClass()</code>
	 * <li> <code>e1.getCampo() == null && e2.getCampo() != null</code>
	 * <li> <code>!e1.getCampo().equals(e2.getCampo())</code>
	 * <li> <code>e1.getNovoValor() == null && e2.getNovoValor() != null</code>
	 * <li> <code>!e1.getNovoValor().equals(e2.getNovoValor())</code>
	 * <li> <code>e1.getValorAntigo() == null && e2.getValorAntigo() != null</code>
	 * <li> <code>!e1.getValorAntigo().equals(e2.getValorAntigo())</code>
	 * </ul>
	 * <p>
	 * Satisfazendo qualquer uma das condições listadas acima o método retornará <code>false</code>.
	 * 
	 * @return <code>true</code> se o objeto especificado for igual a esta diferença 
	 * */
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(this.getClass() != obj.getClass()) return false;
		
		Diferenca outro = (Diferenca) obj;
		
		if(this.campo == null) {
			if(outro.getCampo() != null) return false;
		} else if(!this.campo.equals(outro.getCampo())) return false;
		
		if(this.novoValor == null) {
			if(outro.getNovoValor() != null) return false;
		} else if(!this.novoValor.equals(outro.getNovoValor())) return false;
		
		if(this.valorAntigo == null) {
			if(outro.getValorAntigo() != null) return false;
		} else if(!this.valorAntigo.equals(outro.getValorAntigo())) return false;
		
		return true;
	}
	
	

}

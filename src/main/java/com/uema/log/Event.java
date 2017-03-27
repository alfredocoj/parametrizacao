package com.uema.log;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Entidade responsável por representar a tabela os logs do sistema.
 * 
 * @author Antonio Iago Lemos Gaspar
 * @version %I%, %G%
 * */
@Entity
public class Event {
	
	/**
	 * Campo que não será salvo na base de dados.
	 * Representa uma entidade que herda de Auditavel.
	 * */
	@Transient
	private transient Auditavel auditavel;
	
	/**
	 * O id de registro da tabela
	 * */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	/**
	 * A data e a hora de criação do registro
	 * */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	/**
	 * A ação executada por um usuário
	 * */
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Action action;
	
	/**
	 * O campo da entidade auditada que ocorreu a modificação
	 * */
	@Column
	private String fieldName;
	
	/**
	 * O valor antigo do campo modificado
	 * */
	@Column
	private String previousValue;
	
	/**
	 * O novo valor do campo
	 * */
	@Column
	private String newValue;
	
	/**
	 * A identificação do usuário que realizou a operação
	 * sobre a auditavel
	 * */
	@Column
	private String usuario;
	
	/**
	 * Método construtor
	 * <p>
	 * Alguns campos do Evento não serão preenchidos, de acordo com a ação executada.
	 * <p>
	 * Por exemplo: quando a ação é <code>CREATE</code>, não teremos um campo específico
	 * sendo modificado, mas sim um conjunto de campos da classe. O mesmo para a exclusão.
	 * Teremos o valor campo preenchido quando houver uma ação <code>UPDATE</code>. Mapearemos
	 * o campo, o valor antigo e o novo valor e armazenaremos no banco de dados.
	 * <p>
	 * Em casos de <code>CREATE</code> e <code>DELETE</code>, é interessante implementar o toString da entidade,
	 * para que a entidade possua uma melhor representação no log.
	 * 
	 * @param auditavel O objeto da classe que herda de Auditavel
	 * @param acao      A ação realizada no sitema
	 * @param campo     O campo que foi modificado. Pode ser nulo em casos <code>CREATE</code> e <code>DELETE</code>
	 * @param valorAntigo O valor antigo do campo. Pode ser o objeto inteiro em caso de exclusão
	 * @param valorNovo O valor a ser atribuído ao campo. Pode ser o objeto inteiro caso seja uma inserção
	 * @param usuario   A representação do usuário no sistema.
	 * 
	 * @see Auditavel
	 * @see Object#toString()
	 * */
	public Event(Auditavel auditavel,
					Action acao,
					String campo,
					String valorAntigo,
					String valorNovo,
					String usuario) {
		this.auditavel = auditavel;
		this.timestamp = new Date();
		this.action = acao;
		this.fieldName = campo;
		this.previousValue = valorAntigo;
		this.newValue = valorNovo;
		this.usuario = usuario;
	}
	
	@Override
	public String toString() {
		
		return "Usuário: " + this.usuario
				+ " valor antigo: " + this.previousValue
				+ " novo valor: " + this.newValue;
	}

	public Auditavel getAuditavel() {
		return auditavel;
	}

	public void setAuditavel(Auditavel auditavel) {
		this.auditavel = auditavel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getPreviousValue() {
		return previousValue;
	}

	public void setPreviousValue(String previousValue) {
		this.previousValue = previousValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
	
	

}

package com.uema.log;

import java.util.Map;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
/**
 * Classe que representa um entidade que pode ser auditada.
 * <p>
 * Uma entidade Auditavel possui suas transações registradas em uma tabela de
 * logs. Se um usuário criar, atualizar ou excluir dados de uma determinada
 * entidade que herda de Auditavel, então essa operação será registrada
 * na base de dados. A classe {@link Event} é a entidade que representa
 * a tabela de logs.
 * <p>
 * A classe {@link AuditavelListener} é responsável por monitorar os eventos.
 * Para mais detalhes, veja a documentação da classe.
 * 
 * @author Antonio Iago Lemos Gaspar
 * @version %I%, %G%
 * 
 * @see Event
 * @see AuditavelListener
 * 
 * */
@MappedSuperclass
@EntityListeners(AuditavelListener.class)
public class Auditavel {
	@Transient
	public transient Map<String, Object> valoresCarregados;
	
}

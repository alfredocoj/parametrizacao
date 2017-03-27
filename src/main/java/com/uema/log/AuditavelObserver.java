package com.uema.log;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

/**
 * Classe observadora, responsável por registrar as mudanças
 * nas entidades em uma tabela de log.
 * 
 * @author Antonio Iago Lemos Gaspar
 * @version %I%, %G%
 * 
 * */
@Repository
@Transactional
public class AuditavelObserver implements Observer<Auditavel> {
	
	/**
	 * O contexto da aplicação. Obtemos através dele o bean que constrói
	 * {@link EntityManager}.
	 * */
	@Autowired
	private ApplicationContext context;
	
	/**
	 * O objeto requisição. Através dele obtemos os dados de sessão.
	 * <p>
	 * Com os dados de sessão, registramos o usuário que realizou a ação.
	 * */
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * Verifica se a classe é filha da classe {@link Auditavel}.
	 * 
	 * @param clazz A classe a ser testada.
	 * 
	 * @return <code>true</code> se a classe é filha ou é a classe {@link Auditavel}.
	 * 
	 * */
	@Override
	public boolean handles(Class<?> clazz) {
		
		return Auditavel.class.isAssignableFrom(clazz);
	}
	
	/**
	 * Registra a mudança que um usuário fez em uma entidade.
	 * 
	 * @param auditavel   A entidade que herda de Auditavel
	 * @param acao        A ação executada (CREATE, UPDATE ou DELETE)
	 * @param campo	      O campo da entidade (o atributo) que foi modificado
	 * @param valorAntigo O valor antigo que existia no campo (nulo caso seja uma criação)
	 * @param novoValor   O novo valor a ser atribuído ao campo
	 * 
	 * @see Event
	 * */
	@Override
	public void notificarMudanca(Auditavel auditavel, Action acao, String campo, Object valorAntigo, Object novoValor) {
		
		String usuario = (String) request.getSession().getAttribute("cpf");
		
		Event event = new Event(auditavel,
								acao, 
								campo,
								ToStringHelper.toString(valorAntigo),
								ToStringHelper.toString(novoValor), usuario);
		
		// Para inserir um valor no banco de dados através dos eventos da JPA,
		// necessitamos de uma nova thread que faça essa tarefa. Segundo a
		// documentação, não deve ser feito nenhuma persistência de dados nos 
		// eventos da JPA. Portanto, criamos essa thread para ser possível realizar
		// a inserção.
		//
		Thread inserirLog = new Thread(new Runnable() {	
			@Override
			public void run() {
				// Dentro da thread não é possível
				// obter a bean de EntityManager e também
				// Ela não é thread-safe. Precisamos obter
				// a factory de EntityManager e pedir uma instância
				// própria para esta thread. Logo após, nós liberamos
				// o recurso.
				//
				EntityManagerFactory emf = (EntityManagerFactory) context.getBean("entityManagerFactory");
				EntityManager manager = emf.createEntityManager();
				manager.getTransaction().begin();
				manager.persist(event);
				manager.getTransaction().commit();
				manager.close();
			}
		});
		
		inserirLog.start();
		try {
			inserirLog.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}

package com.uema.log;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe responsável por guardar os observadores do sistema. A cada nova mudança,
 * a RegistradorObserver irá notificar cada observador.
 * 
 * @author Antonio Iago Lemos Gaspar
 * @version %I%, %G%
 * @see Observer
 * 
 * */
public class RegistradorObserver {
	
	/**
	 * Container de observers
	 * */
	private static Set<Observer<?>> observers = new HashSet<>();
	
	/**
	 * Notifica os observer que ocorreu uma mudança em objetos do tipo {@link Auditavel}.
	 * 
	 * @param auditavel   O objeto que herda de Auditavel
	 * @param acao        A ação que o objeto sofreu (CREATE, UPDATE, DELETE)
	 * @param campo       O campo que sofreu uma modificação (pode ser nulo para casos de inserção e exclusão)
	 * @param valorAntigo O valor antigo do campo (pode ser nulo em casos de inserção ou o objeto auditavel em casos de exclusão)
	 * @param novoValor   O novo valor do campo (pode ser nulo em casos de exclusão ou o objeto auditavel em casos de inserção)
	 * 
	 * */
	public static void notificarMudanca(Object auditavel, Action acao, String campo, Object valorAntigo,
			Object novoValor) {
		for(Observer observer : observers) {
			if(observer.handles(auditavel.getClass())) {
				observer.notificarMudanca(auditavel, acao, campo, valorAntigo, novoValor);
			}
		}
		
	}
	
	/**
	 * Registra um observer.
	 * 
	 * @param observer O objeto observador
	 * 
	 * */
	public static void registrar(Observer<?> observer) {
		observers.add(observer);
	}
	
	/**
	 * Remove um observer.
	 * 
	 * @param observer O observer a ser removido
	 * 
	 * */
	public static void remover(Observer<?> observer) {
		observers.remove(observer);
	}

}

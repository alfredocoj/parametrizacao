package com.uema.log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

/**
 * Classe responsável por monitorar as entidades auditáveis.
 * <p>
 * Cada evento em uma entidade, como uma inserção ou exclusão, é mapeado
 * através das anotações da JPA. Cada anotação mapeia um método que será
 * executado quando o evento ocorrer.
 * 
 * @author Antonio Iago Lemos Gaspar
 * @version %I%, %G%
 * 
 * @see Auditavel
 * */
public class AuditavelListener {
	
	/**
	 * Recebe uma entidade e guarda os valores que foram carregados.
	 * 
	 * @param auditavel Uma entidade que herda de {@link Auditavel}.
	 * */
	@PostLoad
	public void postLoad(Auditavel auditavel) {
		auditavel.valoresCarregados = getValores(auditavel);
	}
	
	/**
	 * Recebe um entidade e a registra no log, informando que ela será excluida.
	 * <p>
	 * Esse método é executado antes do evento de exclusão.
	 * 
	 * @param auditavel Uma entidade que herda de {@link Auditavel}
	 * */
	@PostRemove
	public void preRemove(Auditavel auditavel) {
		RegistradorObserver.notificarMudanca(auditavel, Action.DELETE, null, auditavel, null);
	}
	
	/**
	 * Recebe uma entidade e registra no log, informando que ela será criada.
	 * <p>
	 * Esse método é executado antes do evento de inclusão.
	 * 
	 * @param auditavel Uma entidade que herda de {@link Auditavel}
	 * */
	@PostPersist
	public void prePersist(Auditavel auditavel) {
		RegistradorObserver.notificarMudanca(auditavel, Action.CREATE, null, null, auditavel);
	}
	
	/**
	 * Recebe uma entidade e registra no log, informando que ela será atualizada.
	 * <p>
	 * Esse método é executado antes do evento de atualização.
	 * 
	 * @param auditavel Uma entidade que herda de {@link Auditavel}
	 * */
	@PreUpdate
	public void preUpdate(Auditavel auditavel) {
		Map<String, Object> novosValores = getValores(auditavel);
		Map<String, Object> valoresAntigos = auditavel.valoresCarregados;
		
		if(valoresAntigos == null) {
			System.err.println("Os valores antigos estão nulos.");
			return;
		}
		
		for(Diferenca diferenca : getDiferencas(valoresAntigos, novosValores)) {
			RegistradorObserver.notificarMudanca(auditavel, Action.MODIFY, diferenca.getCampo(), diferenca.getValorAntigo(), diferenca.getNovoValor());
		}
	}
	
	/**
	 * Retorna um conjunto de diferenças entre o registro antigo e sua nova versão,
	 * de acordo com cada campo da entidade.
	 * 
	 * @param valoresAntigos Um {@link Map} contendo os valores antigo da entidade
	 * @param novosValores   Um {@link Map} contendo os novos valores da entidade
	 * 
	 * @return Um conjunto de diferenças.
	 * 
	 * @see Diferenca
	 * */
	private Set<Diferenca> getDiferencas(Map<String, Object> valoresAntigos, Map<String, Object> novosValores) {
		Set<Diferenca> diferencas = new HashSet<>();
		
		for(Entry<String, Object> registro : novosValores.entrySet()) {
			Object antigo = valoresAntigos.get(registro.getKey());
			
			if(antigo == null) {
				if(registro.getValue() != null) {
					diferencas.add(new Diferenca(null, registro));
				}
			} else {
				if(!antigo.equals(registro.getValue())) {
					diferencas.add(new Diferenca(antigo, registro));
				}
			}
		}
		return diferencas;
	}
	
	/**
	 * Recebe uma entidade e retorna um {@link Map} com os valores contidos no objeto.
	 * 
	 * @param auditavel Uma entidade que herda de {@link Auditavel}
	 * 
	 * @return Um <code>Map</code> contendo o nome do campo e o valor do campo.
	 * 
	 * @see ResultadoMetodo
	 * */
	private Map<String, Object> getValores(Auditavel auditavel) {
		Class<?> clazz = auditavel.getClass();
		Map<String, Object> map  = new HashMap<>();
		
		for(Field field : clazz.getFields()) {
			// Se campos são transientes, vamos ignorar
			//
			if(field.getAnnotation(Transient.class) != null) {
				continue;
			}
			
			// Obtendo o nome dos métodos getter e is.
			//
			String campoCapitalizado = StringUtils.capitalize(field.getName().toLowerCase());
			String metodoGetter      = "get" + campoCapitalizado;
			String isName            = "is" + campoCapitalizado;
			
			// Executa o método getter e guarda o resultado no map
			//
			ResultadoMetodo resultadoMetodo = executarMetodo(metodoGetter, auditavel); 
			if(resultadoMetodo.executou()) {
				map.put(field.getName(), resultadoMetodo.getResultado());
				continue;
			}
			
		}
		return map;
	}
	
	/**
	 * Executa o método e retorna um objeto do tipo {@link ResultadoMetodo},
	 * contendo o valor do resultado.
	 * 
	 * @param metodo O método a ser executado.
	 * @param auditavel A entidade que possui o método.
	 * 
	 * @return O resultado da execução.
	 * 
	 * @see ResultadoMetodo
	 * */
	private ResultadoMetodo executarMetodo(String metodo, Object auditavel) {
		
		return invocarMetodo(getMetodo(auditavel.getClass(), metodo), auditavel);
	}
	
	/**
	 * Invoca o método definido no objeto.
	 * 
	 * @param metodo O método a ser invocado.
	 * @param auditavel O objeto que possui o método.
	 * 
	 * @return O resultado da invocação do método.
	 * 
	 * @see ResultadoMetodo
	 * */
	private ResultadoMetodo invocarMetodo(Method metodo, Object auditavel) {
		
		if(metodo == null) {
			return ResultadoMetodo.naoPodeExecutar();
		}
		try {
			return new ResultadoMetodo(true, metodo.invoke(auditavel));
		} catch(IllegalArgumentException e) {
			return ResultadoMetodo.naoPodeExecutar();
		} catch(IllegalAccessException e) {
			return ResultadoMetodo.naoPodeExecutar();
		} catch(InvocationTargetException e) {
			return ResultadoMetodo.naoPodeExecutar();
		}
	}
	
	/**
	 * Obtém o método da classe e retorna um objeto do tipo {@link Method}.
	 * 
	 * @param clazz A classe de onde extraímos o método.
	 * @param metodo O nome do método
	 * 
	 * @return O objeto <code>Method</code>.
	 * 
	 * @see Method
	 * */
	private Method getMetodo(Class<?> clazz, String metodo) {
		Method method = null;
		
		try {
			method = clazz.getMethod(metodo);
		} catch(SecurityException e) {
			method = null;
		} catch (NoSuchMethodException e) {
			method = null;
		}
		return method;
	}
}

package com.uema.log;

public class ToStringHelper {
	
	/**
	 * Retorna a representação String do objeto.
	 * <p>
	 * Se for um objeto iteravel, retorna uma String que representa
	 * a lista de objetos.
	 * 
	 * @param objeto O objeto
	 * 
	 * @return A representação String do objeto
	 * 
	 * */
	public static String toString(Object objeto) {
		if(objeto == null) {
			return "null";
		}
		if(Iterable.class.isAssignableFrom(objeto.getClass())) {
			return toStringIterable((Iterable) objeto);
		} else {
			return objeto.toString();
		}
	}

	/**
	 * Retorna a representação String de um objeto Iterable.
	 * 
	 * @param objeto O objeto que herda de Iterable
	 * 
	 * @return A representação String da classe Iterable
	 * 
	 * */
	private static String toStringIterable(Iterable objeto) {
		StringBuilder builder = new StringBuilder("Iterable(");
		boolean hasElements = false;
		for(Object element : objeto) {
			hasElements = true;
			builder.append(element.toString());
			builder.append(", ");
		}
		if(hasElements) {
			builder.delete(builder.length() - 3, builder.length() - 1);
		}
		builder.append(")");
		return builder.toString();
	}

}

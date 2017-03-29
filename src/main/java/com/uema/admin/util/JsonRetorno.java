package com.uema.admin.util;

/**
*
* Historico de Alterações
*
* @created_at 1.0.0 : 30/01/2017 Vinicius Ribas - Emissão inicial
*
*/

/**
 * Classe JsonMensagem - Encapsula as informações do código dentro do objeto código
 *
 * @author Vinicius Ribas - viniciusribas.uema.dpd@gmail.com
 *
 * @category Java
 * @category Spring
 *
 * @package Util
 * @version 1.0.0
 * @copyright Copyright (c) 2017 NTI UEMA
 * @date 30/01/2017
 */

public class JsonRetorno {
	
	public int code;
	public String message;
	
	public void defineMeta(int code){
		this.code = code;
	}
	
	public void defineMeta(int code, String message){
		this.code = code;
		this.message = message;
	}

}

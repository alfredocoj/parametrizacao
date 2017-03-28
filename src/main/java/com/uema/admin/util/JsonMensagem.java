package com.uema.admin.util;

/**
*
* Historico de Alterações
*
* @created_at 1.0.0 : 30/01/2017 Vinicius Ribas - Emissão inicial
*
*/

/**
 * Classe JsonMensagem - Encapsula as informações do código meta e a mensagem de aviso 
 *
 * @author Vinicius Oliveira - viniciusribas.uema.dpd@gmail.com
 *
 * @category Java
 * @category Spring
 *
 * @package Util
 * @version 1.0.0
 * @copyright Copyright (c) 2017 NTI UEMA
 * @date 30/01/2017
 */

public class JsonMensagem {
	
	public int code;
	public String error_message;
	
	public void defineMeta(int code, String warning_message){
		this.code = code;
		this.error_message = warning_message;
	}

}

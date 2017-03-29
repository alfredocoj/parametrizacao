package com.uema.admin.util;

/**
*
* Historico de Alterações
*
* @created_at 1.0.0 : 30/01/2017 Vinicius Ribas - Emissão inicial
*
*/

/**
 * Classe JsonErroModel - Encapsula as informações do meta no objeto meta
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

public class JsonErroModel {
	
	public JsonMensagem meta;
	
	public void defineData(JsonMensagem meta){
		this.meta = meta;
	}
		
}

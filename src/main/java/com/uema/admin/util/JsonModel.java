package com.uema.admin.util;

/**
*
* Historico de Alterações
*
* @created_at 1.0.0 : 30/01/2017 Vinicius Ribas - Emissão inicial
*
*/

import com.uema.admin.entities.EntityBase;

import java.util.Collection;

/**
 * Classe JsonModel - criar um objeto com o meta e um objeto data dentro para retornar no JSON
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

public class JsonModel {
	
	public Object data;
	public JsonRetorno meta;
	
	public void defineData(JsonRetorno meta, Collection<? extends EntityBase> lista){
		this.meta = meta;
		this.data = (Collection<? extends EntityBase>) lista;
	}
	
	public void defineData(JsonRetorno meta, EntityBase entity){
		this.meta = meta;
		this.data = (EntityBase) entity;
	}
	
	public void defineData(JsonRetorno meta, String message){
		this.meta = meta;
		this.data = message;
	}
	
}

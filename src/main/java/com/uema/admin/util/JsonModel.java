package com.uema.admin.util;

/**
 *
 * Historico de Alterações
 *
 * @created_at 1.0.0 : 30/01/2017 Vinicius Ribas - Emissão inicial
 *
 */

import java.util.Collection;
import java.util.Map;

import com.uema.admin.entities.EntityBase;

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

	public void defineData(JsonRetorno meta, Object lista){
		if(lista instanceof Collection || lista instanceof Map || lista instanceof EntityBase || lista instanceof String) {
			this.meta = meta;
			this.data = lista;
		} else {
			this.meta = meta;
			this.data = null;
		}
	}
}

package com.uema.admin.util;

/**
*
* Historico de Alterações
*
* @created_at 1.0.0 : 30/01/2017 Vinicius Ribas - Emissão inicial
*
*/

import com.uema.admin.entities.EntityBase;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

/**
 * Classe ResponseSig - Retorna Mensagem de Sucesso ou erro no padrão Json 
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

public class ResponseSig extends ResponseEntity<Object>{

	public ResponseSig(HttpStatus status) {
		super(status);
		// TODO Auto-generated constructor stub
	}

	public static Object success(Collection<? extends EntityBase> all, int code) throws JSONException {
		
		//recebe mensagem e código "meta" 
		JsonRetorno mensagem = new JsonRetorno();
		//recebe objeto mensagem e a lista de objetos
		JsonModel retorno = new JsonModel();
		
		mensagem.defineMeta(code);
		retorno.defineData(mensagem, all);
			
		return ResponseSig.status(200).body(retorno);
	}
	
	public static Object success(EntityBase entity, int code, String message) throws JSONException {
		
		//recebe mensagem e código "meta" 
		JsonRetorno mensagem = new JsonRetorno();
		//recebe objeto mensagem e a lista de objetos
		JsonModel retorno = new JsonModel();
		
		mensagem.defineMeta(code, message);
		retorno.defineData(mensagem, entity);
			
		return ResponseSig.status(200).body(retorno);
	}
	
	public static Object success(int code, String message) throws JSONException {
		
		//recebe mensagem e código "meta" 
		JsonRetorno mensagem = new JsonRetorno();
		//recebe objeto mensagem e a lista de objetos
		JsonModel retorno = new JsonModel();
		
		mensagem.defineMeta(code, message);
		retorno.defineData(mensagem, "OK");
			
		return ResponseSig.status(200).body(retorno);
	}
	
	public static Object error(int code, String erro) throws JSONException{
		//recebe mensagem e código "meta" 
		JsonMensagem mensagem = new JsonMensagem();
		JsonErroModel retorno = new JsonErroModel();
		
		mensagem.defineMeta(code, erro);
		retorno.defineData(mensagem);
					
		return ResponseSig.status(code).body(retorno);
	}
	
}

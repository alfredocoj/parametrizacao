package com.uema.sigconcursos.util;

/**
*
* Historico de Alterações
*
* @created_at 1.0.0 : 30/01/2017 Alfredo Oliveira - Emissão inicial
*
*/

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Classe SigWS - Classe de comunicação com o Webservice SigWS 
 *
 * @author Alfredo Oliveira - alfredooliveira.uema.dpd@gmail.com
 *
 * @category Java
 * @category Spring
 *
 * @package Util
 * @version 1.0.0
 * @copyright Copyright (c) 2017 NTI UEMA
 * @date 30/01/2017
 */

public class SigWS {
	
	private static String url = "http://www.sigws.uema.br/"; 
	
	private static String appToken = "9cad5b4181fa8326500f2d6473fa9a36";
	
	private static String appName = "siscpa";
	
	private HttpHeaders headers;
	
	private HttpEntity<String> httpEntity;

	public SigWS() {
		this.setHeaders();
		HttpEntity<String> httpEntity = new HttpEntity<String>(this.getHeaders());
		this.setHttpEntity(httpEntity);
	}

	public static String getAppToken() {
		return appToken;
	}

	public static void setAppToken(String appToken) {
		SigWS.appToken = appToken;
	}

	public static String getAppName() {
		return appName;
	}

	public static void setAppName(String appName) {
		SigWS.appName = appName;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		SigWS.url = url;
	}

	public HttpHeaders getHeaders() {
		return this.headers;
	}

	public void setHeaders() {
		
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			headers.set("appToken", SigWS.getAppToken());
			headers.set("appName", SigWS.getAppName());
			
			this.headers = headers;
	}

	public HttpEntity<String> getHttpEntity() {
		return this.httpEntity;
	}

	public void setHttpEntity(HttpEntity<String> httpEntity) {		
		this.httpEntity = httpEntity;
	}
	
	
}

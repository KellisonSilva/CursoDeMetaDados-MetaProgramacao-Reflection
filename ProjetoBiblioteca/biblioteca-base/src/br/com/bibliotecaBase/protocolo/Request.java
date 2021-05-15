package br.com.bibliotecaBase.protocolo;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private String nomeControle;
	private String nomeMetodo;
	private Map<String, Object> queryParams;

	public Request(String url) {

		String[] partesUrl = url.replaceFirst("/", "").split("[?]");

		String[] controleEmetodo = partesUrl[0].split("/");

		nomeControle = Character.toUpperCase(controleEmetodo[0].charAt(0)) + controleEmetodo[0].substring(1)
				+ "Controller";

		nomeMetodo = controleEmetodo[1];

		queryParams = partesUrl.length > 1 ? new QueryParamsBuilder().withParams(partesUrl[1]).build()
				: new HashMap<String, Object>();

	}

	public String getNomeMetodo() {
		return nomeMetodo;
	}

	public String getNomeControle() {
		return nomeControle;
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

}

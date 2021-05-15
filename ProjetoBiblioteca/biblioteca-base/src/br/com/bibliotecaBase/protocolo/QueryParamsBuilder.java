package br.com.bibliotecaBase.protocolo;

import java.util.HashMap;
import java.util.Map;

public class QueryParamsBuilder {

	private Map<String, Object> queryParams = new HashMap<String, Object>();

	public QueryParamsBuilder withParams(String stringQueryParams) {

		String[] separator = stringQueryParams.split("&");

		for (String params : separator) {
			String[] chaveEvalor = params.split("=");

			String chave = chaveEvalor[0];
			Object valor = chaveEvalor[1];

			queryParams.put(chave, valor);
		}
		return this;

	}
	public Map<String, Object> build() {
		return queryParams;
	}

}

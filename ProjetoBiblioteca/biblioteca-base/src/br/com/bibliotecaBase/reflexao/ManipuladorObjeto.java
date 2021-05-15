package br.com.bibliotecaBase.reflexao;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Stream;

public class ManipuladorObjeto {

	private Object instancia;

	public ManipuladorObjeto(Object instancia) {
		this.instancia = instancia;
	}

	public ManipuladorMetodo getMetodo(String nomeMetodo, Map<String, Object> parametros) {
		Stream<Method> metodos = Stream.of(instancia.getClass().getDeclaredMethods());
		Method metodoSelecionado = metodos
				.filter(metodo -> metodo.getName().equals(nomeMetodo)
						&& metodo.getParameterCount() == parametros.values().size()
						&& Stream.of(metodo.getParameters())
								.allMatch(param -> parametros.keySet().contains(param.getName())
										&& parametros.get(param.getName()).getClass().equals(param.getType())))
				.findFirst().orElseThrow(() -> new RuntimeException(" Erro Metodo manipulaMetodo "));
		return new ManipuladorMetodo(metodoSelecionado, instancia, parametros);
	}

}

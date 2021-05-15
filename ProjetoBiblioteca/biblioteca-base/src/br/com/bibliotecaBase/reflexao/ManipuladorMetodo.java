package br.com.bibliotecaBase.reflexao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;


public class ManipuladorMetodo {

	private Object instancia;
	private Method metodo;
	private Map<String, Object> parametros;
	private BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao;

	public ManipuladorMetodo(Method metodo, Object instancia, Map<String, Object> parametros) {
		this.metodo = metodo;
		this.instancia = instancia;
		this.parametros = parametros;
	}

	public ManipuladorMetodo comTratamentoDeExcecao(
			BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao) {
		this.tratamentoExcecao = tratamentoExcecao;
		return this;
	}

	public Object invoca() {
		try {
			List<Object> parametro = new ArrayList<>();
			Stream.of(metodo.getParameters()).forEach(p -> parametro.add(parametros.get(p.getName())));
			return metodo.invoke(instancia, parametro.toArray());
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {

			if (tratamentoExcecao != null) {
				return tratamentoExcecao.apply(metodo, e);
			}
			e.printStackTrace();
			return new RuntimeException(" Erro no metodo invoca da classe ManipuladorMetodo " + e.getTargetException());
		}
	}

}

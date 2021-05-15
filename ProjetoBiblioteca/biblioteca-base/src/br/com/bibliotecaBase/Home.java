package br.com.bibliotecaBase;

import java.util.Map;

import br.com.bibliotecaBase.IoC.ContainerIOC;
import br.com.bibliotecaBase.conversor.ConversorXML;
import br.com.bibliotecaBase.protocolo.Request;
import br.com.bibliotecaBase.reflexao.ManipuladorObjeto;
import br.com.bibliotecaBase.reflexao.Reflexao;

public class Home {
	private String pacotebase;
	private ContainerIOC container;
	
	public Home(String pacotebase) {
		this.pacotebase = pacotebase;
		this.container = new ContainerIOC();
	}

	public Object executa(String url) {

		Request request = new Request(url);
		String nomeControle = request.getNomeControle();
		String nomeMetodo = request.getNomeMetodo();
		Map<String, Object> parametros = request.getQueryParams();
		
		Class<?> classeControle = new Reflexao().getClasse(pacotebase + nomeControle);
		Object instanciaControle = container.getInstancia(classeControle);
		
		 Object resultado = new ManipuladorObjeto(instanciaControle)
		.getMetodo(nomeMetodo, parametros)
		.comTratamentoDeExcecao((metodo, ex) -> {
		System.out.println(" Erro no metodo com tratamento "
		+ metodo.getName() + " a classe " + metodo.getDeclaringClass().getName() + "\r\n");
			throw new RuntimeException(" Erro no metodo !");
		})
		.invoca();
		 
		resultado = new ConversorXML().converte(resultado);
		return resultado;
	}

	public <T, K extends T> void registra(Class<T> tipoFonte, Class<K> tipoDestino) {
		container.registra(tipoFonte, tipoDestino);
	}

}

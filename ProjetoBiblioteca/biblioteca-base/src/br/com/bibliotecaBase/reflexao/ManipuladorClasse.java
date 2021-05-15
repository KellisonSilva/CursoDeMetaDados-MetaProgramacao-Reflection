package br.com.bibliotecaBase.reflexao;

import java.lang.reflect.Constructor;

public class ManipuladorClasse {

	private Class<?> classe;

	public ManipuladorClasse(Class<?> classe) {
		this.classe = classe;
	}

	public ManipuladorConstrutor getConstrutorPadrao() {
		try {
			Constructor<?> construtorPadrao = classe.getDeclaredConstructor();
			construtorPadrao.setAccessible(true);
			return new ManipuladorConstrutor(construtorPadrao);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public ManipuladorObjeto criaInstancia() {
		Object instancia = getConstrutorPadrao().invoca();
		return new ManipuladorObjeto(instancia);
	}
}

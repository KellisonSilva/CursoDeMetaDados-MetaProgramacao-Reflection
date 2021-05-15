package br.com.bibliotecaBase.reflexao;

public class Reflexao {
	public ManipuladorClasse refleteClasse(String fqn) {
		Class<?> instancia = getClasse(fqn);
		return new ManipuladorClasse(instancia);
	}

	public Class<?> getClasse(String fqn) {
		try {
			Class<?> classe = Class.forName(fqn);
			return classe;
		} catch (IllegalArgumentException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}

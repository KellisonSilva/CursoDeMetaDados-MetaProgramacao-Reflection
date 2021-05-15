package br.com.biblioteca.dao;

import java.util.Arrays;
import java.util.List;

import br.com.biblioteca.modelo.Livro;

public class LivroDaoMock implements LivroDao {

	private static List<Livro> Lista_livros = Arrays.asList(new Livro("A vida intelectual", "Filosofia", 23.46),
			new Livro("A Primeira chave", "Comportamental", 21.10),
			new Livro("Introduçao a vida intelectual", "Resenha Filosofica", 32.59),
			new Livro("Ensaio a liberdade", "Filosofia", 41.78));

	@Override
	public List<Livro> lista() {
		return Lista_livros;
	}

	@Override
	public Livro getLIVRO(int id) {
		return Lista_livros.get(id);
	}

}

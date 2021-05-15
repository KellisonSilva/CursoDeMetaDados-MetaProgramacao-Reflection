package br.com.biblioteca.estoque.controle;

import java.util.List;
import java.util.stream.Collectors;

import br.com.biblioteca.dao.LivroDao;
import br.com.biblioteca.modelo.Livro;

public class LivroController {

	private LivroDao livroDao;

	public LivroController(LivroDao livroDao) {
		this.livroDao = livroDao;
	}
	public List<Livro> lista() {
		return livroDao.lista();
	}

	public List<Livro> filtra(String nome) {
		return livroDao.lista().stream().filter(book -> book.getNome().toLowerCase().startsWith(nome.toLowerCase()))
				.collect(Collectors.toList());
	}

	public List<Livro> filtra(String nome, String tipo) {
		return livroDao.lista().stream().filter(book -> book.getNome().toLowerCase().startsWith(nome.toLowerCase())
				&& book.getTipo().equalsIgnoreCase(tipo)).collect(Collectors.toList());
	}

}

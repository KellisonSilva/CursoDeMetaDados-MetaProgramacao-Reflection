package br.com.biblioteca.dao;

import java.util.List;

import br.com.biblioteca.modelo.Livro;

public interface LivroDao {

	List<Livro> lista();
	Livro getLIVRO(int id);
}

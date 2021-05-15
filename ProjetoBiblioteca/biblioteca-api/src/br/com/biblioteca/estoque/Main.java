package br.com.biblioteca.estoque;

import java.util.Scanner;
import br.com.biblioteca.dao.LivroDao;
import br.com.biblioteca.dao.LivroDaoMock;
import br.com.bibliotecaBase.Home;

public class Main {

	public static void main(String[] args) {

		try (Scanner s = new Scanner(System.in)) {
		
			String url = s.nextLine();
			Home home = new Home("br.com.biblioteca.estoque.controle.");
			home.registra(LivroDao.class, LivroDaoMock.class);
			
			while(!url.equals("exit")) {
				Object response = home.executa(url);
				System.out.println(" Resultado " + response);
				url = s.nextLine();
			}
		}
	}

}

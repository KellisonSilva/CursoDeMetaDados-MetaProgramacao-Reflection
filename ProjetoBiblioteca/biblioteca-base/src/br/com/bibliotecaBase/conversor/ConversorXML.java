package br.com.bibliotecaBase.conversor;

import java.lang.reflect.Field;
import java.util.Collection;

import br.com.bibliotecaBase.anotacao.NomeTagXml;

public class ConversorXML {

	public String converte(Object obj) {
		try {
			Class<?> classeObjeto = obj.getClass();
			StringBuilder xmlBuilder = new StringBuilder();
			if (obj instanceof Collection) {
				Collection<?> colecao = (Collection<?>) obj;
				xmlBuilder.append("<lista>");
				for (Object o : colecao) {
					String xml = converte(o);
					xmlBuilder.append(xml);
				}
				xmlBuilder.append("</lista>");
				
			} else {
				NomeTagXml anotacaoClasse = classeObjeto.getDeclaredAnnotation(NomeTagXml.class);
				String nomeClasse = anotacaoClasse == null ? classeObjeto.getName() : anotacaoClasse.value();
				xmlBuilder.append("<"+nomeClasse+">");
				for (Field atributo : classeObjeto.getDeclaredFields()) {
					atributo.setAccessible(true);
					NomeTagXml anotacaoAtributo = atributo.getDeclaredAnnotation(NomeTagXml.class);
					String nomeAtributo = anotacaoAtributo  == null ? atributo.getName() : anotacaoAtributo.value();
					Object valorAtributo = atributo.get(obj);
					xmlBuilder.append("<"+nomeAtributo+">");
					xmlBuilder.append(valorAtributo);
					xmlBuilder.append("</"+nomeAtributo+">");
				}
			}

			return xmlBuilder.toString();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao gerar XML");
		}
	}

}

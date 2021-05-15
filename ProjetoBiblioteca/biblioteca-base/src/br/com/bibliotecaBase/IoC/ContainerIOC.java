package br.com.bibliotecaBase.IoC;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ContainerIOC {

	private Map<Class<?>, Class<?>> MapaDeTipos = new HashMap<>();
	
	public Object getInstancia(Class<?> tipoFonte) {
		
		Class<?> tipoDestino = MapaDeTipos.get(tipoFonte);
		
		if(tipoDestino != null) {
			return getInstancia(tipoDestino);
		}
		Constructor<?>[] construtorPadrao = tipoFonte.getDeclaredConstructors();
		Optional<Constructor<?>> classeConstrutor = Stream.of(construtorPadrao)
				.filter(construtor -> construtor.getParameterCount() == 0).findFirst();
		try {
			if (classeConstrutor.isPresent()) {
				Object instancia = classeConstrutor.get().newInstance();
				return instancia;
			} else {
				Constructor<?> construtor = tipoFonte.getDeclaredConstructors()[0];
				List<Object> parametros = new ArrayList<>();
				for(Parameter param: construtor.getParameters()) {
					Class<?> tipoObjeto = param.getType();
					parametros.add(getInstancia(tipoObjeto));
				}
				return construtor.newInstance(parametros.toArray());
			}

		} catch (InstantiationException
				|IllegalAccessException
				|IllegalArgumentException
				|InvocationTargetException e) {
			throw new RuntimeException(e);
		}

	}

	public <T, K extends T> void registra(Class<T> tipoFonte, Class<K> tipoDestino) {
		MapaDeTipos.put(tipoFonte, tipoDestino);
		
	}

}

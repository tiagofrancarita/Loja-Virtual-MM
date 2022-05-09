package br.com.manomultimarcas.util;

import br.com.manomultimarcas.model.Logger;

//|
public class LoggerUtils {
	
	private static String getMessage(Logger message) {
		
		return String.format("Titulo: %s, Descrição: %s", message.getTitulo(), message.getDescricao() );
	}
}
package br.com.manomultimarcas.util;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.manomultimarcas.model.dto.ObjetoErroDto;


@RestControllerAdvice
@ControllerAdvice
public class ControleExcecoes extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ExceptionLojaVirtual.class})
	public ResponseEntity<Object> handleExceptionCustom(ExceptionLojaVirtual exLojaVirtual){
		
		ObjetoErroDto objetoErroDto = new ObjetoErroDto();
		
		objetoErroDto.setError(exLojaVirtual.getMessage());
		objetoErroDto.setError(HttpStatus.OK.toString());
		
		return new ResponseEntity<Object>(objetoErroDto, HttpStatus.OK);
	}
	
	/* Captura excecoes do projeto.*/
	@ExceptionHandler({Exception.class, RuntimeException.class,Throwable.class})
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ObjetoErroDto objetoErroDTO = new ObjetoErroDto();
		
		String mensagemErro = "";
		
		if (ex instanceof MethodArgumentNotValidException) {
			
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			
			for (ObjectError ObjectError : list) {
				mensagemErro += ObjectError.getDefaultMessage() + "\n";
				
			}
			
		}else if (ex instanceof HttpMessageNotReadableException) {
			
			mensagemErro = "O Corpo da requisição está vazio.";
			
		}
		
		else {
			mensagemErro = ex.getMessage();
		}
		
		objetoErroDTO.setError(mensagemErro);
		objetoErroDTO.setCode(status.value() + " ==> " + status.getReasonPhrase());
		
		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, 
										SQLException.class})
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex){
	
		ObjetoErroDto objetoErroDTO = new ObjetoErroDto();
		String mensagemErro = "";
		
		if (ex instanceof DataIntegrityViolationException) {
			mensagemErro = "Erro de integridade no banco SQL: "  + ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
		}else {
			mensagemErro = ex.getMessage();
		}
		
		if (ex instanceof ConstraintViolationException) {
			mensagemErro = "Erro de chave estrangeira SQL: "  + ((ConstraintViolationException) ex).getCause().getCause().getMessage();
		}else {
			mensagemErro = ex.getMessage();
		}
		
		if (ex instanceof SQLException) {
			mensagemErro = "Erro de sintaxe SQL: "  + ((SQLException) ex).getCause().getCause().getMessage();
		}else {
			mensagemErro = ex.getMessage();
		}
		
		objetoErroDTO.setError(mensagemErro);
		objetoErroDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		
		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
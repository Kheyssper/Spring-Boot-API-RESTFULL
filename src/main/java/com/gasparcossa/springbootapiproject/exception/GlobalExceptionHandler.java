package com.gasparcossa.springbootapiproject.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe que lida e customiza com as excessoes da API REST
 */
@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Vai customizar a exceção que e lançada quando um argumento anotado com @Valid falha na validação
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    Map<String, List<String>> errorMap = new HashMap<>();
    List<String> erros = ex.getBindingResult().getFieldErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
    errorMap.put("Erros: ", erros);
    return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
  }

  /**
   * Customiza o resultado da excecao das violacoes de restricao:
   * 
   * @param ex Objecto da classe ConstraintViolationException que origina a
   *           excessao
   * @return ResponseEntity com o erro e o status da excessao customizada
   **/
  @ExceptionHandler({ ConstraintViolationException.class })
  public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
    List<String> errors = new ArrayList<String>();
    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      errors.add(violation.getMessage());
    }
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  /**
   * Customiza a mensagem de exceção quando um argumento de método não é o tipo esperado
   * 
   * @param ex Objecto da classe que origina a excessao
   * @return ResponseEntity com o erro, header e o status dessa excessao
   **/
  @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException ex) {
    List<String> errors = new ArrayList<String>();

    errors.add(ex.getName().substring(0, 1).toUpperCase() + ex.getName().substring(1) + " deveria ser do tipo "
        + ex.getRequiredType().getName());

    return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
  }
  
  /**
  * Vai customizar todas as excessoes alem das outras especializadas por mim
  */
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object
  body,
  HttpHeaders headers, HttpStatus status, WebRequest request) {
  List<String> erros = new ArrayList<String>();
  erros.add("A pagina nao existe pois: " + ex.getMessage());

  return new ResponseEntity<Object>(erros, status);
  }

  /**
   * Customiza a informacao quando o corpo da solicitacao e invalido
   */
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    Map<String, List<String>> errorMap = new HashMap<>();
    List<String> erros = new ArrayList<String>();
    erros.add("A requisicao nao foi aceite por atribuir um valor errado a um campo, pois: " + ex.getMessage());
    errorMap.put("Erros: ", erros);
    return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
  }


  /**
   * Customiza  o resultado de uma excessao quando uma request tenta usar um metodo HTTP que nao lhe pertence
   */
  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
    HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
      Map<String, List<String>> errorMap = new HashMap<>();
      List<String> erros = new ArrayList<String>();
      erros.add("Essa request nao e suportado pelo metodo :" + ex.getMethod());
      errorMap.put("Erro: ", erros);
      return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }


}

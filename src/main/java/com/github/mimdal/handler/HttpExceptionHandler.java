package com.github.mimdal.handler;

import java.util.ArrayList;
import java.util.List;

import com.github.mimdal.api.web.common.response.ErrorMessageListResponse;
import com.github.mimdal.exception.BusinessException;
import com.github.mimdal.exception.ServiceRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RequiredArgsConstructor
@Slf4j
@ControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ BusinessException.class })
	public final ResponseEntity<String> handleBusinessException(BusinessException ex) {
		logger.error("general exception", ex);
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error happens: " + ex.getClass().getSimpleName() +
				" - error message: " + ex.getLocalizedMessage());
	}

	@ExceptionHandler(ServiceRuntimeException.class)
	public ResponseEntity<String> handleBusinessRuntimeException(ServiceRuntimeException ex) {
		logger.error("business (runtime) exception", ex);
		return ResponseEntity.unprocessableEntity().body("Service Runtime Exception, error message: " + ex.getLocalizedMessage());
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		logger.error(" !!!!!!!!!!!!! Exception: ", ex);
		return new ResponseEntity("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("method argument not valid exception " + ex.getMessage());
		logger.error("validation exception", ex);
		return ResponseEntity.badRequest().body(createMessageListResponse(ex));
	}

	private ErrorMessageListResponse createMessageListResponse(MethodArgumentNotValidException ex) {
		ErrorMessageListResponse messageListResponse = new ErrorMessageListResponse();
		messageListResponse.setMessage("!!! Validation Exception !!!");
		messageListResponse.setErrorMessages(getCustomMessage(ex));
		return messageListResponse;
	}

	private List<Pair<?, ?>> getCustomMessage(MethodArgumentNotValidException ex) {
		List<Pair<?, ?>> messages = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			messages.add(Pair.of(((FieldError) error).getField(), ((FieldError) error).getRejectedValue()));
		}
		return messages;
	}
}

package bookinghospital.common_module.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	//Bad Request
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	//@Valid 실패 시 발생
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException e) {
		StringBuilder errorMessage = new StringBuilder();

		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errorMessage.append(fieldName).append(":").append(message).append("\n");
		});

/*        CommonResponse errorResponse = CommonResponse.builder()
                .message(errorMessage.toString())
                .data(null)
                .build();*/

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
	}

	//지원 안하는 메소드 호출할 경우 발생
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleOwnershipMismatch(HttpRequestMethodNotSupportedException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
	}

	//500 에러
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
}

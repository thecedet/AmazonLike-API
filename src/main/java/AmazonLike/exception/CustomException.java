package AmazonLike.exception;

import org.springframework.http.HttpStatus;

import AmazonLike.payload.ErrorResponse;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    
    private final String message;
    private final HttpStatus httpStatus;
    private final ErrorResponse errorResponse;

    public CustomException(String message, HttpStatus httpStatus) {
      this.message = message;
      this.httpStatus = httpStatus;
      this.errorResponse = new ErrorResponse("error", message, httpStatus.value(), httpStatus.getReasonPhrase());

    }

}

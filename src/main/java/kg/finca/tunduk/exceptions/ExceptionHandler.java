package kg.finca.tunduk.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<?> handleException(RuntimeException e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return new ResponseEntity("system error", HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<?> handleException(OkHttpConnectionException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<?> handleException(JsonWriteException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<?> handleException(JsonReadException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
    }
}

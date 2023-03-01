package kg.finca.tunduk.models.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionResponse {
    int status;
    String message;
}

package kg.finca.tunduk.models.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Request {
    String apiKey;
    String pin;
    String otp;
}

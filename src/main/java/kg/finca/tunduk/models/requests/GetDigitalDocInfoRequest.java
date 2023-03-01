package kg.finca.tunduk.models.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetDigitalDocInfoRequest {
    String pin;
    String otp;
}

package kg.finca.tunduk.models.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetDigitalDocInfoResponse {
    int status;
    String message;
    Response response;
}

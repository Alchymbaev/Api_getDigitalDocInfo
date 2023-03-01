package kg.finca.tunduk.models.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Response {
    @NotNull
    String document;
}

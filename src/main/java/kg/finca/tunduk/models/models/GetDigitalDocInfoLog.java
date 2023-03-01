package kg.finca.tunduk.models.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class GetDigitalDocInfoLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date request_date;
    String request;
    String response;

    @PrePersist
    protected void onCreate(){
        request_date = new Date();
    }
}

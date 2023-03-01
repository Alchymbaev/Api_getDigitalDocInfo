package kg.finca.tunduk.services;

import kg.finca.tunduk.models.requests.GetDigitalDocInfoRequest;
import org.springframework.http.ResponseEntity;

public interface GetDigitalDocInfoService {
    ResponseEntity<?> getDigitalDocInfo(GetDigitalDocInfoRequest request);
}

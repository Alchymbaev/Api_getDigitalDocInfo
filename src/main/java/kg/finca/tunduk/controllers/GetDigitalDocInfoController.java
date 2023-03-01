package kg.finca.tunduk.controllers;

import kg.finca.tunduk.models.requests.GetDigitalDocInfoRequest;
import kg.finca.tunduk.services.GetDigitalDocInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/tunduk")
public class GetDigitalDocInfoController {

    private final GetDigitalDocInfoService service;

    public GetDigitalDocInfoController(GetDigitalDocInfoService service) {
        this.service = service;
    }

    @PostMapping("/getDigitalDocInfo")
    ResponseEntity<?> getDigitalDocInfo(@RequestBody GetDigitalDocInfoRequest request) {
        return service.getDigitalDocInfo(request);
    }
}

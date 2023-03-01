package kg.finca.tunduk.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kg.finca.tunduk.dao.GetDigitalDocInfoRep;
import kg.finca.tunduk.exceptions.JsonReadException;
import kg.finca.tunduk.exceptions.JsonWriteException;
import kg.finca.tunduk.microservice.OkHttpConnection;
import kg.finca.tunduk.models.models.GetDigitalDocInfoLog;
import kg.finca.tunduk.models.requests.GetDigitalDocInfoRequest;
import kg.finca.tunduk.models.requests.Request;
import kg.finca.tunduk.models.responses.ExceptionResponse;
import kg.finca.tunduk.models.responses.ExceptionWithResponse;
import kg.finca.tunduk.models.responses.GetDigitalDocInfoResponse;
import kg.finca.tunduk.services.GetDigitalDocInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetDigitalDocInfoServiceImpl implements GetDigitalDocInfoService {
    private static final String url = "http://X.X.X.X/r1/central-server/GOV/70000011/state-services-portal/getDigitalDocInfoByQrPdf";
    private static final String apiKey = "123asfbcx35u%34u55TgdyHf23WRWE234yrrfbdfsnbr25346";
    ObjectMapper objectMapper = new ObjectMapper();

    private final GetDigitalDocInfoRep rep;

    public GetDigitalDocInfoServiceImpl(GetDigitalDocInfoRep rep) {
        this.rep = rep;
    }

    @Override
    public ResponseEntity<?> getDigitalDocInfo(GetDigitalDocInfoRequest r) {
        log.info("===================================================================================================");
        log.info("RequestBody: pin = {}, otp = {}", r.getPin(), r.getOtp());
        OkHttpConnection connection = new OkHttpConnection();
        Request request = new Request(apiKey, r.getPin(), r.getOtp());
        String jsonRequest = getJsonFromObject(request);
        log.info("Json Request: {}", jsonRequest);
        String jsonResponse = connection.post(url, jsonRequest);
        log.info("Json Response: {}", jsonResponse);
        try {
            return new ResponseEntity<>(getObjectFromJson(jsonResponse, ExceptionResponse.class), HttpStatus.OK);
        } catch (Exception e) {
            try {
                return new ResponseEntity<>(getObjectFromJson(jsonResponse, ExceptionWithResponse.class), HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>(getObjectFromJson(jsonResponse, GetDigitalDocInfoResponse.class), HttpStatus.OK);
            }
        } finally {
            log.info("Write log in Database");
            saveLog("{ pin: " + r.getPin() + ", otp: " + r.getOtp() + " }", jsonResponse);
        }
    }

    private String getJsonFromObject(Object o){
        try {
            log.info("Get Json from Object: {}", o.getClass().getName());
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.warn("Json write error");
            throw new JsonWriteException("json write error");
        }
    }


    private Object getObjectFromJson(String json, Class<?> classType) {
        try {
            log.info("Get Object: {} from Json", classType.getName());
            return objectMapper.readValue(json, classType);
        } catch (JsonProcessingException e) {
            log.warn("Can't parse json to Object: {}", classType.getName());
            throw new JsonReadException("json read error");
        }
    }

    private void saveLog(String request, String response){
        GetDigitalDocInfoLog log = new GetDigitalDocInfoLog();
        log.setRequest(request);
        log.setResponse(response);
        rep.save(log);
    }

}

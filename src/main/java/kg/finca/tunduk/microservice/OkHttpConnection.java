package kg.finca.tunduk.microservice;

import kg.finca.tunduk.exceptions.OkHttpConnectionException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Slf4j
@Service
public class OkHttpConnection {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public String post(String url, String json){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-Road-Client", "central-server/XXX/52147123/abs")
                .post(requestBody)
                .build();
        log.info("Connection: {}", request.toString());
        Call call = client.newCall(request);
        log.info("Call: {}", call.toString());
        try {
            Response response = call.execute();
            log.info("Connection success");
            return response.body().string();
        } catch (IOException e) {
            log.warn("Connection error");
            throw new OkHttpConnectionException("connection error");
        }
    }
}

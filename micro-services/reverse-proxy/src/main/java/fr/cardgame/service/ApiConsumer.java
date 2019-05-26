package fr.cardgame.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ApiConsumer {

    /**
     * Consume an API
     *
     * @param url
     * @param parameters
     * @param classType
     * @param <T>
     *
     * @return
     */
    public <T> ResponseEntity consume(String url, Map<String, Object> parameters, Class<T> classType) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            T response = restTemplate.postForObject(url, parameters, classType);
            return new ResponseEntity<T>(response, HttpStatus.OK);
        } catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
            return new ResponseEntity(httpClientOrServerExc.getStatusCode());
        }

    }
}

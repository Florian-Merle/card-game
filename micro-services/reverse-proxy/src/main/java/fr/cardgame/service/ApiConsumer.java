package fr.cardgame.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiConsumer {

    /**
     * Consume an API
     *
     * @param url
     * @param classType
     * @param <T>
     *
     * @return
     */
    public <T, U> ResponseEntity consume(HttpMethod method, String url, U requestBody, Class<T> classType) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(requestBody, headers);

        try {
            return restTemplate.exchange(url, method, request, classType);
        } catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
            return new ResponseEntity(httpClientOrServerExc.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

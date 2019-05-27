package fr.cardgame.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
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
     * @param classType
     * @param <T>
     *
     * @return
     */
    public <T, U> ResponseEntity consume(RequestMethod method, String url, U requestBody, Class<T> classType) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<T> response = restTemplate.exchange(url, this.getHttpMethod(method), request, classType);
            return response;
        } catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
            return new ResponseEntity(httpClientOrServerExc.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Transform a request method into a httpMethod
     *
     * @param method
     * @return
     * @throws Exception
     */
    private HttpMethod getHttpMethod(RequestMethod method) throws Exception {
        switch (method) {
            case POST:
                return HttpMethod.POST;
            case GET:
                return HttpMethod.GET;
            case DELETE:
                return HttpMethod.DELETE;
            case HEAD:
                return HttpMethod.HEAD;
            case OPTIONS:
                return HttpMethod.OPTIONS;
            case PATCH:
                return HttpMethod.PATCH;
            case PUT:
                return HttpMethod.PUT;
            case TRACE:
                return HttpMethod.TRACE;
        }

        throw new Exception("Method " + method.name() + " unknown");
    }
}

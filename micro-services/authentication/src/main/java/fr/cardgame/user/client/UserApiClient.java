package fr.cardgame.user.client;

import fr.cardgame.user.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Api client
 * Used to consume data from user micro-service api
 */
@Service
public class UserApiClient {

    private static String API_URL = "http://localhost:8080";

    // URI(s)
    private static String GET_USER_BY_EMAIL_URI = "/getUserByEmail";
    private static String GET_USER_BY_ID_URI    = "/getUserById";

    /**
     * Consume user micro-service. Try to get a user by its email.
     *
     * @param email
     * @return
     */
    public UserDto getUserByEmail(String email) {
        // construct api url
        String url = UserApiClient.API_URL + UserApiClient.GET_USER_BY_EMAIL_URI;
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);

        // consume api
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, params, UserDto.class);
    }

    public UserDto getUserById(int id) {
        // construct api url
        String url = UserApiClient.API_URL + UserApiClient.GET_USER_BY_ID_URI;
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", Integer.toString(id));

        // consume api
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, params, UserDto.class);
    }
}

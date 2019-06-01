package fr.cardgame.user.client;

import fr.cardgame.user.dto.UpdateUserCashDto;
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

    private static String API_URL = "http://localhost:8082";

    // URI(s)
    private static String UPDATE_USER_CASH = "/updateCash";

    /**
     * Consume user micro-service. Try to get a user by its email.
     *
     * @param updateUserCashDto
     * @return
     */
    public boolean updateUserCash(UpdateUserCashDto updateUserCashDto) {
        // construct api url
    	boolean ret = true;
        String url = UserApiClient.API_URL + UserApiClient.UPDATE_USER_CASH;
        Map<String, Object> params = new HashMap<>();
        params.put("id", updateUserCashDto.getId());
        params.put("cash", updateUserCashDto.getCash());

        
        // consume api
        try
        {
        	RestTemplate restTemplate = new RestTemplate();
        	restTemplate.postForObject(url, params, UpdateUserCashDto.class);
        }
        catch(Exception e)
        {
        	ret = false;
        }
        return ret;
    }

}

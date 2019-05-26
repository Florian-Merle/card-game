package fr.cardgame.dto;

import fr.cardgame.service.DtoToMap;

import java.util.HashMap;
import java.util.Map;

abstract public class AuthenticatedGenericDto extends GenericDto {

    private String token;

    public AuthenticatedGenericDto() {
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, Object> getTokenMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", this.getToken());
        return map;
    }
}

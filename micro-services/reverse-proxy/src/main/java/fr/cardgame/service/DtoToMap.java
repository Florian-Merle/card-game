package fr.cardgame.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cardgame.dto.GenericDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DtoToMap {

    /**
     * Transform a GenericDto into a map
     *
     * @param genericDto
     * @return
     */
    public static Map<String, Object> convert(GenericDto genericDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(genericDto, Map.class);
    }
}

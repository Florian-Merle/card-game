package fr.cardgame.dto;

import fr.cardgame.service.DtoToMap;

import java.util.Map;

abstract public class GenericDto {

    /**
     * Return a map representing this dto
     *
     * @return
     */
    public Map<String, Object> toMap() {
        return DtoToMap.convert(this);
    }
}

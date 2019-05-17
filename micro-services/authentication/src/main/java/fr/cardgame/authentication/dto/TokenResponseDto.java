package fr.cardgame.authentication.dto;

/**
 * Token given to the user
 */
public class TokenResponseDto {
    private String userId;

    public TokenResponseDto(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

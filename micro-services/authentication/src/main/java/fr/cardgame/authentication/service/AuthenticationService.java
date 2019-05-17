package fr.cardgame.authentication.service;

import fr.cardgame.authentication.dto.TokenRequestDto;
import fr.cardgame.authentication.dto.TokenResponseDto;
import fr.cardgame.user.client.UserApiClient;
import fr.cardgame.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	@Autowired
	private UserApiClient userApiClient;

	public TokenResponseDto getAuthToken(TokenRequestDto tokenRequestDto) {
		UserDto userFromApi = this.userApiClient.getUserByEmail(tokenRequestDto.getEmail());

		// no user found
		if (null == userFromApi) {
			return null;
		}

		// wrong password
		if (!userFromApi.getPassword().equals(tokenRequestDto.getPassword())) {
			return null;
		}

		return new TokenResponseDto(
				Integer.toString(userFromApi.getId())
		);
	}
}

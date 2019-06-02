package fr.cardgame.authentication.service;

import fr.cardgame.authentication.dto.CredentialsDto;
import fr.cardgame.authentication.dto.TokenDto;
import fr.cardgame.user.client.UserApiClient;
import fr.cardgame.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	@Autowired
	private UserApiClient userApiClient;

	/**
	 * Get auth token
	 *
	 * @param credentialsDto
	 * @return
	 */
	public TokenDto getAuthToken(CredentialsDto credentialsDto) {
		UserDto userFromApi = this.userApiClient.getUserByEmail(credentialsDto.getEmail());

		// no user found
		if (null == userFromApi) {
			return null;
		}

		// wrong password
		if (!userFromApi.getPassword().equals(credentialsDto.getPassword())) {
			return null;
		}

		return new TokenDto(
				Integer.toString(userFromApi.getId())
		);
	}

	/**
	 * Return user if token is valid
	 *
	 * @param tokenDto
	 * @return
	 */
	public UserDto getUser(TokenDto tokenDto) {
		// FIXME there should be security here by checking the validity of the token
		int id = Integer.parseInt(tokenDto.getToken());

		return this.userApiClient.getUserById(id);
	}
}

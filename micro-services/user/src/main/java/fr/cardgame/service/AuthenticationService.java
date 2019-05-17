package fr.cardgame.service;

import fr.cardgame.user.dto.UserRegisterInDTO;
import fr.cardgame.user.repository.UserRepository;
import fr.cardgame.user.dto.UserLoginDTO;
import fr.cardgame.user.factory.UserFactory;
import fr.cardgame.user.validator.UserSignInDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cardgame.user.model.User;

import javax.servlet.http.HttpSession;

@Service
public class AuthenticationService {

	public static final String USER_KEY = "user";

	private final HttpSession session;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserFactory userFactory;

	/**
	 * Using dependency injection to get the session
	 *
	 * @param session
	 */
	public AuthenticationService(HttpSession session) {
		this.session = session;
	}

	/**
	 * Method used to register a user
	 *
	 * @param userRegisterInDTO
	 * @return
	 */
	public boolean register(UserRegisterInDTO userRegisterInDTO) {
		UserSignInDTOValidator validator = new UserSignInDTOValidator();

		// incorrect submitted data
		if (!validator.validate(userRegisterInDTO)) {
			return false;
		}

		// user already exists
		User potentialUser = userRepository.findByEmail(userRegisterInDTO.getEmail());
		if (null != potentialUser) {
			return false;
		}

		// create the user and save it
		User user = this.userFactory.createUser(userRegisterInDTO);

		userRepository.save(user);

		return true;
	}

	/**
	 * Log a user in. If the user could be connected, its object will be saved in the session
	 *
	 * @param userLoginDTO
	 * @return
	 */
	public boolean login(UserLoginDTO userLoginDTO) {
		User user = userRepository.findByEmail(userLoginDTO.getEmail());

		// user not found for this name
		if (null == user) {
			return false;
		}

		// wrong password
		if (!userLoginDTO.getPassword().equals(user.getPassword())) {
			return false;
		}

		// save user in session
		this.session.setAttribute(
			AuthenticationService.USER_KEY,
			user
		);

		return true;
	}


	/**
	 * Try to logout a user
	 *
	 * @return if a user was logged in true, false otherwise
	 */
	public boolean logout() {
		if (null == this.session.getAttribute(AuthenticationService.USER_KEY)) {
			return false;
		}

		this.session.removeAttribute(AuthenticationService.USER_KEY);
		return true;
	}

	/**
	 * Return the logged in user
	 *
	 * @return
	 */
	public User getLoggedInUser() {
		return (User) this.session.getAttribute(AuthenticationService.USER_KEY);
	}
}

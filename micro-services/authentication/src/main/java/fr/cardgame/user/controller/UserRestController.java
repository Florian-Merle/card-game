package fr.cardgame.user.controller;


import fr.cardgame.service.AuthenticationService;
import fr.cardgame.user.dto.UserLoginDTO;
import fr.cardgame.user.dto.UserRegisterInDTO;
import fr.cardgame.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserRestController {

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	private ResponseEntity<User> getUser() {
		User user = authenticationService.getLoggedInUser();
		return new ResponseEntity<User>(
				user,
				null != user ? HttpStatus.OK : HttpStatus.UNAUTHORIZED
		);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	private ResponseEntity register(@RequestBody UserRegisterInDTO userLoginDTO) {
		return new ResponseEntity(
				authenticationService.register(userLoginDTO) ? HttpStatus.OK : HttpStatus.BAD_REQUEST
		);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	private ResponseEntity login(@RequestBody UserLoginDTO userLoginDTO, HttpSession session) {
		return new ResponseEntity(
				authenticationService.login(userLoginDTO) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED
		);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/logout")
	private ResponseEntity logout() {
		return new ResponseEntity(
				authenticationService.logout() ? HttpStatus.OK : HttpStatus.BAD_REQUEST
		);
	}
}

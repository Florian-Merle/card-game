package fr.cardgame.user.controller;


import fr.cardgame.user.dto.GetByEmailDto;
import fr.cardgame.user.model.User;
import fr.cardgame.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/getUserByEmail")
	private ResponseEntity<User> getUser(@RequestBody GetByEmailDto getByEmailDto) {
		User user = this.userService.getUserByEmail(getByEmailDto.getEmail());

		if (null == user) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}

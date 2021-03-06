package fr.cardgame.user.controller;

import fr.cardgame.user.dto.GetByEmailDto;
import fr.cardgame.user.dto.GetByIdDto;
import fr.cardgame.user.dto.UpdateUserCashDto;
import fr.cardgame.user.dto.UserRegisterDto;
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
    public ResponseEntity<User> getUserByEmail(@RequestBody GetByEmailDto getByEmailDto) {
        System.out.println("user: /getUserByEmail");

        User user = this.userService.getUserByEmail(getByEmailDto.getEmail());

        if (null == user) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getUserById")
    public ResponseEntity<User> getUserById(@RequestBody GetByIdDto getByIdDto) {
        System.out.println("user: /getUserById");

        User user = this.userService.getUserById(getByIdDto.getId());

        if (null == user) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateCash")
    public ResponseEntity<User> updateUserCash(@RequestBody UpdateUserCashDto updateUserCashDto) {
        System.out.println("user: /updateCash");

        User user = this.userService.getUserById(updateUserCashDto.getId());
        user.setCash(updateUserCashDto.getCash());

        user = this.userService.update(user);

        if (null == user) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ResponseEntity<User> register(@RequestBody UserRegisterDto userRegisterDto) {
        System.out.println("user: /register");

        User user = this.userService.register(userRegisterDto);

		return new ResponseEntity<User>(
				user,
				null != user ? HttpStatus.OK : HttpStatus.BAD_REQUEST
		);
	}
}

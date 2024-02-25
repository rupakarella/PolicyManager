package com.hexaware.policymanager.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.policymanager.dto.AuthRequest;
import com.hexaware.policymanager.dto.LoginResponse;
import com.hexaware.policymanager.repository.UsersRepository;
import com.hexaware.policymanager.service.JwtService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

	@Autowired
	UsersRepository user;

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	public LoginController(AuthenticationManager authenticationManager, JwtService jwtService)

	{
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;

	}

	@PostMapping("/authenticate")
	public LoginResponse userLogin(@RequestBody AuthRequest authRequest) {
		LoginResponse response = new LoginResponse();
		authenticate(authRequest.getUsername(), authRequest.getPassword());

		String token = jwtService.generateToken(authRequest.getUsername());
		String type = user.findUserTypeByEmailAddress(authRequest.getUsername());
		long userId = user.findUserIdByEmailAddress(authRequest.getUsername());
		String userName = user.findUserNameByEmailAddress(authRequest.getUsername());

		response.setToken(token);
		response.setUserId(userId);
		response.setUserType(type);
		response.setUserName(userName);

		return response;
	}

	private void authenticate(String username, String password) {
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		if (!authenticate.isAuthenticated()) {
			throw new UsernameNotFoundException("Invalid Username or Password / Invalid request");
		}
	}
}
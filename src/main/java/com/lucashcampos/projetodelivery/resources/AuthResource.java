package com.lucashcampos.projetodelivery.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucashcampos.projetodelivery.dto.EmailDTO;
import com.lucashcampos.projetodelivery.security.JWTUtil;
import com.lucashcampos.projetodelivery.security.UserSS;
import com.lucashcampos.projetodelivery.services.AuthService;
import com.lucashcampos.projetodelivery.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthService service;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/isValidToken", method = RequestMethod.POST)
	public ResponseEntity<Boolean> isValidToken(@RequestBody String token) {
		boolean isValid = jwtUtil.tokenIsValid(token);
        return ResponseEntity.ok(isValid);
    }
	
}

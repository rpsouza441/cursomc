package org.rodrigo.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.rodrigo.cursomc.dto.EmailDTO;
import org.rodrigo.cursomc.security.JWTUtil;
import org.rodrigo.cursomc.security.UserSS;
import org.rodrigo.cursomc.services.AuthService;
import org.rodrigo.cursomc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthService service;

	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generationToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/forgot")
	public ResponseEntity<Void> forgot(@RequestBody @Valid EmailDTO emailDTO) {
		service.sendNewPassword(emailDTO.getEmail());
		return ResponseEntity.noContent().build();
	}
}
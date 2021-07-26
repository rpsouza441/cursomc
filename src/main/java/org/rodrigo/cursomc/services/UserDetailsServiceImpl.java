package org.rodrigo.cursomc.services;

import org.rodrigo.cursomc.domain.Cliente;
import org.rodrigo.cursomc.repositories.ClienteRepository;
import org.rodrigo.cursomc.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ClienteRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente c = repo.findByEmail(email);
		if (c == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(c.getId(), c.getEmail(), c.getSenha(), c.getPerfis());
	}

}

package it.contrader.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.contrader.dao.UtenteRepository;
import it.contrader.model.Utente;
import it.contrader.util.stringToBCrypt;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	UtenteRepository utenteRepository;
	
	stringToBCrypt converter;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Utente utente = utenteRepository.findByUsername(username);
		
		if (utente.getUsername().equals(username)) {
			return new User(utente.getUsername(),utente.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	
	}
}
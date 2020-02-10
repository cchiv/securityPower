package it.contrader.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.model.Utente;
import it.contrader.model.Utente.Usertype;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/utente")
	public ResponseEntity<Utente> utenteTest() {
		
		Utente utente = new Utente();
		
		utente.setId(1);
		utente.setUsername("primoutente");
		utente.setPassword("admin");
		utente.setUsertype(Usertype.ADMIN);
		
		return ResponseEntity.ok().body(utente);
	
	}
}

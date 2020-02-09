package it.contrader.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import it.contrader.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long>{
	Utente findByUsernameAndPassword(String username, String password);
	Utente findByUsername(String username);
}

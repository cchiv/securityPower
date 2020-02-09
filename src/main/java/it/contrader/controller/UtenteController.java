package it.contrader.controller;

import it.contrader.dto.UtenteDTO;
import it.contrader.service.UtenteService;
import it.contrader.util.HeaderUtil;
import it.contrader.util.PaginationUtil;
import it.contrader.util.ResponseUtil;
import it.contrader.util.stringToBCrypt;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UtenteController {
	stringToBCrypt pwdCrypter;
	
	private final Logger log = LoggerFactory.getLogger(UtenteController.class);

	private static final String ENTITY_NAME = "Utente";
	
	private final UtenteService utenteService;

	public UtenteController(UtenteService utenteService) {
	    this.utenteService = utenteService;
	}

    @PostMapping("/insert")
    public ResponseEntity<UtenteDTO> createUser(@Valid @RequestBody UtenteDTO utenteDTO) throws URISyntaxException {
        log.debug("REST request to save user : {}", utenteDTO);
        String hashedPwd = this.pwdCrypter.hashPassword(utenteDTO.getPassword());
        utenteDTO.setPassword(hashedPwd);
        UtenteDTO result = utenteService.save(utenteDTO);
        return ResponseEntity.created(new URI("/utente" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(result.getId())))
                .body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<UtenteDTO> updateUser(@Valid @RequestBody UtenteDTO utenteDTO) throws URISyntaxException {
        log.debug("REST request to update utenteDTO : {}", utenteDTO);
        String hashedPwd = this.pwdCrypter.hashPassword(utenteDTO.getPassword());
        utenteDTO.setPassword(hashedPwd);
        UtenteDTO result = utenteService.save(utenteDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, String.valueOf(utenteDTO.getId()))).body(result);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UtenteDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of Users");
        Page<UtenteDTO> page = utenteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/user/findAll");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/findUser/{id}")
    public ResponseEntity<UtenteDTO> getUser(@PathVariable Long id) {
        log.debug("REST request to get User : {}", id);
        Optional<UtenteDTO> utenteDTO = utenteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(utenteDTO);
    }
    
    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<UtenteDTO> findByUsername(@PathVariable String username) {
    	UtenteDTO utenteDTO = utenteService.findByUsername(username);
        return ResponseEntity.ok().body(utenteDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("REST request to delete Utenti : {}", id);
        utenteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
}

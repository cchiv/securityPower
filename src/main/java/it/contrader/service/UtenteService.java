package it.contrader.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.contrader.dao.UtenteRepository;
import it.contrader.dto.UtenteDTO;
import it.contrader.mapper.UtenteMapper;
import it.contrader.model.Utente;

import java.util.Optional;

@Service
@Transactional
public class UtenteService implements ServiceDTO<UtenteDTO> {
	private final Logger log = LoggerFactory.getLogger(UtenteService.class);
	
    private final UtenteRepository utenteRepository;
    
    private final UtenteMapper utenteMapper;

    public UtenteService(UtenteMapper utenteMapper, UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
        this.utenteMapper = utenteMapper;
    }

    @Override
    public UtenteDTO save(UtenteDTO utenteDTO) {
        log.debug("Request to save Utente : {}", utenteDTO);
        Utente utente = utenteMapper.toEntity(utenteDTO);
        utente = utenteRepository.save(utente);
        return utenteMapper.toDTO(utente);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UtenteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all User");
        return utenteRepository.findAll(pageable).map(utenteMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UtenteDTO> findOne(Long id) {
        log.debug("Request to get Utente : {}", id);
        return utenteRepository.findById(id).map(utenteMapper::toDTO);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete User : {}", id);
        utenteRepository.deleteById(id);
    }

    public UtenteDTO findByUsernameAndPassword(String username, String password) {
        return utenteMapper.toDTO(((UtenteRepository) utenteRepository).findByUsernameAndPassword(username, password));
    }
    
    public UtenteDTO findByUsername(String username) {
        return utenteMapper.toDTO(((UtenteRepository) utenteRepository).findByUsername(username));
    }   
    
}
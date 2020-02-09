package it.contrader.mapper;

import org.mapstruct.Mapper;

import it.contrader.dto.UtenteDTO;
import it.contrader.model.Utente;

@Mapper(componentModel = "spring")
public interface UtenteMapper extends InterfaceMapper<UtenteDTO, Utente>{

    default Utente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Utente utente = new Utente();
        utente.setId(id);
        return utente;
    }
}

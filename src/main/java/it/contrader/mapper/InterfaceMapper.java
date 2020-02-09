package it.contrader.mapper;

import java.util.List;

public interface InterfaceMapper <DTO, Entity> {

    public Entity toEntity(DTO dto);
    public DTO toDTO(Entity entity);
    public List <Entity> toEntityList(Iterable<DTO> dtoList);
    public List <DTO> toDTOList(Iterable<Entity> entityList); 
    
}
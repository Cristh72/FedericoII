package it.esercitazine.federicoii.federicoii.mapper;

import it.esercitazine.federicoii.federicoii.dto.AnagraficaDTO;
import it.esercitazine.federicoii.federicoii.entity.Anagrafica;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class AnagraficaMapper {
    public List<AnagraficaDTO> anagraficaToDTOs(List<Anagrafica> anagraficas) {
        List<AnagraficaDTO> anagsDTO = new ArrayList<>();
        if (anagraficas==null || anagraficas.isEmpty())
            return Collections.emptyList();

        for (Anagrafica anag : anagraficas){
            anagsDTO.add(anagraficaToDTO(anag));
        }

        return anagsDTO;
    }

    public AnagraficaDTO anagraficaToDTO(Anagrafica anagrafica) {
        AnagraficaDTO anagDTO = new AnagraficaDTO();
        anagDTO.setId(anagrafica.getId());
        anagDTO.setNome(anagrafica.getNome());
        anagDTO.setCognome(anagrafica.getCognome());
        anagDTO.setDataNascita(anagrafica.getDataNascita());
        anagDTO.setLuogoDiNascita(anagrafica.getLuogoDiNascita());

        return anagDTO;
    }

    public Anagrafica anagraficaToEntity(AnagraficaDTO anagraficaDTO) {
        Anagrafica anagraficaEntity = new Anagrafica();
        anagraficaEntity.setNome(anagraficaDTO.getNome());
        anagraficaEntity.setCognome(anagraficaDTO.getCognome());
        anagraficaEntity.setLuogoDiNascita(anagraficaDTO.getLuogoDiNascita());
        anagraficaEntity.setDataNascita(anagraficaDTO.getDataNascita());
        return anagraficaEntity;
    }
}

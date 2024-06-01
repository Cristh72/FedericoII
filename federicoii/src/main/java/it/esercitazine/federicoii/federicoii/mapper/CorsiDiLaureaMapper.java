package it.esercitazine.federicoii.federicoii.mapper;

import it.esercitazine.federicoii.federicoii.dto.CdlConStudentiDTO;
import it.esercitazine.federicoii.federicoii.dto.CorsiDiLaureaDTO;
import it.esercitazine.federicoii.federicoii.entity.CorsiDiLaurea;
import it.esercitazine.federicoii.federicoii.entity.Studenti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CorsiDiLaureaMapper {


    public List<CorsiDiLaureaDTO> corsiToDTOs(List<CorsiDiLaurea> corsiDiLaureas) {
        List<CorsiDiLaureaDTO> corsiDTO = new ArrayList<>();
        if (corsiDiLaureas==null || corsiDiLaureas.isEmpty())
            return Collections.emptyList();

        for (CorsiDiLaurea corsi : corsiDiLaureas){
            corsiDTO.add(corsiToDTO(corsi));
        }

        return corsiDTO;
    }

    public CorsiDiLaureaDTO corsiToDTO(CorsiDiLaurea corso) {
        CorsiDiLaureaDTO corsoDTO = new CorsiDiLaureaDTO();
        corsoDTO.setId(corso.getId());
        corsoDTO.setNome(corso.getNome());
        corsoDTO.setRamoDiAppartenenza(corso.getRamoDiAppartenenza());
        //corsoDTO.setStudenti(studentiMapper.studentiToDTOs(corso.getStudenti()));
        corsoDTO.setNumStudenti(corso.getNumStudenti());

        return corsoDTO;
    }

    public CorsiDiLaurea corsiToEntity(CorsiDiLaureaDTO corsiDiLaureaDTO) {
        CorsiDiLaurea corsoEntity = new CorsiDiLaurea();
        corsoEntity.setId(corsiDiLaureaDTO.getId());
        corsoEntity.setNome(corsiDiLaureaDTO.getNome());
        corsoEntity.setRamoDiAppartenenza(corsiDiLaureaDTO.getRamoDiAppartenenza());


        return corsoEntity;
    }


}

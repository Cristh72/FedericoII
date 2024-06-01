package it.esercitazine.federicoii.federicoii.mapper;

import it.esercitazine.federicoii.federicoii.dto.CdlDocentiDTO;
import it.esercitazine.federicoii.federicoii.dto.CdlStudentiEDocentiDTO;
import it.esercitazine.federicoii.federicoii.entity.CorsiDiLaurea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CdlDocentiMapper {
    @Autowired
    DocentiMapper docentiMapper;
    public List<CdlDocentiDTO> cdlToDTOs(List<CorsiDiLaurea> corsiDiLaureas) {
        List<CdlDocentiDTO> corsiDTO = new ArrayList<>();
        if (corsiDiLaureas==null || corsiDiLaureas.isEmpty())
            return Collections.emptyList();

        for (CorsiDiLaurea corsi : corsiDiLaureas){
            corsiDTO.add(cdlToDTO(corsi));
        }

        return corsiDTO;
    }

    public CdlDocentiDTO cdlToDTO(CorsiDiLaurea corso) {
        CdlDocentiDTO cdlDTO = new CdlDocentiDTO();
        cdlDTO.setId(corso.getId());
        cdlDTO.setNome(corso.getNome());
        cdlDTO.setRamoDiAppartenenza(corso.getRamoDiAppartenenza());
        cdlDTO.setDocentiDTO(docentiMapper.docentiToDTOs(corso.getDocenti()));

        return cdlDTO;
    }
}

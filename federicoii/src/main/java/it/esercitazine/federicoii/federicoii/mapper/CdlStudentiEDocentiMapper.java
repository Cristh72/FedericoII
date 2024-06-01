package it.esercitazine.federicoii.federicoii.mapper;

import it.esercitazine.federicoii.federicoii.dto.CdlConStudentiDTO;
import it.esercitazine.federicoii.federicoii.dto.CdlStudentiEDocentiDTO;
import it.esercitazine.federicoii.federicoii.entity.CorsiDiLaurea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CdlStudentiEDocentiMapper {

    @Autowired
    StudentiMapper studentiMapper;
    @Autowired
    DocentiMapper docentiMapper;
    public List<CdlStudentiEDocentiDTO> cdlToDTOs(List<CorsiDiLaurea> corsiDiLaureas) {
        List<CdlStudentiEDocentiDTO> corsiDTO = new ArrayList<>();
        if (corsiDiLaureas==null || corsiDiLaureas.isEmpty())
            return Collections.emptyList();

        for (CorsiDiLaurea corsi : corsiDiLaureas){
            corsiDTO.add(cdlToDTO(corsi));
        }

        return corsiDTO;
    }

    public CdlStudentiEDocentiDTO cdlToDTO(CorsiDiLaurea corso) {
        CdlStudentiEDocentiDTO cdlDTO = new CdlStudentiEDocentiDTO();
        cdlDTO.setId(corso.getId());
        cdlDTO.setNome(corso.getNome());
        cdlDTO.setRamoDiAppartenenza(corso.getRamoDiAppartenenza());
        cdlDTO.setStudentiDTO(studentiMapper.studentiToDTOs(corso.getStudenti()));
        cdlDTO.setDocentiDTO(docentiMapper.docentiToDTOs(corso.getDocenti()));

        return cdlDTO;
    }
}

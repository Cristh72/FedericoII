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
public class CdlConStudentiDTOMapper {

    @Autowired
    StudentiMapper studentiMapper;
    public List<CdlConStudentiDTO> cdlToDTOs(List<CorsiDiLaurea> corsiDiLaureas) {
        List<CdlConStudentiDTO> corsiDTO = new ArrayList<>();
        if (corsiDiLaureas==null || corsiDiLaureas.isEmpty())
            return Collections.emptyList();

        for (CorsiDiLaurea corsi : corsiDiLaureas){
            corsiDTO.add(cdlToDTO(corsi));
        }

        return corsiDTO;
    }

    public CdlConStudentiDTO cdlToDTO(CorsiDiLaurea corso) {
        CdlConStudentiDTO cdlDTO = new CdlConStudentiDTO();
        cdlDTO.setId(corso.getId());
        cdlDTO.setNome(corso.getNome());
        cdlDTO.setRamoDiAppartenenza(corso.getRamoDiAppartenenza());
        cdlDTO.setStudenti(studentiMapper.studentiToDTOs(corso.getStudenti()));

        return cdlDTO;
    }


    public CorsiDiLaurea  cdlToEntity(CdlConStudentiDTO corso) {
        CorsiDiLaurea cdlEnti = new CorsiDiLaurea();
        cdlEnti.setId(corso.getId());
        cdlEnti.setNome(corso.getNome());
        cdlEnti.setRamoDiAppartenenza(corso.getRamoDiAppartenenza());
        cdlEnti.setStudenti(studentiMapper.studentiToEntities(corso.getStudenti()));
        Integer numeStudenti = numeris(cdlEnti.getStudenti());
        cdlEnti.setNumStudenti(numeStudenti);

        return cdlEnti;
    }

    public List<CorsiDiLaurea> cdlToEntities(List<CdlConStudentiDTO> corsiDiLaureas) {
        List<CorsiDiLaurea> corsiDTO = new ArrayList<>();
        if (corsiDiLaureas==null || corsiDiLaureas.isEmpty())
            return Collections.emptyList();

        for (CdlConStudentiDTO corsi : corsiDiLaureas){
            corsiDTO.add(cdlToEntity(corsi));
        }

        return corsiDTO;
    }

    public Integer numeris(List<Studenti> studentis){
        Integer numeris = 0;

        if(studentis==null || studentis.isEmpty()) {
            return 0;
        }

        for (Studenti s:studentis){
            numeris = numeris + 1;
        }
        return numeris;}
}

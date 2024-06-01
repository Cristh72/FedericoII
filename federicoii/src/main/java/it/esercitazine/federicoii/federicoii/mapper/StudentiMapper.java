package it.esercitazine.federicoii.federicoii.mapper;

import it.esercitazine.federicoii.federicoii.dto.CorsiDiLaureaDTO;
import it.esercitazine.federicoii.federicoii.dto.StudentiDTO;
import it.esercitazine.federicoii.federicoii.entity.Anagrafica;
import it.esercitazine.federicoii.federicoii.entity.CorsiDiLaurea;
import it.esercitazine.federicoii.federicoii.entity.Docenti;
import it.esercitazine.federicoii.federicoii.entity.Studenti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class StudentiMapper {

    @Autowired
    CorsiDiLaureaMapper corsiDiLaureaMapper;
    @Autowired
    AnagraficaMapper anagraficaMapper;
    public StudentiDTO studenteToDTO(Studenti studente){
        StudentiDTO studenteDto = new StudentiDTO();
        studenteDto.setId(studente.getId());
        studenteDto.setCf(studente.getCf());
        studenteDto.setAnagraficaDTO(anagraficaMapper.anagraficaToDTO(studente.getAnagrafica()));
        if (studente.getCorsiDiLaurea() != null){
            studenteDto.setCorsiDiLaureaDTO(corsiDiLaureaMapper.corsiToDTO(studente.getCorsiDiLaurea()));
        }else {
            studenteDto.setCorsiDiLaureaDTO(null);
        }

        return studenteDto;
    }
    public List<StudentiDTO> studentiToDTOs(List<Studenti> studentis) {
        List<StudentiDTO> studentiDto = new ArrayList<>();

        for(Studenti studente:studentis){
            studentiDto.add(studenteToDTO(studente));
        }
        return studentiDto;
    }

    public Studenti studentiToEntity(StudentiDTO nuovoStudente) {
        Studenti studenteEntity = new Studenti();
        studenteEntity.setId(nuovoStudente.getId());
        studenteEntity.setCf(nuovoStudente.getCf());
        studenteEntity.setAnagrafica(anagraficaMapper.anagraficaToEntity(nuovoStudente.getAnagraficaDTO()));
        studenteEntity.setCorsiDiLaurea(corsiDiLaureaMapper.corsiToEntity(nuovoStudente.getCorsiDiLaureaDTO()));

        return studenteEntity;
    }

    public List<Studenti> studentiToEntities(List<StudentiDTO> studenti) {
        List<Studenti> studentiEntity = new ArrayList<>();
        for(StudentiDTO studente:studenti){
            studentiEntity.add(studentiToEntity(studente));
        }
        return studentiEntity;
    }
}

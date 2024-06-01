package it.esercitazine.federicoii.federicoii.mapper;

import it.esercitazine.federicoii.federicoii.dto.StudenteTrovatoPerCFDTO;
import it.esercitazine.federicoii.federicoii.dto.StudentiDTO;
import it.esercitazine.federicoii.federicoii.entity.Studenti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class StudenteTrovatoPerCFMapper {

    @Autowired
    AnagraficaMapper anagraficaMapper;
    public StudenteTrovatoPerCFDTO studenteTrovatoToDTO(Studenti studente){
        StudenteTrovatoPerCFDTO studenteTrovatoPerCFDTOvatoPerDto = new StudenteTrovatoPerCFDTO();
        studenteTrovatoPerCFDTOvatoPerDto.setId(studente.getId());
        studenteTrovatoPerCFDTOvatoPerDto.setCf(studente.getCf());
        studenteTrovatoPerCFDTOvatoPerDto.setAnagraficaDTO(anagraficaMapper.anagraficaToDTO(studente.getAnagrafica()));

        return studenteTrovatoPerCFDTOvatoPerDto;
    }
    public List<StudenteTrovatoPerCFDTO> studenteTrovatoToDTOs(List<Studenti> studentis) {
        List<StudenteTrovatoPerCFDTO> studenteTrovatoPerCFDTOvatoPerDtoS = new ArrayList<>();
        for(Studenti studente:studentis){
            studenteTrovatoPerCFDTOvatoPerDtoS.add(studenteTrovatoToDTO(studente));
        }
        return studenteTrovatoPerCFDTOvatoPerDtoS;
    }

    public Studenti studenteTrovatoToEntenty(StudenteTrovatoPerCFDTO nuovoStudente) {
        Studenti studentiNewEntity = new Studenti();
        studentiNewEntity.setId(nuovoStudente.getId());
        studentiNewEntity.setCf(nuovoStudente.getCf());
        studentiNewEntity.setAnagrafica(anagraficaMapper.anagraficaToEntity(nuovoStudente.getAnagraficaDTO()));

        return studentiNewEntity;
    }
}

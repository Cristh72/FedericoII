package it.esercitazine.federicoii.federicoii.mapper;

import it.esercitazine.federicoii.federicoii.dto.DocentiDTO;
import it.esercitazine.federicoii.federicoii.dto.DocentiSenzaCDLDTO;
import it.esercitazine.federicoii.federicoii.entity.Docenti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DocentiMapper {
    @Autowired
    AnagraficaMapper anagraficaMapper;
    @Autowired
    CorsiDiLaureaMapper corsiDiLaureaMapper;

    public DocentiDTO docentiToDTO(Docenti docente){
        DocentiDTO docentiDTO = new DocentiDTO();
        docentiDTO.setId(docente.getId());
        docentiDTO.setCf(docente.getCf());
        docentiDTO.setAnagraficaDTO(anagraficaMapper.anagraficaToDTO(docente.getAnagrafica()));
        if (docente.getCorsiDiLaurea()==null){
            docentiDTO.setCorsiDiLaureaDTO(null);
        }else {
            docentiDTO.setCorsiDiLaureaDTO(corsiDiLaureaMapper.corsiToDTO(docente.getCorsiDiLaurea()));
        }

        return docentiDTO;
    }
    public List<DocentiDTO> docentiToDTOs(List<Docenti> docenti) {

        List<DocentiDTO> listaDocenti = new ArrayList<>();
        if (docenti==null || docenti.isEmpty()){
            return Collections.emptyList();
        }

        for(Docenti prof : docenti){
            listaDocenti.add(docentiToDTO(prof));
        }

        return listaDocenti;
    }

    public Docenti docentiToEntity(DocentiSenzaCDLDTO nuovoProf) {

        Docenti docentiDTO = new Docenti();
        docentiDTO.setId(nuovoProf.getId());
        docentiDTO.setCf(nuovoProf.getCf());
        docentiDTO.setAnagrafica(anagraficaMapper.anagraficaToEntity(nuovoProf.getAnagraficaDTO()));

        return docentiDTO;
    }
}

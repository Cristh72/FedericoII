package it.esercitazine.federicoii.federicoii.mapper;

import it.esercitazine.federicoii.federicoii.dto.DocentiSenzaCDLDTO;
import it.esercitazine.federicoii.federicoii.entity.Docenti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DocentiSenzaCDLMapper {

    @Autowired
    AnagraficaMapper anagraficaMapper;

    public DocentiSenzaCDLDTO docentiSenzaToDTO(Docenti docente){
        DocentiSenzaCDLDTO docentiDTO = new DocentiSenzaCDLDTO();
        docentiDTO.setId(docente.getId());
        docentiDTO.setCf(docente.getCf());
        docentiDTO.setAnagraficaDTO(anagraficaMapper.anagraficaToDTO(docente.getAnagrafica()));

        return docentiDTO;
    }
    public List<DocentiSenzaCDLDTO> docentiSenzaToDTOs(List<Docenti> docenti) {

        List<DocentiSenzaCDLDTO> listaDocenti = new ArrayList<>();
        if (docenti==null || docenti.isEmpty()){
            return Collections.emptyList();
        }

        for(Docenti prof : docenti){
            listaDocenti.add(docentiSenzaToDTO(prof));
        }

        return listaDocenti;
    }
}

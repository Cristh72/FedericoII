package it.esercitazine.federicoii.federicoii.dto;

import it.esercitazine.federicoii.federicoii.entity.Anagrafica;
import it.esercitazine.federicoii.federicoii.entity.CorsiDiLaurea;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentiDTO {

    private Long id;
    private String cf;
    private CorsiDiLaureaDTO corsiDiLaureaDTO;
    private AnagraficaDTO anagraficaDTO;

}

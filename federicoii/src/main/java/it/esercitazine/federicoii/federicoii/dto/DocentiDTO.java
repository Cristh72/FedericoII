package it.esercitazine.federicoii.federicoii.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocentiDTO {

    private Long id;
    private String cf;
    private CorsiDiLaureaDTO corsiDiLaureaDTO;
    private AnagraficaDTO anagraficaDTO;
}

package it.esercitazine.federicoii.federicoii.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CdlDocentiDTO {

    private Long id;
    private String nome;
    private String ramoDiAppartenenza;
    private List<DocentiDTO> docentiDTO;
}

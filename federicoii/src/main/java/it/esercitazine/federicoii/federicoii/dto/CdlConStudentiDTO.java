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
public class CdlConStudentiDTO {

    private Long id;
    private String nome;
    private String ramoDiAppartenenza;
    private Integer numStudenti;
    private List<StudentiDTO> studenti;
}

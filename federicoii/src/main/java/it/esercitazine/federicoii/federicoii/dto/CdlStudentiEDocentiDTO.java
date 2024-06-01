package it.esercitazine.federicoii.federicoii.dto;

import it.esercitazine.federicoii.federicoii.entity.Docenti;
import it.esercitazine.federicoii.federicoii.entity.Studenti;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CdlStudentiEDocentiDTO {

    private Long id;
    private String nome;
    private String ramoDiAppartenenza;
    private List<StudentiDTO> studentiDTO;
    private List<DocentiDTO> docentiDTO;
}

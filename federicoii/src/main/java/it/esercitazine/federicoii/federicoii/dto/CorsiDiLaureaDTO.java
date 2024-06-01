package it.esercitazine.federicoii.federicoii.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CorsiDiLaureaDTO {

    private Long id;
    private String nome;
    private String ramoDiAppartenenza;
    private Integer numStudenti;

}

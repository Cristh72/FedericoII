package it.esercitazine.federicoii.federicoii.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnagraficaDTO {

    private Long id;
    private String nome;
    private String cognome;
    private String dataNascita;
    private String luogoDiNascita;
}

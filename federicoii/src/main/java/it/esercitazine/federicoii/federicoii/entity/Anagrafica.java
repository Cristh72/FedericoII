package it.esercitazine.federicoii.federicoii.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "anagrafica")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anagrafica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private String cognome;
    @Column(name = "data_di_nascita")
    private String dataNascita;
    @Column(name = "luogo_di_nascita")
    private String luogoDiNascita;


}

package it.esercitazine.federicoii.federicoii.entity;

import it.esercitazine.federicoii.federicoii.dto.StudentiDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "corsi_di_laurea")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CorsiDiLaurea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @Column
    private String ramoDiAppartenenza;
    @OneToMany(mappedBy = "corsiDiLaurea", cascade = CascadeType.ALL)
    private List<Studenti> studenti;
    @OneToMany(mappedBy = "corsiDiLaurea", cascade = CascadeType.ALL)
    private List<Docenti> docenti;
    @Column
    private Integer numStudenti;

}

package it.esercitazine.federicoii.federicoii.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "docenti")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Docenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 16)
    @Size(min = 16, max = 16)
    private String cf;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private CorsiDiLaurea corsiDiLaurea;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Anagrafica anagrafica;
}
